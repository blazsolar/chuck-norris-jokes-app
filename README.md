#Chuck Norris jokes app

App displays Chuck Norris joke every time you opened it.

[![Build Status](https://travis-ci.org/blazsolar/chuck-norris-jokes-app.svg?branch=master)](https://travis-ci.org/blazsolar/chuck-norris-jokes-app)
[![Coverage Status](https://coveralls.io/repos/blazsolar/chuck-norris-jokes-app/badge.png)](https://coveralls.io/r/blazsolar/chuck-norris-jokes-app)

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

## License

    The MIT License (MIT)
    
    Copyright (c) 2014 Blaž Šolar
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.