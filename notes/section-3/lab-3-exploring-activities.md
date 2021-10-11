# 🖥 Lab: Exploring Activities
In this lab, you'll learn more about Android `Activities`, `Intents`, and the Android Studio debugger

## Objectives
- Use the Android Studio debugger to examine the `Activity` lifecycle
- Create a 2nd `Activity` to display the calculated summation of the user's entered values
- Define a factory method to create a new explicit `Intent` that starts the new `Activity`
    - This method should take in the summed value as a parameter
    - This passed value should be added as an `Intent` extra
    - This value should be parsed from the `Intent` extras
    - Display the parsed extra within the UI
- Use this new factory method to display the summation result `Activity` when the user clicks the `See Result` button