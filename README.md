# CineStash

CineStash is a modern movie discovery application built with **Kotlin Multiplatform (KMP)** and **Jetpack Compose**. 

This project serves as a practical implementation of modern Android development standards, focusing on clean architecture (MVVM), reactive UI patterns, and the latest Android 15 edge-to-edge display requirements.

## 🚀 Features

* **Movie Discovery:** Browse rich details about movies, including ratings, release dates, and overviews.
* **Immersive Detail View:** A fully edge-to-edge detail screen featuring a poster header that draws behind the system status bar.
* **Cast & Crew:** Horizontal scrollable lists for cast members with circular avatars.
* **Favorites System:** Ability to toggle and persist favorite movies locally.
* **Robust State Management:** Handles Loading, Success, and Error states seamlessly using Kotlin Flows.
* **Material 3 Design:** Utilizes the latest Material Design components and theming.

## 🛠️ Tech Stack

* **Language:** Kotlin
* **Platform:** Kotlin Multiplatform (Android Target)
* **UI Toolkit:** Jetpack Compose (Material 3)
* **Architecture:** MVVM (Model-View-ViewModel)
* **Dependency Injection:** Koin
* **Image Loading:** Coil 3
* **Concurrency:** Kotlin Coroutines & Flow
* **Navigation:** Jetpack Navigation Compose

## 📱 Engineering Highlights

### Edge-to-Edge Compliance (Android 15+)
CineStash is built to strictly adhere to the new Android 15 edge-to-edge enforcement.
* **System Bars:** The app draws content behind both the status bar and navigation bar for a premium, immersive feel.
* **Insets Handling:** Instead of relying on default `Scaffold` padding, the app manually applies `WindowInsets`, `statusBarsPadding()`, and `navigationBarsPadding()` to specific UI elements to ensure content is never obscured while maintaining full-screen visuals.

### Clean Architecture
The application follows a strict separation of concerns:
* **UI Layer:** Pure Composables that observe `StateFlow` from ViewModels.
* **Presentation Layer:** ViewModels that handle business logic and expose a single `UiState`.
* **Data Layer:** Repositories responsible for data fetching and error handling.
