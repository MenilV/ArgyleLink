# Argyle Link Android app

### Important notes
The API was supposed to be locked with `basic auth` but tests show that it's widely available and usable even without auth
but upon further investigation, it seems that data *is missing* if there's no basic auth header included
(eg. Logo urls)

### Architecture
MVI + RxJava + Hilt + Retrofit

### Libraries
Standard: Kotlin StdLib & AndroidX libraries
Networking: OkHttp+Retrofit+Moshi
Async: RxJava/RxKotlin and other Rx related libraries
DI: Dagger/Hilt
Image loading: Glide
Testing: JUnit, Espresso


### Known issues
A potential issue can occur in the SearchItem not being unique enough as it is a `name+kind+image` which might not be unique always