# WeatherForecaster
A simple weather forecasting Android app.

## Architecture
This app implements Clean Architecture which has three main layers, UI/Presentation, Domain, and Data layer. This is an offline-first app which means the main data source of the app is from the local database. When user does not connect to the internet, the data will be retrieved from the local database. Then, when user connect to the internet, the local database data will be synced with the remote data which fetched from the Rest API. I tried and learned using TDD (Test-Driven Development) approach while developing this app.

## Tech Stack and Libraries
- Kotlin
- Jetpack Compose
- Material 3
- Lottie Animation
- Koin
- Flow
- Coroutines
- Ktor Client
- Kotlin Serialization
- Room
- Jetpack Preferences DataStore
- Mockito

## Features
1. View weather info of a specific location, includes temperature, weather, weather description, humidity, feels like temperature, and wind speed.
2. Search a location to get a weather info.


## Preview
**Home**<br/>
![Home](https://res.cloudinary.com/dkwoatrfe/image/upload/w_360,h_800/v1686909982/Screenshot_2023-06-16-16-56-36-049_com.ajailani.weather_forecaster_gwskxz.jpg)

**Search Location**<br/>
![Search Location](https://res.cloudinary.com/dkwoatrfe/image/upload/w_360,h_800/v1686909982/Screenshot_2023-06-16-16-56-47-830_com.ajailani.weather_forecaster_iny7qm.jpg)