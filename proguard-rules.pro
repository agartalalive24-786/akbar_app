# Chess Vision Pro ProGuard Rules

-dontusemixedcaseclassnames
-verbose

# Keep line numbers
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Keep Kotlin
-keepclassmembers class **$WhenMappings {
    <fields>;
}

# Keep Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager { *; }

# Keep Room
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao class * { *; }
-keep class androidx.room.** { *; }

# Keep Compose
-keep class androidx.compose.** { *; }

# Keep models
-keep class com.akbar.chessvisionpro.data.models.** { *; }

# JSON serialization
-keepclassmembers class * implements com.google.gson.JsonSerializable {
    static final long serialVersionUID;
    static final com.google.gson.internal.Gson gson;
    public static final com.google.gson.stream.JsonReader;
    public static final com.google.gson.stream.JsonWriter;
}
