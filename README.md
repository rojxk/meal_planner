# Meal Planner Application - Database Integration

## Overview
The Meal Planner Application is a Java-based application designed to help users effectively manage their meal planning with the integration of a PostgreSQL database. This branch (`database`) includes features that allow for persistent storage of meals, ingredients, and user data, enhancing the application's functionality over the in-memory version found in the `main` branch.

## Features
- **User Management**: Manage user data in the database.
- **Meal Management**: Add, remove, and list meals with persistent storage.
- **Ingredient Tracking**: Add ingredients to meals and manage them through the database.
- **Dynamic Data Interaction**: Use prepared statements to interact dynamically with the database.
- **Error Handling**: Robust error handling for database operations.

## Prerequisites
- Java JDK 11 or later.
- PostgreSQL database server running locally or remotely.
- Maven for managing project dependencies and executions.

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/meal-planner.git
   cd meal-planner
   git checkout database
