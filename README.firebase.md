# Chess Vision Pro — build notes

I made a small automated change to apply the Google Services Gradle plugin in the `app` module so Firebase configuration is picked up during builds.

What changed
- app/build.gradle: the `com.google.gms.google-services` plugin is now applied in the plugins block (it previously had `apply false`).

Important: google-services.json (NOT included)
- This repository uses Firebase libraries. You must provide your own `google-services.json` file in the `app/` directory for the build to succeed.
- Download the file from the Firebase console for the Android app with applicationId `com.akbar.chessvisionpro` and place it at `app/google-services.json`.
- Do NOT commit sensitive configuration if it contains credentials you don't want public; consider using CI secrets instead.

How to build locally
1. Ensure `app/google-services.json` is present.
2. From repo root run:

   ./gradlew clean assembleDebug --stacktrace --info

If the build still fails, paste the full Gradle stacktrace here and I'll iterate on fixes.
