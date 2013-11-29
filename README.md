Basedroid: A Base Template for Android Apps
======================

Basedroid is a starting framework for new Android apps that helps you get all the boilerplate out of the way.

It comes packed with several frameworks and features that make Android development much simpler:

   * Butterknife, a view injection library

   * An HTTP client with GET / POST requests and JSON parsing

   * A BasedroidStateManager that uses Gson to serialize and deserialize data for easy persistence

   * Maven, a build tool and dependency manager

Build Requirements
------------
   * [Android SDK](https://developer.android.com/sdk/index.html)

   * [Maven 3.1.1](http://maven.apache.org/)

   * [Maven Android SDK deployer](https://github.com/mosabua/maven-android-sdk-deployer)


Configuring Basedroid
------------

  You must have an environment variable ANDROID_HOME set to your Android SDK directory.
  Basedroid is built on API 19 (4.4 KitKat) but is compatible with as low as API 14 (4.0 ICS).

        export ANDROID_HOME=/my/sdk/path
        git clone git://github.com/achuinard/basedroid.git

Building from source
--------------------

  After cloning the Maven Android SDK deployer and running a `mvn clean install` there,
  you can build Basedroid from source using the following command:

        mvn clean package

  You can append `-Prelease` to the build command above to sign it with the keystore included.
  Ideally you will create your own keystore as it will be easy to swap once created.

About
--------------------

I started Basedroid out of frustration with the old Android build system.  Since then, they've released a new Gradle-based system,
but I still prefer to use Maven.  Basedroid contained libraries such as Roboguice and ActionBarSherlock at one point,
but those have been removed in favor of Butterknife and setting the min API to 14 (which is >85% of Android devices at this point).

I intend to expand upon this project with a ViewPager example, some fragments, etc, but this is good enough to get going since I
delete the example activities anyway when I fork it for a new app.


More info
---------

  * Twan Software (Tony Chuinard)

    <http://twansoftware.com>
