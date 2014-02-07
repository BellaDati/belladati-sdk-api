# BellaDati SDK

This repository contains the main part of the BellaDati SDK; its interface definitions and Maven parent project.

Related repositories are [belladati-sdk-java](https://github.com/BellaDati/belladati-sdk-java/) and [belladati-sdk-android](https://github.com/BellaDati/belladati-sdk-android/), containing the API implementations for standard Java and Android.

## Usage

The compiled SDK libraries are available from BellaDati: [API](http://api.belladati.com/sdk/0.9/sdk-api-0.9.0.jar), [API Javadoc](http://api.belladati.com/sdk/0.9/sdk-api-0.9.0-javadoc.jar), implementations for [standard Java](http://api.belladati.com/sdk/0.9/sdk-java-0.9.0.jar) and [Android](http://api.belladati.com/sdk/0.9/sdk-android-0.9.0.jar). To use the SDK, the API library and one of its implementations is required.

For setup instructions, dependencies, and example usage, please refer to the [BellaDati SDK documentation](http://support.belladati.com/techdoc/Java+SDK) or view the [SDK Javadoc](http://api.belladati.com/sdk/0.9/javadoc/).

## Build Instructions

A Java 6 JDK and [Apache Maven](http://maven.apache.org/) are required to build the BellaDati SDK. Maven is included in most Eclipse for Java distributions.

To prepare building the SDK, clone [this repository](https://github.com/BellaDati/belladati-sdk-main), [belladati-sdk-java](https://github.com/BellaDati/belladati-sdk-java/) and [belladati-sdk-android](https://github.com/BellaDati/belladati-sdk-android/) into the same parent folder.

The API declaration and its implementations are separate Maven modules that are part of the same Maven project. The project is declared in the folder `root` in this repository. The modules are defined in `api` and at the top level of the implementation repositories.

In the `root` folder of this repository, call `mvn install`. This will tell Maven to build the entire project including the API and both implementations.

As a result, Maven creates a `target` directory in each module, containing the module's jar file and other build artifacts.
