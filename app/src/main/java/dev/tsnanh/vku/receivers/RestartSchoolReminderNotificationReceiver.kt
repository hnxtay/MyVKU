package dev.tsnanh.vku.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import dev.tsnanh.vku.utils.setSchoolReminderAlarm

class RestartSchoolReminderNotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_BOOT_COMPLETED -> {
                context.setSchoolReminderAlarm(GoogleSignIn.getLastSignedInAccount(context)!!.email!!)
            }
        }
    }
}