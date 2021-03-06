package dev.tsnanh.vku.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import dev.tsnanh.vku.domain.entities.Subject
import dev.tsnanh.vku.receivers.SchoolReminderReceiver
import timber.log.Timber
import java.util.ArrayList
import java.util.Calendar

const val RC_SCHOOL_REMINDER_MORNING = 1000
const val RC_SCHOOL_REMINDER_AFTERNOON = 2000
const val RC_SCHOOL_REMINDER_EVENING = 3000
const val RC_SCHOOL_REMINDER_NIGHT = 4000
val calendarMorning = prepareCalendar(6, 30)
val calendarAfternoon = prepareCalendar(12, 0)
val calendarEvening = prepareCalendar(18, 0)
val calendarNight = prepareCalendar(21, 0)

fun Context.setSchoolReminderAlarm(email: String) {
    val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val intent = Intent(this, SchoolReminderReceiver::class.java).apply {
        putExtra("email", email)
    }
    val hasIntentMorning = PendingIntent.getBroadcast(
        this,
        RC_SCHOOL_REMINDER_MORNING,
        intent,
        PendingIntent.FLAG_NO_CREATE
    )
    val hasIntentAfternoon = PendingIntent.getBroadcast(
        this,
        RC_SCHOOL_REMINDER_AFTERNOON,
        intent,
        PendingIntent.FLAG_NO_CREATE
    )
    val hasIntentEvening = PendingIntent.getBroadcast(
        this,
        RC_SCHOOL_REMINDER_EVENING,
        intent,
        PendingIntent.FLAG_NO_CREATE
    )
    val hasIntentNight = PendingIntent.getBroadcast(
        this,
        RC_SCHOOL_REMINDER_NIGHT,
        intent,
        PendingIntent.FLAG_NO_CREATE
    )

    if (hasIntentMorning == null || hasIntentAfternoon == null || hasIntentEvening == null || hasIntentNight == null) {
        val morningIntent = PendingIntent.getBroadcast(
            this,
            RC_SCHOOL_REMINDER_MORNING,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val afternoonIntent = PendingIntent.getBroadcast(
            this,
            RC_SCHOOL_REMINDER_AFTERNOON,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val eveningIntent = PendingIntent.getBroadcast(
            this,
            RC_SCHOOL_REMINDER_EVENING,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val nightIntent = PendingIntent.getBroadcast(
            this,
            RC_SCHOOL_REMINDER_NIGHT,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendarMorning.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                morningIntent
            )
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendarAfternoon.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                afternoonIntent
            )
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendarEvening.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                eveningIntent
            )
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendarNight.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                nightIntent
            )
        } else {
            when (Calendar.getInstance()[Calendar.HOUR_OF_DAY]) {
                in 0..6 -> {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarMorning.timeInMillis,
                        morningIntent
                    )
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarAfternoon.timeInMillis,
                        afternoonIntent
                    )
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarEvening.timeInMillis,
                        eveningIntent
                    )
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarNight.timeInMillis,
                        nightIntent
                    )
                }
                in 6..12 -> {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarMorning.timeInMillis + AlarmManager.INTERVAL_DAY,
                        morningIntent
                    )
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarAfternoon.timeInMillis,
                        afternoonIntent
                    )
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarEvening.timeInMillis,
                        eveningIntent
                    )
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarNight.timeInMillis,
                        nightIntent
                    )
                }
                in 12..18 -> {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarMorning.timeInMillis + AlarmManager.INTERVAL_DAY,
                        morningIntent
                    )
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarAfternoon.timeInMillis + AlarmManager.INTERVAL_DAY,
                        afternoonIntent
                    )
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarEvening.timeInMillis,
                        eveningIntent
                    )
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarNight.timeInMillis,
                        nightIntent
                    )
                }
                in 18..21 -> {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarMorning.timeInMillis + AlarmManager.INTERVAL_DAY,
                        morningIntent
                    )
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarAfternoon.timeInMillis + AlarmManager.INTERVAL_DAY,
                        afternoonIntent
                    )
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarEvening.timeInMillis + AlarmManager.INTERVAL_DAY,
                        eveningIntent
                    )
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarNight.timeInMillis,
                        nightIntent
                    )
                }
                else -> {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarMorning.timeInMillis + AlarmManager.INTERVAL_DAY,
                        morningIntent
                    )
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarAfternoon.timeInMillis + AlarmManager.INTERVAL_DAY,
                        afternoonIntent
                    )
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarEvening.timeInMillis + AlarmManager.INTERVAL_DAY,
                        eveningIntent
                    )
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendarNight.timeInMillis + AlarmManager.INTERVAL_DAY,
                        nightIntent
                    )
                }
            }
        }
        Timber.d("Create successfully!")
    } else {
        Timber.d("Already created!")
    }
}

fun prepareCalendar(hourOfDay: Int, minute: Int): Calendar {
    return Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hourOfDay)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
}

@Throws(IllegalArgumentException::class)
fun String.getHourFromStringLesson(): Int {
    return when (this[0]) {
        '1', '2' -> 7
        '3', '4' -> 9
        '5' -> 10
        '6' -> 12
        '7' -> 13
        '8' -> 14
        '9' -> 15
        // add more later
        else -> throw IllegalArgumentException("Wrong lesson string")
    }
}

fun String.getMinutesFromStringLesson(): Int {
    return when (this[0]) {
        '1', '3' -> 0
        '2', '4' -> 50
        '5' -> 40
        '6', '8' -> 30
        '7', '9' -> 20
        // add more later
        else -> throw IllegalArgumentException("Wrong lesson string")
    }
}

fun String.getDayOfWeekFromString(): ArrayList<Int> {
    return arrayListOf(
        when (this) {
            Constants.MONDAY -> Calendar.MONDAY
            Constants.TUESDAY -> Calendar.TUESDAY
            Constants.WEDNESDAY -> Calendar.WEDNESDAY
            Constants.THURSDAY -> Calendar.THURSDAY
            Constants.FRIDAY -> Calendar.FRIDAY
            Constants.SATURDAY -> Calendar.SATURDAY
            else -> throw IllegalArgumentException("Wrong day of week string")
        }
    )
}

fun dayOfWeekFilter(subject: Subject, dayOfWeek: Int): Boolean {
    return when (subject.dayOfWeek) {
        Constants.MONDAY -> Calendar.MONDAY == dayOfWeek
        Constants.TUESDAY -> Calendar.TUESDAY == dayOfWeek
        Constants.WEDNESDAY -> Calendar.WEDNESDAY == dayOfWeek
        Constants.THURSDAY -> Calendar.THURSDAY == dayOfWeek
        Constants.FRIDAY -> Calendar.FRIDAY == dayOfWeek
        Constants.SATURDAY -> Calendar.SATURDAY == dayOfWeek
        else -> Calendar.SUNDAY == dayOfWeek
    }
}