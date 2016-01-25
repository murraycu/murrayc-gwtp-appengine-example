# murrayc-gwtp-appengine-example

## Deploying to appengine

You can deploy to appengine like so:

$ mvn appengine:update

You can then see this, for example, at:
  http://murraycgwtpexample.appspot.com/ ,

Or:
  http://yourprojectid.appspot.com/
where yourprojectid is the project ID that you registered at
  https://console.cloud.google.com/
and which is specified in your appengine-web.xml file, like so:
  <application>yourprojectid</application>


## Internationalization

MurraycGwtpExampleConstants.java
is generated from
MurraycGwtpExampleConstants.properties
.

You can do this manually like so:
$ mvn gwt:i18n

