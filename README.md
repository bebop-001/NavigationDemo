    * Initial draft: Sun May  5 13:55:22 PDT 2019

###About
This app is meant to demonstrate Kotlin, Data-Binding and the Navigation package.  The initial version only implements Data-Binding with a button listener and a text view.

It was written while taking a Udacity course on programming in Kotlin for Android.  At one point I decided to try and implement some of the concepts taught in the class.  After spinning my wheels for about a week I discovered that my problem was that there were changes to gradle that I don't remember covering in the course.

I tried to set up git so it's easy to see what is changed at each step along the way.  What I'd suggest is to clone the repository to a local directory.  Using 'git log' you can see the message for each commit, use 'git diff --name-status' to see what files were changed between commits, and then use git diff on the files to see what changed between files.

**Note:** you need at least AndroidStudio 3.4, Kotlin plugin 1.3.31 and Gradle 5.11 for this app.

This is what I've done so far:

1. Create an initial kotlin android application with an empty activity.  By default this will give you a MainActivity.kt and an activity_main.xml layout.  I removed the android test directories.  This app isn't going to do enough to make their use worth while.
2. Make the basic changes to gradle, MainActivity and activity_main.xml for the basic activity to compile.  This includes adding the <layout> tag to the layout file.  It also involded replacing the constraint-layout (which doesn't support data-binding) with a simple LinearLayout.
  
> After you have completed this step you should see a new package under Android->app->GeneratedJava which will contain a class called ActivityMainBinding.  ActivityMainBinding is a name-mangled version of activity_main and if you examine it you will see static public declarations for each of the view objects in activity_main that are name-mangled versions of their resource ID.  This is the magic of name-mangling: you no longer need to go through the process of finding each view using 'findViewById' and you get compile-time handles for each view object rather than waiting for runtime handles.

3.  Add a button listener to the MainActivity.  Here you will see the use of data-binding in the onCreate method.