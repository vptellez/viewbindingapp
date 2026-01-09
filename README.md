# ViewBinding Cart App (Android)

Aplicación Android desarrollada con **ViewBinding**, **Kotlin**, **Material Design 3** y una **arquitectura basada en estado**.  
El proyecto demuestra cómo construir una UI reactiva y mantenible **sin Jetpack Compose**, aplicando buenas prácticas vigentes en 2026.

---

![CI](https://github.com/vptellez/viewbindingapp/actions/workflows/android-ci.yml/badge.svg)

---

## Características

- ViewBinding habilitado
- Arquitectura unidireccional UI → ViewModel → State
- StateFlow para manejo de estado
- RecyclerView con DiffUtil
- Contador de productos tipo carrito
- Botones Material 3
- Animaciones con ValueAnimator
- Navigation Component
- Tema Material 3

---

## 🧱 Arquitectura

UI (Fragment / Adapter)
↓
ViewModel
↓
State (CartState)
↓
Model (Product)


### Principios aplicados

- La UI **no contiene lógica de negocio**
- El ViewModel expone un **único estado inmutable**
- El estado es la **única fuente de verdad**
- Flujo de datos unidireccional

Este enfoque facilita el testeo, el mantenimiento y una posible migración futura a Jetpack Compose.

---

## 🛠️ Tecnologías

- Kotlin 2.0
- Android SDK 36
- ViewBinding
- Material Design 3
- StateFlow
- Coroutines
- Navigation Component

---

## Ejecución del proyecto

1. Clonar el repositorio
2. Abrir en Android Studio (AGP 8.13+)
3. Ejecutar en emulador o dispositivo físico (minSdk 24)

No se requiere configuración adicional.

---

## Tests

El proyecto incluye tests unitarios del ViewModel que validan:

- Incremento y decremento de productos
- Prevención de valores negativos
- Cálculo correcto del total del carrito

---

## 📌 Notas

Este proyecto utiliza ViewBinding de forma intencional para demostrar una alternativa moderna y válida al uso de Jetpack Compose en escenarios reales de producción.

---

## 📄 Licencia

MIT
