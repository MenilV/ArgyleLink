package com.menilv.network.common

import com.menilv.exception.ApiErrorException
import com.menilv.exception.GenericException
import com.menilv.exception.NoInternetException
import com.menilv.exception.SerializationException
import com.menilv.exception.UnauthorizedException
import com.menilv.model.base.ErrorMessageEnum
import com.squareup.moshi.JsonDataException
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import java.io.IOException
import java.lang.reflect.Type
import java.net.UnknownHostException
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class RxCallAdapterFactory private constructor() : CallAdapter.Factory() {
    private val original: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *> {
        return RxCallAdapterWrapper(
            retrofit, original.get(returnType, annotations, retrofit) as CallAdapter<Any, Any>
        )
    }

    private inner class RxCallAdapterWrapper constructor(
        private val retrofit: Retrofit,
        private val wrapped: CallAdapter<Any, Any>
    ) : CallAdapter<Any, Any> {
        override fun adapt(call: Call<Any>): Any {
            val adapted = wrapped.adapt(call)

            return when (adapted) {
                is Maybe<*> -> adapted.onErrorResumeNext { it: Throwable -> Maybe.error(asGenericException(retrofit, it)) }
                is Single<*> -> adapted.onErrorResumeNext { Single.error(asGenericException(retrofit, it)) }
                is Observable<*> -> adapted.onErrorResumeNext { it: Throwable -> Observable.error(asGenericException(retrofit, it)) }
                is Flowable<*> -> adapted.onErrorResumeNext { it: Throwable -> Flowable.error(asGenericException(retrofit, it)) }
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

    companion object {
        fun create(): RxCallAdapterFactory = RxCallAdapterFactory()
    }
}
