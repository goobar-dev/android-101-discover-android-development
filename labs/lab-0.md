# 🖥 Lab 0: Setting Up Your Android Development Environment
In this lab, you'll install Android Studio, and setup your Android development environment so you're ready to build your first Android app.

## Objectives
- Install Android Studio
- Use the Android SDK Manager to install the latest Android SDK
- Use AVD to create a new Android emulator
- Prepare a device for app debugging


## 💡 Helpful Resources

### 💡 Installing the JDK
AdoptOpenJDK is a great option
- [Download Site](https://adoptopenjdk.net/)
- [via Brew](https://github.com/AdoptOpenJDK/homebrew-openjdk)

For Android Studio projects created with versions of Android Studio 4.2+ you'll need to choose a version of JDK 11+ to be compatible with the Android Gradle Plugin.

To ensure compatability/stability, use a version between JDK 11-15.

### 💡 Exporting JAVA_HOME
https://docs.opsgenie.com/docs/setting-java_home

#### Exporting JAVA_HOME on Unix Systems
- Locate your shell startup script
- likely something similar to `.bashrc`, `.bash_profile`, `.zshrc`
- Set the `JAVA_HOME` variable as follows
```
# JDK Setup
export JAVA_HOME=<JDK Path>/Contents/Home
export PATH=$JAVA_HOME:$PATH
```

** If installing AdoptOpenJDK on a Mac via Brew, your JDK Path likely looks something like
`/Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk`

#### Exporting JAVA_HOME on Windows 10
- Open `Command Prompt` as Administrator
- Set the `JAVA_HOME` variable as follows
```
setx -m JAVA_HOME "C:\Progra~1\Java\<JDK Version>"
```

### 💡 Exporting ANDROID_HOME
Setting `ANDROID_HOME` will be very similar to setting `JAVA_HOME`

```
export ANDROID_HOME=/Users/<your user>/Library/Android/sdk
export PATH=$ANDROID_HOME/platform-tools:$PATH
export PATH=$ANDROID_HOME/tools:$PATH
```

On Mac, the path to the SDK likely looks something like `/Users/<your user>/Library/Android/sdk`

### 💡 Invoking the Gradle Wrapper

#### On Mac/Linux
- From the terminal, navigate to the root directory of your Android Studio project
- run `./gradlew <task name>`

To test this, you can run the simplest Gradle task; the `help` task `./gradlew help`

#### On Windows
- From the terminal, navigate to the root directory of your Android Studio project
- run `gradle.bat <task name>`

To test this, you can run the simplest Gradle task; the `help` task `gradle.bat help`

### 💡 I'm getting a "permission denied" error when invoking Gradle Wrapper
If you get a "permission denied" error when trying to execution the Gradle Wrapper, try making the file executable.
Sometimes this gets reset and you need to reset the permissions.

`chmod +x gradlew`