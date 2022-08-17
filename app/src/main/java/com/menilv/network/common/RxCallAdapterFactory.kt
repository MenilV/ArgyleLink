package com.menilv.network.common

import com.menilv.exception.ApiErrorException
import com.menilv.exception.GenericException
import com.menilv.exception.NoInternetException
import com.menilv.exception.SerializationException
import com.menilv.exception.UnauthorizedException
import com.menilv.model.base.ErrorMessageEnum
import com.menilv.model.base.ErrorResponse
import com.squareup.moshi.JsonDataException
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.io.IOException
import java.lang.reflect.Type
import java.net.UnknownHostException
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

class RxCallAdapterFactory private constructor() : CallAdapter.Factory() {
    private val original: RxJava3CallAdapterFactory = RxJava3CallAdapterFactory.create()

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<Any, Any> {
        return RxCallAdapterWrapper(
            retrofit, original.get(returnType, annotations, retrofit) as CallAdapter<Any, Any>
        )
    }

    private inner class RxCallAdapterWrapper constructor(
        private val retrofit: Retrofit,
        private val wrapped: CallAdapter<Any, Any>
    ) : CallAdapter<Any, Any> {
        override fun adapt(call: Call<Any>): Any {
            return when (val adapted = wrapped.adapt(call)) {
                is Maybe<*> -> adapted.onErrorResumeNext { Maybe.error(asGenericException(retrofit, it)) }
                is Single<*> -> adapted.onErrorResumeNext { Single.error(asGenericException(retrofit, it)) }
                is Observable<*> -> adapted.onErrorResumeNext { Observable.error(asGenericException(retrofit, it)) }
                is Flowable<*> -> adapted.onErrorResumeNext { Flowable.error(asGenericException(retrofit, it)) }
                is Completable -> adapted.onErrorResumeNext { Completable.error(asGenericException(retrofit, it)) }
                else -> adapted
            }
        }

        override fun responseType(): Type = wrapped.responseType()
    }

    private fun asGenericException(
        retrofit: Retrofit,
        throwable: Throwable
    ): GenericException {
        return when {
            throwable is HttpException -> {
                when (throwable.code()) {
                    401, 403 -> UnauthorizedException(throwable)
                    400, in 404..499 -> ApiErrorException(
                        ErrorMessageEnum.SOMETHING_WENT_WRONG,
//                        getErrorBodyAs(retrofit, throwable.response())?.error?.get(0)?.message
//                            ?: ErrorMessageEnum.SOMETHING_WENT_WRONG,
                        throwable
                    )
                    in 501..600 -> ApiErrorException(ErrorMessageEnum.SERVICE_TEMP_UNAVAILABLE, throwable)
                    else -> ApiErrorException(ErrorMessageEnum.SOMETHING_WENT_WRONG, throwable)
                }
            }
            throwable is UnknownHostException -> {
                NoInternetException(throwable = throwable)
            }
            (throwable is IOException) or (throwable is JsonDataException) -> {
                SerializationException(throwable = throwable)
            }
            else -> {
                GenericException(throwable)
            }
        }
    }

    private fun getErrorBodyAs(
        retrofit: Retrofit,
        response: Response<*>?
    ): ErrorResponse? {

        val converter: Converter<ResponseBody, ErrorResponse> = retrofit.responseBodyConverter(
            ErrorResponse::class.java, arrayOfNulls<Annotation>(0)
        )

        return response?.errorBody()
            ?.let { converter.convert(it) }
    }

    companion object {
        fun create(): RxCallAdapterFactory = RxCallAdapterFactory()
    }
}
