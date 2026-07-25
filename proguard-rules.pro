# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-dontusemixedcaseclassnames
-verbose

# Preserve line numbers for debugging stack traces
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile

# Keep Kotlin metadata
-keepclassmembers class **$WhenMappings {
    <fields>;
}

# Keep Hilt generated code
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }

# Keep Room entities
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao class * { *; }
