#LiliiaIskanderovaMobile
Mobile tests for native and web applications

Change "deviceName" parameter in src/test/resources/nativeTNG.xml and src/test/resources/webTNG.xml to your device name

Run native tests via "mvn clean test -P native" console command

Run web tests via "mvn clean test" or "mvn clean test -P web" console commands

Running tests in cloud:

To run tests in EPAM mobile cloud add access token to src/test/resources/environment.properties file (token property)
and add UDID number for current device to relevant *TNG.xml config:
nativeAndroidCloudTNG.xml for native Android
webAndroidCloudTNG.xml for web Android
nativeIOSCloudTNG.xml for native iOS
webIOSCloudTNG.xml for web iOS

To run native Android tests in cloud use "mvn clean test -P nativeAndroidCloud" console command
To run web Android tests in cloud use "mvn clean test -P webAndroidCloud" console command
To run native iOS tests in cloud use "mvn clean test -P nativeIOSCloud" console command
To run web Android tests in cloud use "mvn clean test -P webIOSCloud" console command
