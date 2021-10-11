# 🖥 Lab: Exploring Activities
In this lab, you'll learn more about Android `Activities`, `Intents`, and the Android Studio debugger

## Objectives
- Use the Android Studio debugger to examine the `Activity` lifecycle
- Use `Log.d()` to add log messages to key `Activity` lifecycle methods
- Create a 2nd `Activity` to display the calculated summation of the user's entered values
    - Be sure to add a new `AndroidManifest.xml` entry for this new `Activity`
    - Android Studio will do this for you if you use the Activity creation dialog
- Define a factory method to create a new explicit `Intent` that starts the new `Activity`
    - This method should take in value1, value2, and the summed value as parameters
    - These values should be added as a `Intent` extras
    - These values should be parsed from the `Intent` extras
    - Display the parsed extras within the UI
- Use this new factory method to display the summation result `Activity` when the user clicks the `See Result` button