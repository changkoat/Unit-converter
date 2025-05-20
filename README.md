Below is the content of the README.md file explaining the project and its features:
markdown# Unit Converter

## Overview
A Java application for converting between different units of measurement including length, weight, temperature, volume, area, speed, time, and digital storage.

## Features
- Support for multiple conversion categories (length, weight, temperature, etc.)
- Real-time conversion as the user types
- Intuitive user interface with dropdown selection for units
- Accurate conversion with appropriate decimal precision
- Ability to copy conversion results to clipboard
- Conversion history tracking
- Favorite/recent conversions for quick access

## Technologies Used
- Java version 11
- JavaFX for UI components
- JUnit 5 for testing
- Maven for dependency management

## Setup and Installation
1. Clone the repository
2. Ensure you have JDK 11+ installed
3. Run `mvn clean install` to build the project
4. Execute `mvn javafx:run` to start the application

## Usage
1. Select a conversion category from the main menu (Length, Weight, etc.)
2. Choose your source unit and target unit from the dropdown menus
3. Enter a value to convert in the input field
4. View the converted result in real-time
5. Use the "Copy" button to copy the result to your clipboard

## Testing
The project includes comprehensive unit tests for the conversion logic and controller functionality. Run tests using `mvn test`.

## Future Improvements
- Support for currency conversion with API integration
- Custom unit creation
- Dark mode theme
- Mobile application version

## Contributors
- Chang Koat
Key Features Implemented
The following features have been implemented in this Unit Converter project:

Multi-category Unit Conversion: The application supports conversion between various unit categories.

Technical highlights: Implemented a flexible system to easily add new categories and units
Challenges overcome: Handling different conversion algorithms (linear vs. non-linear conversions)


Real-time Conversion: Values update instantly as users type.

Technical highlights: Implemented property listeners to update conversion results
Challenges overcome: Ensuring good performance with rapid input changes


Robust Input Validation: The application validates all user inputs.

Technical highlights: Created a custom input validation system
Challenges overcome: Balancing user experience with strict validation requirements



Testing Strategy
The project includes the following types of tests:

Unit Tests: Testing individual conversion algorithms and unit definitions
Integration Tests: Testing the interaction between the conversion engine and the controller
System Tests: Testing the application flow from UI input to displayed results

Test coverage focuses on conversion accuracy, proper handling of edge cases (very large/small numbers), and validation of user inputs.
