# Secure Shared Preferences - Android

## Storing Data Securely on Android Devices

[![API](https://img.shields.io/badge/API-19%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=19)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) 
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)

### Introduction

Storing data securely is necessary. This library uses Android Keystore to generate and store cryptographic keys. This is the best and safest way. This library adds a transparent layer of security over the standard [SharedPreferences](https://developer.android.com/training/data-storage/shared-preferences#WriteSharedPreference) APIs. Values are encrypted/decrypted securely.

### Supported API's

From __Android 6.0 (API Level 23) onwards: Symmetric__ key generation and storage in the Android KeyStore is supported. It use [EncryptedSharedPreferences](https://developer.android.com/reference/androidx/security/crypto/EncryptedSharedPreferences).
From __Android 5.1 (API Level 22) downwards up to Android 4.4 (API Level 19): Asymmetric__ key generation and storage in the Android KeyStore is supported. It use [SecurePreferences](https://github.com/SoryApps/secure-storage-android).

### Usage

Add the library to your project settings.gradle:

```groovy
include ':app', ':securestoragelibrary', ':securesharedpreferences'
```

Add the library to your apps build.gradle:

```groovy
implementation project(':securesharedpreferences')
```

Get a handle to shared preferences:
```java
SharedPreferences preferences = (SharedPreferences) new SecureSharedPreferences(context, "NAME_PREFERENCES_FILE");
```

Then, use it like the [SharedPreferences](https://developer.android.com/training/data-storage/shared-preferences#WriteSharedPreference) APIs


Everything about the cryptographic keys such as generating, maintaining and usage is handled internally by the module, so you do not need to worry about it.

Note to Android L (API 22) downwards: getAll() method not supported yet. You can propose an implementation.

### Error handling
The library throws for everything a [SecurityException](https://developer.android.com/reference/java/lang/SecurityException). You can change it to [Log](https://developer.android.com/reference/android/util/Log) class.

### Want to know more:

These links cover security aspect of the android keystore:
<https://developer.android.com/training/articles/keystore.html#SecurityFeatures>
<https://source.android.com/security/keystore/>
<https://codingquestion.blogspot.de/2016/09/how-to-use-android-keystore-api-with.html>
<http://nelenkov.blogspot.de/2012/05/storing-application-secrets-in-androids.html>
<http://nelenkov.blogspot.de/2015/06/keystore-redesign-in-android-m.html>
<http://www.androidauthority.com/use-android-keystore-store-passwords-sensitive-information-623779/>  

This link covers security aspect of the android storage:
<https://developer.android.com/guide/topics/data/data-storage.html>
<http://stackoverflow.com/a/26077852/3392276>

### License:
-------
    Copyright (C) 2019 SoryApps
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       https://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

