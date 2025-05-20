# Capital One Android Take-Home Test

This is a responsive Pokemon app built with Jetpack Compose, using Clean Architecture, Flow, Koin for DI, and Ktor for networking. It fetches Pokemon data from the PokeAPI and displays it in a list with a shared element transition to a detail screen.

---

## Tech Stack

- **Jetpack Compose** UI
- **Ktor** for HTTP calls
- **Koin** for dependency injection
- **Flow + Coroutines** for async streams
- **Coil** for image loading
- **SharedElement API** from `androidx.compose.animation` for transitions

---

## Technical Decisions

- **Clean Architecture** structure: separates domain, data, and presentation layers for modularity and testability.
- **Ktor over Retrofit**: Ktor provides coroutine-native networking with lower overhead, which aligns well with Kotlin Flow pipelines.
- **SharedElement Transitions** were implemented using the experimental `SharedTransitionScope`, following [Android's official guide](https://developer.android.com/develop/ui/compose/animation/shared-elements).
- **StateFlow + collectAsStateWithLifecycle** used for ViewModel → UI state propagation, ensuring lifecycle awareness.

---

## Known Limitations

- The **shared element transition is partially complete** — the image and name from the list screen are passed to the detail screen, but due to API latency, the matching elements are sometimes not ready when the transition starts. With more time, this could be resolved by pre-caching or stubbing detail data at the navigation point.
- No **pagination** or **offline caching** was implemented due to time constraints.
  
### UI Test Coverage
Compose UI tests were not included due to time constraints. However, the app was architected with testability in mind, using StateFlow-based ViewModels and a clean separation between UI and logic layers.
Given more time, I would have written Compose UI tests to verify:
- Loading and error states on both the list and detail screens
- Rendering of Pokémon data and user interactions such as, tapping an item to navigate.

The app is structured to support Compose testing via createComposeRule() and fake ViewModel injection. This would make screen-level tests easy to implement as a next step.

---

## What Works

- Fetching Pokémon list and details from the API
- Navigation between list and detail screens
- ViewModel-driven state management with proper DI
- Compose UI integration and layout logic
- Smooth animations and UI responsiveness
- Modular and testable architecture ready for extension

---

## Extra Info

- The app is written to be **fully testable** and uses `safeApiCall<T>` and `Resource<T>` wrappers for network state, allowing reuse of the same ViewModel structure for both screens.
- Shared transition implementation follows the **latest Compose guidelines** using `SharedTransitionLayout`, and is scoped using `SharedTransitionScope`.

---

## Running Tests

Tests are written using:
- `JUnit`
- `Turbine` for `Flow` assertions
- 
---
