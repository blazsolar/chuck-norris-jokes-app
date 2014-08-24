#Chuck Norris jokes app

App displays Chuck Norris joke every time you opened it.

## Setup
### Install Android SDK
```
brew install android-sdk
```

### Install Android components
```
android update sdk --all --no-ui --filter platform-tools
android update sdk --all --no-ui --filter tools
android update sdk --all --no-ui --filter build-tools-20.0.0
android update sdk --all --no-ui --filter android-20
```

### Installing app in device
```
./gradlew installDebug
```