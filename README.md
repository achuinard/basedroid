Basedroid: A Template for New Android Apps 
======================

Basedroid is a starting framework for new Android apps that servers to eliminate the boilerplate you find yourself writing in apps.

It comes packed with several frameworks and features that make Android development much simpler:

   * roboguice, a dependency injection library, comes configured.  Examples include automatic injection of 
     SharedPreferences classes, UI elements, and more.

   * Action Bar Sherlock comes packaged with Basedroid.

   * A simple HTTP client with GET / POST requests of JSON. 

   * Mosh warns the user when it has not heard from the server
     in a while.

   * Mosh supports lossy links that lose a significant fraction
     of their packets.

   * Mosh handles some Unicode edge cases better than SSH and existing
     terminal emulators by themselves, but requires a UTF-8
     environment to run.

   * Mosh leverages SSH to set up the connection and authenticate
     users. Mosh does not contain any privileged (root) code.

Configuring Basedroid
------------

  You must have a PATH variable ANDROID_HOME set to your Android SDK directory.  Basedroid is built on API 14 but is compatible with as low
    as Android 2.2.

  * [Linux][]

        export ANDROID_HOME=/my/sdk/path

Building from source
--------------------

  On a Unix-like system you can build Basedroid from source using the following
  command:

    mvn clean package

  To build Basedroid you will need

  * [Android SDK][]
  * [Maven 3.0.4][] Java build 

  You can e-mail yourself an APK to test it out on your phone.


More info
---------

  * Tony Chuinard:

    <http://chuinard.com>
