# Clean Architecture Demo App
This is a clean architecture app example built with

- Coroutines
- MVVM
- Extension Functions
- Dagger Hilt
- Retrofit
- Room
- Navigation Components
- Cache Strategy (Repository Pattern)
- Coroutines Flow
- ViewBinding

# Architecture

![Test Image 4](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)


## what is the architecture/structure in this project:
###### I use clean architecture with MVVM for my presenter .

## Why Clean Architecture?
###### when you start a project it is really important to have a clean architecture mostly for large applications. you can test all layers independently. this is when Clean Architecture can help you have independent layers. what I see in my project as layers is UI, domain, data. Ui handles Views, Domain handles use-cases and buisiness logic, data is where you can find repositories related to API or database.

## What is MVVM?
###### It's an architectural pattern that has 3 types of objects including Models, Views, ViewModels.
###### * Model: it keeps data. they are data classes that are filling by use-cases.
###### * View: the screens like activities or fragments
###### * ViewModel: it is like a bridge between model and view. it gives data from the model and gives it to view.
