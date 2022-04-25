# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]


## [0.0.0] - 2022-02-18
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

## [Unreleased]


## [0.0.0] - 2022-03-20
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
