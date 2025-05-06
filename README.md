# Android MVVM Architecture - JustCats APP

`MVVM Architecture` is one of the most popular architecture to develop an app, MVVM architectural pattern divides an application into three key components: Model, View and ViewModel. Model: This is your data and business logic, here you process data, perform opperations, and interact with data sources. View: This is the User Interface (UI) of your App. ViewModel: Acts as a mediator between the model and view.

<img src="https://github.com/user-attachments/assets/87232356-b425-4432-b6ee-79c7b31beffa" alt="home_justcats" width="300" height="500">
<img src="https://github.com/user-attachments/assets/ac50e70b-8378-4a41-b462-d86db816b956" alt="detail_justcats" width="300" height="500">




## Prerequisites
Basic `Kotlin`, knowledge of `HTTP` request by Retrofit Library, `Room` for local database, `Koin` for dependency injection

## Project Description
I build a JustCat Application with MVVM Architecture, In the home screen you will get a list of cat photos, when you click the photos you will get the detail of the cat like Name, Temprament and Description, and there is a `Heart Button` that you can click to add the cat to the favorite screen, thiws action using the `DAO` Methode to save the cat to the local database that we create using `Room library`. for UI i get inspired from Pinterest which using the Staggered grid so the image can be dynamic based on itws height.

## TheCatAPI
I use [TheCatAPI](https://developers.thecatapi.com/) for collecting Cat photos and cat information.

## Libraries
* [Retrofit2](https://github.com/square/retrofit) for HTTP Request
* [Ksp](https://github.com/google/ksp/releases) for Annotation Processors
* [Coil](https://github.com/coil-kt/coil) for Image Loader
* [Room](https://developer.android.com/jetpack/androidx/releases/room) for Local Database
* [Koin](https://insert-koin.io/) for Dependency Injection
* [Paging3](https://developer.android.com/jetpack/androidx/releases/paging) for Load Bulk Data

## Run Project
This Project build on `JDK 2.2`. Sync the `Gradle` and run the project. Install APK on your emulator on real device. Don't forget to turn on the `Internet` in your device so the application can access the API
