# BrailleVision

**BrailleVision** is an intelligent assistive technology pipeline that leverages hardware-accelerated computer vision and topological pattern recognition to decode tactile Braille matrixes in real-time. Driven by OpenCV and a modular JavaFX architecture, it seamlessly orchestrates live stream ingestion, spatial feature extraction, algorithmic translation, and synthesized auditory feedback to bridge the tactile-to-digital divide.

## Features
- **Camera Integration**: Capture images of Braille text in real-time.
- **Computer Vision**: Process images to detect and extract Braille dot patterns.
- **Braille Translation**: Accurately translate Braille patterns into standard readable text.
- **Text-to-Speech (TTS)**: Convert the translated text into spoken audio.
- **Graphical User Interface**: A modern and user-friendly interface built with JavaFX.

## Tech Stack
The project is built using the following technologies:
- **Language**: Java 21
- **Build Tool**: Maven
- **UI Framework**: JavaFX (v21.0.1)
- **Computer Vision**: OpenCV (v4.9.0-0)
- **JSON Parsing**: Jackson Databind (v2.16.1)

## Architecture Overview
The application is structured into modular components:
- `camera`: Manages camera hardware interaction and image capture.
- `vision`: Handles image processing and Braille dot detection using OpenCV.
- `translator`: Maps detected Braille patterns to corresponding text characters.
- `speech`: Manages Text-to-Speech capabilities for the translated text.
- `ui`: Contains the JavaFX views, controllers, and application entry point.
- `model`: Defines the data models used across the application.
- `utils`: Helper functions and common utilities.

## Prerequisites
To run this project locally, ensure you have the following installed:
- [Java Development Kit (JDK) 21](https://jdk.java.net/21/)
- [Apache Maven](https://maven.apache.org/download.cgi)

## How to Run
1. Clone the repository to your local machine.
2. Navigate to the project directory:
   ```bash
   cd Braillevision
   ```
3. Compile and build the project using Maven:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn javafx:run
   ```

## Troubleshooting / Known Issues

### OpenCV and JavaFX Warnings
When running the application using `mvn javafx:run`, you may encounter the following output in your console. This is expected and can be safely ignored:

![Console Output](screenshot.png)

The JavaFX warnings are related to the module system restrictions in newer Java versions. The OpenCV warning indicates that the library is correctly handling compatibility by falling back to a local load method, allowing the application to function normally.

## License
[Add your license here]
