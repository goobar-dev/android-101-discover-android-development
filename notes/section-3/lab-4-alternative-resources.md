# 🖥 Lab: Configuration Changes & Resources
In this lab, you'll explore configuration changes by providing a custom layout for landscape orientation

## Objectives
- Create a new Android Resource Directory for landscape orientation
    - Right click `res` directory
    - Select _New_ -> _Android Resource Directory_
    - Change _Resource Type_ to _Layout_
    - Add _Orientation_ to the selected qualifiers list by highlighting it and clicking the >_>_> button
    - Change _Screen Orientation_ to _Landscape_
    - Click _OK_
- Create a new landscape layout for `MainActivity`
    - Right click _res/layout-land_
    - Select _New_ -> _Layout Resource File_
    - Enter _activity_main_ as the resource name
- Copy your existing _activity_main.xml_ code into the new layout file
- Adjust the `LinearLayout` to use _landscape_ orientation rather than the default _portrait_
    - Be sure to keep the ids of our Views the same
- Deploy your app to your emulator and observe how the UI changes when the orientation changes