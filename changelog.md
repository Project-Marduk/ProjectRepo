# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Released]


## [1.0.0] - 2022-02-18
### Added
- Our Project Setup Documentation, imcluding:
  - Read Me
  - Changelog
- Our Project Board with the basic set up.

### Changed
- Switch the project repo from private to public, both because it doesn't need to be private, and be cause wee need the wiki feature for free.

### Removed
- Nothing yet to removed a bad project board I made. Leaving this note as an example fore future change log entries.

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

## [Released]


## [2.0.0] - 2022-03-20
### Added
- A login page for users to login (currently doesn't need a username for develop purposes until released)
- A create username page so the user can create an account with a new valid username that isn't taken yet
- A design page so the user can start creating their diagrams (currently doesn't support all shapes and modifying shapes until a release is ready)
- FXController now has a way to implement basic shapes using javafx.shapes 
  - Square
  - Circle
  - Line
- Mouse events inside of FXcontroller now enables users to move shapes around
- Jacoco testing inside of the desktop client 
- JUnit testing is set up and ready (testing is currently in progress for the unreleased features above)
- Documentation inside of Desktop Client 

### Changed
- Build files in order to allow for proper building inside of Desktop Client so testing is allowable
- Build.gradle
- Packages
- Padding on the login and create user page to allow for uniformity across all machines

### Removed
- The basic default packages inside of Desktop Client (replaced with new ones)
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

## [Released]


## [3.0.0] - 2022-03-20
### Added
- Desktop Client
  - TestFX Application Testing
  - Hamcrest JavaFX Assert Testing 
  - DesktopClientTest package inside of it's own Test module
  - Test resource path in order to test FXML files without touching the actual FXML files

### Changed
- Desktop Client
-   Login.FXML to include more javaFX layouts and specify fx controller
-   CreateUser.FXML to include more javaFX layouts and specify fx controler
-   Design.FXML to add special fx:ID's to each element
-   Build.gradle in order to add testfx dependency and hamcrest dependency
-   Cache information in order to build dependencies properly
-   Refactored testing file names and pathing
-   FXController is now ready for the SVG pathing and shapes instead of using JavaFX shapes

### Removed
- Desktop Client
  - First take of testing classes that aren't needed
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

## [Released]


## [4.0.0] - 2022-04-24
### Added
- Desktop Client
  - Tree view actions in order to create shapes based on what shape the user wants to add
  - Make shapes selectable allows for resizing of basic javafx shapes
  - Generate svg paths from shape objects
  - Input objects, drawing object, and drawing board to contain shapes to user inserts into the system
  - Width, height, color, and x y coordinates are available for the user to type in to create specific shapes
  - Basic dimensions are created if the user doesn't insert values for their shape they want to insert
  - API calls 
  - Backend database

### Changed
- Desktop Client
  - FXController to use input objects and drawing objects instead of javafx.shapes
  - Design.fxml to contain the new tree view layout on the left hand side
  - Shape attributes will now be allocated with input object attributes instead of javafx.shapes attributes

### Removed
- Desktop Client
  - Shape attributes using javafx 
  - Inserting shapes with javafx shapes
  - First take of testing classes that aren't needed
