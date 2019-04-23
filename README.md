#### Kotlin + JavaFX + Spring Framework 5
This repo will include sample applications using **Spring Framework 5** with desktop **JavaFX** applications written in **Kotlin**.

The goal is to provide demo applications, which work out of the box. If they don't work - let me know! I hate demos that don't work.

The application can be run from the command line without an IDE, as long as the required software is installed (see Install section).


#### Philosophy
All the code samples are not meant to be production quality and I use the following guidelines:

* Enough unit tests to illustrate a point or technique
* Logging, exception and error handling is avoided, unless it’s specifically a focus of the sample
* Longer variable names to assist in clarity
* Shorter classes and methods to minimise TL;DR syndrome.


My blog on JavaFx with Kotlin at [https://thickclient.blog/](https://thickclient.blog/)

### Install
You will need to install:

* Java 11 or 12 SDK (Oracle or Open JDK)
* [OpenJFX 12](https://gluonhq.com/products/javafx/)

If you want to edit FXML and keep your sanity:

* [SceneBuilder for Java 11](https://gluonhq.com/products/scene-builder/)


#### Dependency Management and Build - Gradle

Gradle is bundled as part of the project - no need to install anything.

**Note:** Remember the path of the OpenJFX install. I will refer to this as the **JFX_INSTALL** dir.

---
### Configuration

*The projects does NOT need Gradle to be installed as this is included inside the project via a gradle wrapper*


##### Set JFX_INSTALL variable in build.gradle file

Find the section in the build.gradle:

    
    // BEGIN YOUR SETUP

    def JFX_INSTALL =  ** YOUR JFX_INSTALL DIR HERE ** 

    // END YOUR SETUP

and update the JFX_INSTALL value with your OpenJFX install directory.

---
### Build

Build the applications with gradle:

    gradlew clean build


Run all the unit and integration tests

    gradlew test
    

## Run

to run:

    gradlew runApp

     
