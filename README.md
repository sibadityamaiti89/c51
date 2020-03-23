# Architecture

The app is built on Kotlin and follows an MVVM architecture. The android activity and fragments function as views, the ViewModel serves the business logic and holds properties in the form of LiveData which the fragments observe.
The ViewModel works with the repository(Network package) which abstracts out the data source from the ViewModel.
We are using Data to bind data to those views.
Viewmodels in the app extends the android architecture components ViewModel classes. 


# Testing efforts
The ViewModel and the Utils have been unit tested.


# Other Libraries used on the project

1)Android architecture components (ViewModel, LiveData, Navigation Component, Data binding)

2)Glide (Image loading, rendering and caching)

3)Square Moshi (JSON parsing)

4)Lottie (To show loading and error state animation)

5)Retrofit (For network calling)

6)MockWebServer(For mocking webserver call)

7)JUnit(For unit testing)

