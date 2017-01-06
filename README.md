# Example android that uses several functionalities
Example that will address the following Android API functionalities.

  - Manage (Save, view, modify) data in SQLite.
  - Use of own library in aar file in an activity.
  - Creation of image gallery.
  - Using Preferences with a Custom Toolbar.
  - Created layout to display rating scores.
  - Using the intent-filter to open an activity from a URL that is shared (if the app is not installed, it redirects to a web page)

Instructions for adding a new library (aar) in an Android project to make use of it in an activity:

### Follow this steps:

`Step 1: Import .aar`

File ---> New ---> New Module ---> (select) import .JAR/.AAR package ---> Next --->(select .aar file then)---> Finish

Now your existing project is imported.

`Step 2: Add dependencies`

File ---> Project Structure ---> (Now you will get module list in left side at bottom.) ---> (Select app module) ---> select dependencies tab ---> Click on (+) button ---> Select module dependencies ---> (select module which you added) ---> OK ---> OK

Add screens to 
[![Step2_a](https://github.com/mugan86/android-sqlite/blob/master/screens/1.png?raw=true)](https://github.com/mugan86/android-sqlite)
[![Step2_a](https://github.com/mugan86/android-sqlite/blob/master/screens/2.png?raw=true)](https://github.com/mugan86/android-sqlite)
[![Step2_a](https://github.com/mugan86/android-sqlite/blob/master/screens/3.png?raw=true)](https://github.com/mugan86/android-sqlite)

### Note: To check dependency is added

```sh
dependencies {
    compile fileTree(include: ['*.jar'], dir: 'libs')
    compile 'com.android.support:appcompat-v7:25.1.0'
    compile 'com.android.support:design:25.1.0'
    compile 'com.google.code.gson:gson:2.4'
    compile 'com.squareup.picasso:picasso:2.5.2'
    compile 'com.android.support:cardview-v7:25.1.0'
    compile 'com.android.support:support-v4:25.1.0'
    compile 'com.android.support:support-vector-drawable:25.1.0'
    testCompile 'junit:junit:4.12'
    compile project(':mylibrary')
}
