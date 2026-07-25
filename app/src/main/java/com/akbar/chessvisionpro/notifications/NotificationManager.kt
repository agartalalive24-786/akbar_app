package com.akbar.chessvisionpro.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.akbar.chessvisionpro.ui.MainActivity
import com.akbar.chessvisionpro.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    
    init {
        createNotificationChannels()
    }
    
    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val dailyChallengeChannel = NotificationChannel(
                CHANNEL_DAILY_CHALLENGE,
                "Daily Challenges",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Get notified about daily chess puzzle challenges"
                enableVibration(true)
            }
            
            val achievementChannel = NotificationChannel(
                CHANNEL_ACHIEVEMENT,
                "Achievements",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Celebrate your chess milestones"
            }
            
            val streakChannel = NotificationChannel(
                CHANNEL_STREAK,
                "Streak Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Keep your puzzle streak alive"
            }
            
            notificationManager.createNotificationChannel(dailyChallengeChannel)
            notificationManager.createNotificationChannel(achievementChannel)
            notificationManager.createNotificationChannel(streakChannel)
        }
    }
    
    fun showDailyChallengeNotification(
        title: String = "Daily Puzzle Challenge",
        description: String = "Solve today's puzzle and boost your rating!"
    ) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("screen", "puzzle")
        }
        
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val notification = NotificationCompat.Builder(context, CHANNEL_DAILY_CHALLENGE)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(description)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(longArrayOf(0, 500))
            .build()
        
        notificationManager.notify(NOTIFICATION_ID_DAILY, notification)
    }
    
    fun showAchievementNotification(
        achievement: String,
        description: String
    ) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ACHIEVEMENT)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("🏆 Achievement Unlocked: $achievement")
            .setContentText(description)
            .setAutoCancel(true)
            .build()
        
        notificationManager.notify(NOTIFICATION_ID_ACHIEVEMENT, notification)
    }
    
    fun showStreakReminderNotification(
        currentStreak: Int
    ) {
        val notification = NotificationCompat.Builder(context, CHANNEL_STREAK)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Keep Your Streak Alive! 🔥")
            .setContentText("You have $currentStreak day(s) streak. Solve a puzzle today!")
            .setAutoCancel(true)
            .build()
        
        notificationManager.notify(NOTIFICATION_ID_STREAK, notification)
    }
    
    fun cancelNotification(notificationId: Int) {
        notificationManager.cancel(notificationId)
    }
    
    companion object {
        const val CHANNEL_DAILY_CHALLENGE = "daily_challenge"
        const val CHANNEL_ACHIEVEMENT = "achievement"
        const val CHANNEL_STREAK = "streak_reminder"
        
        const val NOTIFICATION_ID_DAILY = 1001
        const val NOTIFICATION_ID_ACHIEVEMENT = 1002
        const val NOTIFICATION_ID_STREAK = 1003
    }
}
