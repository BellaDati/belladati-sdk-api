# BellaDati SDK Interface Definitions

This repository contains the interface definitions of the BellaDati SDK.

Related repositories are [belladati-sdk-java](https://github.com/BellaDati/belladati-sdk-java/) and [belladati-sdk-android](https://github.com/BellaDati/belladati-sdk-android/), containing the API implementations for standard Java and Android.

## Usage

The compiled SDK libraries are available from BellaDati: [API](http://api.belladati.com/sdk/0.9/sdk-api-0.9.0.jar), [API Javadoc](http://api.belladati.com/sdk/0.9/sdk-api-0.9.0-javadoc.jar), implementations for [standard Java](http://api.belladati.com/sdk/0.9/sdk-java-0.9.0.jar) and [Android](http://api.belladati.com/sdk/0.9/sdk-android-0.9.0.jar). To use the SDK, the API library and one of its implementations is required.

For setup instructions, dependencies, and example usage, please refer to the [BellaDati SDK documentation](http://support.belladati.com/techdoc/Java+SDK) or view the [SDK Javadoc](http://api.belladati.com/sdk/0.9/javadoc/).

## Build Instructions

A Java 6 JDK and [Apache Maven](http://maven.apache.org/) are required to build the BellaDati SDK. Maven is included in most Eclipse for Java distributions.

To prepare building the SDK, clone [this repository](https://github.com/BellaDati/belladati-sdk-api), [belladati-sdk-java](https://github.com/BellaDati/belladati-sdk-java/) and [belladati-sdk-android](https://github.com/BellaDati/belladati-sdk-android/). If you don't want to build everything, it's enough to just clone the repository you want to build.

You will need [GnuPG and a signing key](https://docs.sonatype.org/display/Repository/How+To+Generate+PGP+Signatures+With+Maven) in order to build signed jars. If you're fine with unsigned jars, you can go to each project's `pom.xml` and remove the plugin setup for `maven-gpg-plugin`.

When you're ready, call `mvn install` to build each project. Maven will create a `target` directory for each repository, containing the project's jar file and other build artifacts.
