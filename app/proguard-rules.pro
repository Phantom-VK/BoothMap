# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-keepattributes Signature
-keepattributes *Annotation*

# Keep the Booth model class and its members
-keepclassmembers class com.swag.boothmap.datacalsses.Booth { *; }

# Keep Firebase database classes and members
-keepclassmembers class com.google.firebase.database.** { *; }

# Keep all annotations (important for Firebase serialization)
-keepattributes *Annotation*

# Keep Firebase DataSnapshot (this helps prevent Firebase-specific serialization issues)
-keep class com.google.firebase.database.DataSnapshot { *; }

# Keep Kotlin data classes (if any other Kotlin data classes are involved)
-keepclassmembers class **Kt$ { *; }

# Keep methods used by Kotlin coroutines (since you're using coroutines)
-keepclassmembers class kotlinx.coroutines.** { *; }


# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile