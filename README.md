# ChessProject

A Java-based chess game implementation focusing on clean architecture, object-oriented design principles, and testability.

## 🧠 Project Overview

This project started as an MVC-structured application and has since been transformed into a Maven project with a clean separation of responsibilities. The goal was to enhance code maintainability, readability, and testability by applying proper software design principles.

## ✨ Key Improvements

### ✅ Code Refactoring
- **Separated Responsibilities:** The original codebase violated several OOP principles. Classes like `Board` and `Piece` contained multiple responsibilities (e.g., UI logic, game logic). These were refactored to adhere to the **Single Responsibility Principle**.
- **Mouse Interaction Handling:** Moved mouse-related logic from the `Board` class into a dedicated class responsible for handling mouse actions.
- **Piece Movement Separation:** Movement logic was separated from the `Piece` models. The **Strategy Pattern** was applied to define unique movement rules for each piece type (e.g., `RookMovement`, `KnightMovement`, `QueenMovement`, etc.).
- **Factory Pattern & Enums:** A **PieceFactory** and a `PieceType` `enum` were introduced to improve clarity and streamline piece creation.
- **JUnit Tests:** Comprehensive unit tests were written using **JUnit** to verify allowed moves for different pieces, covering edge cases (e.g., movement from corners, blocked paths, etc.).

### ♟️ Game Logic
- **Refactored Checkmate Logic:** The check/checkmate logic was cleaned and partially refactored, though further improvements were planned.

## 🧪 Testing
Tests cover:
- All possible movements for individual pieces.
- Edge cases like movement from board edges or corners.
- Valid/invalid movements depending on board state (e.g., blocked by friendly/enemy pieces).


## 📁 Project Structure (Post-Refactor)
