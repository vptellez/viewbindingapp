# ViewBinding Cart App (Android)

Aplicación Android desarrollada con **ViewBinding**, **Kotlin**, **Material 3** y **arquitectura basada en estado**.  
El proyecto demuestra cómo construir una UI reactiva sin Jetpack Compose, manteniendo buenas prácticas modernas (2026).

---

## Características

- ViewBinding habilitado
- Arquitectura UI → ViewModel → State
- StateFlow para manejo de estado
- RecyclerView con DiffUtil
- Contador de productos tipo carrito
- Botones Material 3
- Animaciones con ValueAnimator
- Navigation Component
- Tema Material 3 habilitado

---

## Arquitectura

ui (Fragment / Adapter) -> ViewModel -> State (CartState) -> Model (Product)
- La UI **no contiene lógica**
- El ViewModel expone un único estado inmutable
- El estado es la única fuente de verdad

---

## Tecnologías

- Kotlin 2.0
- Android SDK 36
- ViewBinding
- Material Design 3
- StateFlow
- Coroutines
- Navigation Component
