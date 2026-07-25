# Firebase Configuration
android.enableR8=true
-keepattributes *Annotation*
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.firebase.**
-dontwarn com.google.android.gms.**

# Retrofit
-keepattributes Signature
-keepattributes *Annotation*
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-dontwarn retrofit2.**

# OkHttp
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

# Gson
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class * implements com.google.gson.JsonDeserializable
-keep class * implements com.google.gson.JsonSerializable

# Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.**

# Timber
-keep class timber.log.Timber** { *; }
-keep class * extends timber.log.Timber$Tree

# Dagger Hilt
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.android.lifecycle.HiltViewModel
-dontwarn dagger.hilt.**

# Kotlin
-keep class kotlin.** { *; }
-keep class kotlinx.** { *; }
-dontwarn kotlin.**
-dontwarn kotlinx.**

# Keep data classes
-keep class com.akbar.chessvisionpro.data.models.** { *; }
-keep class com.akbar.chessvisionpro.data.remote.** { *; }

# General
-keep class com.akbar.chessvisionpro.** { *; }
-keepclassmembers class * {
    *** get*();
    void set*(...);
}