# Mendix World 2.0 - Mendix Face Detection & Blurring
This is an Example Project with Java Actions to do face detection and blurring that is presented at Mendix World 2.0 in more detail, check it out: https://www.mendix.com/mendix-world/

# Pre-requisites & Setup
## Google Cloud Project with access to the Google Vision API 

You will need a project at https://console.cloud.google.com/ 

Make sure that billing is enabled: https://support.google.com/googleapi/answer/6158867

### Create a Service Account for the project

![alt text](https://mxw2.s3.eu-central-1.amazonaws.com/ServiceAccount.png "Create a Service Account")

### Select the role

Now select the owner role to make sure we have enough access, if you need to restrict access, see: https://cloud.google.com/iam/docs/granting-changing-revoking-access 

![alt text](https://mxw2.s3.eu-central-1.amazonaws.com/ServiceAccount2.png "Select the role")

Once you have selected the role click **Continue**, the third step is optional so you can click **Done**

![alt text](https://mxw2.s3.eu-central-1.amazonaws.com/ServiceAccount3.png "Open new Service Account")

Open the newly created Service Account by clicking on it

### Create a Access Key
Next for the Service Account in the JSON format (this will download a .json file you need to keep safe

![alt text](https://mxw2.s3.eu-central-1.amazonaws.com/ServiceAccount4.png "Create Key")

### Enable the Google Vision API

Once we have the key, lastly we need to  go to **APIs & Services**

Click on **ENABLE APIS AND SERVICES**

![alt text](https://mxw2.s3.eu-central-1.amazonaws.com/API1.png "Enable APIS & Services")

Type in "Vision" and click on the "Cloud Vision API" option

![alt text](https://mxw2.s3.eu-central-1.amazonaws.com/API2.png "Search Cloud Vision API")

Click **ENABLE** to enable the API, this might take a minute

![alt text](https://mxw2.s3.eu-central-1.amazonaws.com/FaceGCP.gif "Click Enable")

# How to Use

## Download project

```git clone https://github.com/mxcrawford/mx-face-detect-and-blur.git```

## Import Google Vision .jars to your userlib folder using ivy

```cd mx-face-detect-and-blur/```

### Make sure you a Java JRE or JDK in your Windows Path

```
echo %$PATH%
output should contain something like: C:\Program Files\AdoptOpenJDK\jdk-11.0.3.7-hotspot
```

## Run the import command

```runivy.cmd```

Output:

```
java -jar build\apache-ivy-2.4.0\ivy-2.4.0.jar -settings ivysettings.xml -ivy ivy.xml -retrieve "userlib/[artifact]-[revision].[ext]"
:: loading settings :: file = ivysettings.xml
:: resolving dependencies :: com.mendix#mxawsconnectors;working@S014688192857
        confs: [default, export]
        found com.google.cloud#libraries-bom;8.0.0 in central
        found com.google.cloud#google-cloud-vision;1.99.3 in central
        found io.grpc#grpc-api;1.28.1 in central
        found io.grpc#grpc-context;1.28.1 in central
        found com.google.errorprone#error_prone_annotations;2.3.4 in central
        found com.google.code.findbugs#jsr305;3.0.2 in central
        found org.codehaus.mojo#animal-sniffer-annotations;1.18 in central
        found com.google.guava#guava;28.2-android in central
        found com.google.guava#failureaccess;1.0.1 in central
        found com.google.guava#listenablefuture;9999.0-empty-to-avoid-conflict-with-guava in central
        found org.checkerframework#checker-compat-qual;2.5.5 in central
        found com.google.j2objc#j2objc-annotations;1.3 in central
        found io.grpc#grpc-stub;1.28.1 in central
        found io.grpc#grpc-protobuf;1.28.1 in central
        found com.google.api.grpc#proto-google-common-protos;1.17.0 in central
        found io.grpc#grpc-protobuf-lite;1.28.1 in central
        found com.google.api#api-common;1.9.0 in central
        found javax.annotation#javax.annotation-api;1.3.2 in central
        found com.google.auto.value#auto-value-annotations;1.7 in central
        found com.google.api.grpc#proto-google-cloud-vision-v1p4beta1;0.82.3 in central
        found com.google.api.grpc#proto-google-cloud-vision-v1;1.81.3 in central
        found com.google.api.grpc#proto-google-cloud-vision-v1p1beta1;0.82.3 in central
        found com.google.api.grpc#proto-google-cloud-vision-v1p3beta1;0.82.3 in central
        found com.google.api.grpc#proto-google-cloud-vision-v1p2beta1;1.81.3 in central
        found com.google.api#gax;1.56.0 in central
        found org.threeten#threetenbp;1.4.3 in central
        found com.google.auth#google-auth-library-oauth2-http;0.20.0 in central
        found com.google.auth#google-auth-library-credentials;0.20.0 in central
        found com.google.http-client#google-http-client;1.34.0 in central
        found org.apache.httpcomponents#httpclient;4.5.10 in central
        found org.apache.httpcomponents#httpcore;4.4.12 in central
        found commons-logging#commons-logging;1.2 in central
        found commons-codec#commons-codec;1.11 in central
        found io.opencensus#opencensus-api;0.24.0 in central
        found io.opencensus#opencensus-contrib-http-util;0.24.0 in central
        found com.google.http-client#google-http-client-jackson2;1.34.0 in central
        found com.fasterxml.jackson.core#jackson-core;2.10.1 in central
        found com.google.api-client#google-api-client;1.30.10 in central
        found com.google.oauth-client#google-oauth-client;1.31.0 in central
        found com.google.http-client#google-http-client;1.36.0 in central
        found org.apache.httpcomponents#httpclient;4.5.12 in central
        found org.apache.httpcomponents#httpcore;4.4.13 in central
        found commons-codec#commons-codec;1.14 in central
        found com.google.guava#guava;29.0-android in central
        found com.google.http-client#google-http-client-jackson2;1.36.0 in central
        found com.fasterxml.jackson.core#jackson-core;2.11.1 in central
        found com.google.protobuf#protobuf-java;3.12.2 in central
        found com.google.api#gax-grpc;1.57.1 in central
        found com.google.api#gax;1.57.1 in central
        found com.google.auth#google-auth-library-oauth2-http;0.21.0 in central
        found com.google.auto.value#auto-value-annotations;1.7.2 in central
        found com.google.auth#google-auth-library-credentials;0.21.0 in central
        found com.google.api#api-common;1.9.3 in central
        found io.grpc#grpc-stub;1.30.0 in central
        found io.grpc#grpc-api;1.30.0 in central
        found io.grpc#grpc-context;1.30.0 in central
        found io.grpc#grpc-auth;1.30.0 in central
        found io.grpc#grpc-protobuf;1.30.0 in central
        found io.grpc#grpc-protobuf-lite;1.30.0 in central
        found io.grpc#grpc-netty-shaded;1.30.0 in central
        found io.grpc#grpc-core;1.30.0 in central
        found com.google.code.gson#gson;2.8.6 in central
        found com.google.android#annotations;4.1.1.4 in central
        found io.perfmark#perfmark-api;0.19.0 in central
        found io.grpc#grpc-alts;1.30.0 in central
        found io.grpc#grpc-grpclb;1.30.0 in central
        found org.apache.commons#commons-lang3;3.5 in central
        found org.conscrypt#conscrypt-openjdk-uber;2.2.1 in central
        found com.google.protobuf#protobuf-java-util;3.12.0 in central
:: resolution report :: resolve 1376ms :: artifacts dl 87ms
        :: evicted modules:
        io.grpc#grpc-api;1.28.1 by [io.grpc#grpc-api;1.30.0] in [default]
        io.grpc#grpc-context;1.28.1 by [io.grpc#grpc-context;1.30.0] in [default]
        com.google.guava#guava;28.2-android by [com.google.guava#guava;29.0-android] in [default]
        io.grpc#grpc-stub;1.28.1 by [io.grpc#grpc-stub;1.30.0] in [default]
        io.grpc#grpc-protobuf;1.28.1 by [io.grpc#grpc-protobuf;1.30.0] in [default]
        com.google.protobuf#protobuf-java;3.11.4 by [com.google.protobuf#protobuf-java;3.12.2] in [default]
        io.grpc#grpc-protobuf-lite;1.28.1 by [io.grpc#grpc-protobuf-lite;1.30.0] in [default]
        com.google.api#api-common;1.9.0 by [com.google.api#api-common;1.9.3] in [default]
        com.google.auto.value#auto-value-annotations;1.7 by [com.google.auto.value#auto-value-annotations;1.7.2] in [default]
        com.google.api#gax;1.56.0 by [com.google.api#gax;1.57.1] in [default]
        com.google.auth#google-auth-library-oauth2-http;0.20.0 by [com.google.auth#google-auth-library-oauth2-http;0.21.0] in [default]
        com.google.auth#google-auth-library-credentials;0.20.0 by [com.google.auth#google-auth-library-credentials;0.21.0] in [default]
        com.google.http-client#google-http-client;1.34.0 by [com.google.http-client#google-http-client;1.36.0] in [default]
        org.apache.httpcomponents#httpclient;4.5.10 by [org.apache.httpcomponents#httpclient;4.5.12] in [default]
        org.apache.httpcomponents#httpcore;4.4.12 by [org.apache.httpcomponents#httpcore;4.4.13] in [default]
        commons-codec#commons-codec;1.11 by [commons-codec#commons-codec;1.14] in [default]
        com.google.http-client#google-http-client-jackson2;1.34.0 by [com.google.http-client#google-http-client-jackson2;1.36.0] in [default]
        com.fasterxml.jackson.core#jackson-core;2.10.1 by [com.fasterxml.jackson.core#jackson-core;2.11.1] in [default]
        org.threeten#threetenbp;1.4.1 by [org.threeten#threetenbp;1.4.3] in [default]
        com.google.http-client#google-http-client;1.35.0 by [com.google.http-client#google-http-client;1.36.0] in [default]
        com.google.http-client#google-http-client-jackson2;1.35.0 by [com.google.http-client#google-http-client-jackson2;1.36.0] in [default]
        com.google.protobuf#protobuf-java;3.12.0 by [com.google.protobuf#protobuf-java;3.12.2] in [default]
        ---------------------------------------------------------------------
        |                  |            modules            ||   artifacts   |
        |       conf       | number| search|dwnlded|evicted|| number|dwnlded|
        ---------------------------------------------------------------------
        |      default     |   74  |   0   |   0   |   22  ||   52  |   0   |
        |      export      |   0   |   0   |   0   |   0   ||   0   |   0   |
        ---------------------------------------------------------------------
:: retrieving :: com.mendix#mxawsconnectors
        confs: [default, export]
        52 artifacts copied, 0 already retrieved (27925kB/389ms)

....\mx-face-detect-and-blur>pause
Press any key to continue . . .```
```

## Open & Run your project
Open the 'Face Detection.mpr' project file in Mendix Studio Pro 8.12.1+ & Run the Project
https://docs.mendix.com/releasenotes/studio-pro/8.12#8121

![alt text](https://mxw2.s3.eu-central-1.amazonaws.com/RunProject.png "Open & Run")

## Upload your .json credentials file under credentials

![alt text](https://mxw2.s3.eu-central-1.amazonaws.com/UploadCredentials.png "Upload Credentials")

## Upload a photo or image with a face

![alt text](https://mxw2.s3.eu-central-1.amazonaws.com/Upload.gif "Upload a photo")

## Try out your first image blur!

![alt text](https://mxw2.s3.eu-central-1.amazonaws.com/Blur.gif "Test Blur")

## Customize to your needs and to your liking, have fun and Go Make It!
