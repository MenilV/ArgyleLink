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

### Screenshots
![scr4](https://user-images.githubusercontent.com/3142641/185617824-d3deee3e-f75f-44d0-a91f-a31d07678760.png)
![scr3](https://user-images.githubusercontent.com/3142641/185617839-f4b6e312-875a-4e67-b8ea-16960ec865d0.png)
![scr2](https://user-images.githubusercontent.com/3142641/185617846-58431106-cb1a-4ef2-a3a7-7e00219e0c84.png)
![scr1](https://user-images.githubusercontent.com/3142641/185617848-879f090a-9ba2-4f08-80c3-2c8484b1b0ff.png)


### Known issues
A potential issue can occur in the SearchItem not being unique enough as it is a `name+kind+image` which might not be unique always
Know issue with clearing the searchview requires twice to click the clear button before resetting the list
Integration tests have an issue with webmockserver if the architecture uses rxjava3 which is fully compatible. With rxjava2 they work as expected

### Extras
The project has a mini CI pipeline when pushing to master branch to run tests and checks and create a build
Tests and checks can be run manually locally as well (checks are in the scrips directory)