# Akbar App - Android Application

A modern Android application built with Kotlin and Android Architecture Components.

## Project Structure

- `app/` - Main application module
- `app/src/main/java/` - Kotlin source code
- `app/src/main/res/` - Android resources (layouts, drawables, strings)
- `build.gradle` - Project and module build configurations

## Getting Started

### Prerequisites
- Android Studio (latest version)
- Android SDK 21 or higher
- Kotlin 1.9+

### Build Instructions
```bash
./gradlew build
```

### Run on Emulator/Device
```bash
./gradlew installDebug
./gradlew run
```

## Architecture

This project uses MVVM architecture with:
- LiveData for reactive UI updates
- Room Database for local storage
- Retrofit for API calls
- Dagger Hilt for dependency injection
