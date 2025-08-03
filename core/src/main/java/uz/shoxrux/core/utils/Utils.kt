package uz.shoxrux.core.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.ByteArrayOutputStream
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.*
import java.time.format.DateTimeFormatter

fun bitmapToByteArray(context: Context, uri: Uri): ByteArray {
    val inputStream = context.contentResolver.openInputStream(uri)
    val bitmap = BitmapFactory.decodeStream(inputStream)
    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 10, baos)
    return baos.toByteArray()
}

fun Long.toReadableTime(): String {
    val postTime = Instant.ofEpochSecond(this).atZone(ZoneId.systemDefault()).toLocalDateTime()
    val now = LocalDateTime.now()

    val duration = Duration.between(postTime, now)
    val minutes = duration.toMinutes()
    val hours = duration.toHours()
    val days = duration.toDays()

    return when {
        minutes < 1 -> "Только что"
        minutes < 60 -> "$minutes ${minutes.minutesWord()} назад"
        hours < 24 && postTime.toLocalDate() == now.toLocalDate() ->
            postTime.format(DateTimeFormatter.ofPattern("HH:mm"))

        days == 1L || postTime.toLocalDate() == now.minusDays(1).toLocalDate() ->
            "Вчера, ${postTime.format(DateTimeFormatter.ofPattern("HH:mm"))}"

        else -> {
            val formatter = if (postTime.year == now.year) {
                DateTimeFormatter.ofPattern("d MMMM")
            } else {
                DateTimeFormatter.ofPattern("d MMMM yyyy")
            }
            postTime.format(formatter)
        }
    }
}


private fun Long.minutesWord(): String = when {
    this % 10 == 1L && this % 100 != 11L -> "minute"
    this % 10 in 2..4 && (this % 100 !in 12..14) -> "minutes"
    else -> "minute"
}

private fun Long.hoursWord(): String = when {
    this % 10 == 1L && this % 100 != 11L -> "hout"
    this % 10 in 2..4 && (this % 100 !in 12..14) -> "hour"
    else -> "hours"
}