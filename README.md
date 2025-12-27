Wishlist App 📌

Wishlist App is a modern Android application that allows users to create, view, and manage a list of desired items.
The app is built using Kotlin and follows MVVM architecture with Room Database for local data storage.

✨ Features

📦 Add wishlist items using Floating Action Button (FAB)

🗄 Store data locally using Room Database

🧠 Clean architecture using MVVM

🧭 App navigation using NavHost and NavController

🔝 Top App Bar with action button navigation

🧹 Swipe to delete wishlist items with delete icon

🔔 Snackbar for user feedback

📱 Simple, clean, and user-friendly UI

🛠 Tech Stack

Language: Kotlin

Architecture: MVVM

Database: Room

UI Components:

FloatingActionButton

TopAppBar

Snackbar

Navigation: Android Navigation Component

🧱 App Architecture (MVVM)
UI (Screens)
   ↓
ViewModel
   ↓
Repository
   ↓
Room Database (DAO + Entity)

📂 Project Structure
│
├── data
│   ├── entity
│   ├── dao
│   ├── database
│   └── repository
│
├── ui
│   ├── screens
│   ├── viewmodel
│   └── navigation
│
└── MainActivity.kt

▶️ How to Run the App

Open the project in Android Studio

Sync Gradle files

Run on emulator or physical device

🎯 What I Learned

Implementing Room Database with MVVM

Handling navigation between screens

Using Swipe gestures with delete icon

Showing feedback using Snackbar

Structuring a scalable Android project

📄 License

This project is open-source 

⭐ If you find this project useful, please star the repository!
