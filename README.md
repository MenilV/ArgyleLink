# Argyle Link Android app

### Important notes
The API was supposed to be locked with `basic auth` but tests show that it's widely available and usable even without auth
but upon further investigation, it seems that data *is missing* if there's no basic auth header included (eg. Logo urls)

### Architecture
MVI + RxJava2 (compatible with RxJava3) + Hilt + OkHttp + Retrofit

### Libraries
Standard: Kotlin StdLib & AndroidX libraries
Networking: OkHttp, Retrofit, Moshi
Async: RxJava/RxKotlin and other Rx related libraries
DI: Dagger/Hilt
Image loading: Glide
Testing: JUnit, Espresso, Robolectric, Mockito


### Known issues
A potential issue can occur in the SearchItem not being unique enough as it is a `name+kind+image` which might not be unique always
Know issue with clearing the searchview requires twice to click the clear button before resetting the list
Integration tests have an issue with webmockserver if the architecture uses rxjava3 which is fully compatible. With rxjava2 they work as expected

### Extras
The project has a mini CI pipeline when pushing to master branch to run tests and checks and create a build
Tests and checks can be run manually locally as well (checks are in the scrips directory)