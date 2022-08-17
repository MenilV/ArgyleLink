# Argyle Link Android app

### Important notes
The API was supposed to be locked with `basic auth` but tests show that it's widely available and usable even without auth

### Architecture
MVI + RxJava + Hilt + Retrofit

### Libraries
Standard: Kotlin StdLib & AndroidX libraries
Networking: OkHttp+Retrofit+Moshi
Async: RxJava/RxKotlin and other Rx related libraries
DI: Dagger/Hilt
Image loading: Glide
Testing: JUnit, Espresso