    * Initial draft: Sun May  5 13:55:22 PDT 2019

###About
This app is meant to demonstrate Kotlin, Data-Binding and the Navigation package.  The initial version only implements Data-Binding with a button listener and a text view.

It was written while taking a Udacity course on programming in Kotlin for Android.  At one point I decided to try and implement some of the concepts taught in the class.  After spinning my wheels for about a week I discovered that my problem was that there were changes to gradle that I don't remember covering in the course.

I tried to set up git so it's easy to see what is changed at each step along the way.  What I'd suggest is to clone the repository to a local directory.  Using 'git log' you can see the message for each commit, use 'git diff --name-status' to see what files were changed between commits, and then use git diff on the files to see what changed between files.

**Note:** you need at least AndroidStudio 3.4, Kotlin plugin 1.3.31 and Gradle 5.11 for this app.

This is what I've done so far:

1. Create an initial kotlin android application with an empty activity.  By default this will give you a MainActivity.kt and an activity_main.xml layout.  I removed the android test directories.  This app isn't going to do enough to make their use worth while.
2. Make the basic changes to gradle, MainActivity and activity_main.xml for the basic activity to compile.  This includes adding the <layout> tag to the layout file.  It also involded replacing the constraint-layout (which doesn't support data-binding) with a simple LinearLayout.
  
    After you have completed this step you should see a new package under Android->app->GeneratedJava which will contain a class called ActivityMainBinding.  ActivityMainBinding is a name-mangled version of activity_main and if you examine it you will see static public declarations for each of the view objects in activity_main that are name-mangled versions of their resource ID.  This is the magic of name-mangling: you no longer need to go through the process of finding each view using 'findViewById' and you get compile-time handles for each view object rather than waiting for runtime handles.

3.  Add a button listener to the MainActivity.  Here you will 
see the use of data-binding in the onCreate method.

4.  Add an "About" menu item that will display build timestamp, branch, build type, etc.

    * Changed app/build.gradle to add info to BuildConfig.
    * created res/menu/menu_main.xml.
    * Added menu inflator and listener callback to MainActivity.
    * Added an AlertDialog to MainActivity for displaying the menu info.
    * Create an app/src/main/res/navigation directory and add to it a navigation.xml resource file.  The NavigationEditor (later) will save it's actions here.

5.  Create fragments for navigation.

    * Add implementation dependencies for navigation-fragment-ktx and navigation-ui-ktx to the app/build.gradle.
    * Add fragments for navigation demo.  For each of these, create both a ...Frag.kt fragment class and ...frag_.xtml layout file.  Each will have one or two buttons to demonstrate data-binding for callbacks and navigation.  You have to add the <layout> wrapper to each of the xml files to enable data-binding.

        * RootFrag.kt and root_frag.xml has two buttons with id's: to_upper_frag_btn and to_lower_frag_btn
        * UpperFrag.kt and upper_frag.xml which has one button with an id of to_top_from_upper_button.
        * LowerFrag.kt and lower_frag.xml which has one button: to_top_from_lower_btn
        * TopFrag and top_frag.xml which has one button: to_root_btn.

    * Compile the code.  This will fail for the *Fragment.kt files but will generate the *FragBinding.java files for data-binding.
    * Edit each of the Frag.kt files.  Your goal is to get the onCreateView method working and hook up the basic data-binding code.  We aren't worried about the button event handlers yet.  When everything compiled I checked in the code.
    * Open the res/navigation/navigation.xml layout.  In design mode this will bring up a graphic Navigation Editor that is used to define the action relationships for each fragment.
.
        * In the upper-left of the Navigation Editor there is a small icon that looks like a phone.  Use this to add each of your 4 fragments.
        * Drag them to appropriate locations.
        * use the graphic editor to "connect the dots".  **Note** that the **"Pop Behavior"** has a checkbox that says "Inclusive.  **Inclusive means "pop until you find the fragment and pop that too."  Non-inclusive means "pop until you find the fragment, then stop."**  Define these action relationships:

            * root -> upper frag
            * root -> lower frag
            * upper frag -> top frag
            * lower frag -> top frag
            * top frag -> root frag
 ![NavEditor Image](snapshots/NavEditor)

        * This is how the Navigation Editor looked to me at this point.  **Yours may look different.**  The layout for the editor is in .idea/navEditor.xml which is not under git control.

6.  Next we will add onClick listeners to each of the fragments using the NavGraph created by the navigation editor.  If you look at res/navigation/navigaton.xml we created, you will find it now contains a <Fragment> for each of the four fragments we added.  Each <Fragment> contains an action or actions that were defined by the connections were made.  Syntax for the resource id for the action is "action_<Fragment>_to_<destination Fragment>.  For instance the action from the rootFrag to the upperFrag is "action_rootFrag_to_upperFrag".  

> We have two ways we can use setOnClickListener.  As far as I can tell, it doesn't make much difference which is used.  The thing to note is that both use the navigate method which is of note because this method deals with adding/removing from the  back stack which means that we can use the back arrow without any further coding.

        * assign a lambda:
          view -> view.findNavController().navigate(action resource id)
        * use the Navigate class to create our onClick:
          Navigation.createNavigateOnClickListener(action_resource_id, null)

7. Enable the "up" button in the action bar.  In theory, this is simple:  all you have to do is over ride the onSupportNavigateUp method.  **HOWEVER**  -- there is a possible got-ya here.  We added a menu item above.  When you implement the "up" button "->" is pressed, for some reason the onOptionsItemSelected method is called.  If you implemented this to return true, the onSupportNavigationUp method will never be called.  **You must implement the <u>onOptionsItemSelected</u> method to return false if it doesn't recognize the menu item passed in or onSupportNavigationUp will never be called and the up button will not work!**

8. Extend menu to fragments:  This turned out to be easier than expected.  I created a res/menu/menu_&lt;frag name&gt;.xml for each fragment and over rode onCreateOptionsMenu and onOptionsItemSelected in each fragment class as well as adding setHasOptionsMenu(true) to each Fragment class' onCreateView.  There was already an onCreateOptionMenu aond onOptionsItemSelected method in the MainActivity class.  Then each class checked the item id passed into the onOptionsItemSelected and if the item wasn't for the class, it called it's super class.  As I had hoped, the activity and fragments each received their items.
