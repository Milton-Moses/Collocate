# Collocate - Project Setup Guide

This guide will walk you through the steps required to set up the Collocate project for local development.

## Prerequisites

Before you begin, ensure you have the following software installed on your machine:

* **Git**: For cloning the repository.
* **Java Development Kit (JDK)**: Version 17 or higher is recommended.
* **An IDE**: A Java IDE such as IntelliJ IDEA or Eclipse.
* **Firebase Account**: You will need a Firebase project to handle the backend and database.
* **Google AI API Key**: You will need a Gemini API key for the AI assistant features.

## Installation Steps

1.  **Clone the Repository**
    Open your terminal or command prompt and run the following command to clone the project:
    ```bash
    git clone [https://github.com/Milton-Moses/Collocate.git](https://github.com/Milton-Moses/Collocate.git)
    cd Collocate
    ```

2.  **Set Up Firebase**
    * Go to the [Firebase Console](https://firebase.google.com/) and create a new project.
    * Within your project, set up **Cloud Firestore** as your database.
    * You will need to obtain your Firebase configuration credentials to allow the Java application to connect to your project. Follow the Firebase documentation to generate a private key file for a service account.
    * Place the downloaded JSON credentials file in a secure location in the project's resource directory. **Important**: Do not commit this file to Git. Make sure to add its name to your `.gitignore` file.

3.  **Configure API Keys**
    * Go to the [Google AI Studio](https://ai.google.dev/) to get your Gemini API key.
    * The application will look for this key in an environment variable. For security, it's highly recommended you **do not hardcode the key** in the source code.
    * Set an environment variable named `GEMINI_API_KEY` with your key's value.
        * **On Windows:** `setx GEMINI_API_KEY "YOUR_API_KEY"`
        * **On macOS/Linux:** `export GEMINI_API_KEY="YOUR_API_KEY"`

4.  **Build and Run the Application**
    * Open the cloned project directory in your IDE (e.g., IntelliJ IDEA).
    * Your IDE should automatically detect the project structure. Allow it to download any necessary dependencies.
    * Locate the main application class (e.g., `com.collocate.MainApp`) and run it. The JavaFX application should launch.

## Troubleshooting

* **JavaFX libraries not found**: Ensure your IDE is configured correctly for JavaFX development. You may need to add the JavaFX SDK as a global library and include its modules in the VM options for your run configuration.
* **Firebase Connection Error**: Double-check that your Firebase credentials file is correctly placed in the project and that the path in the code points to it. Ensure the service account has the necessary permissions in the Firebase IAM settings.
