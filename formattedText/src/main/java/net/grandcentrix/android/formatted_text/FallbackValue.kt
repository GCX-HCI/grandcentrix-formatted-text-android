package net.grandcentrix.android.util.formatted_text

import android.content.Context
import androidx.annotation.StringRes

data class FallbackValue(val value: Any?, @StringRes val fallbackId: Int) {
    fun resolveString(context: Context) = value?.toString() ?: context.getString(fallbackId)
}

fun Any?.withFallback(@StringRes fallbackId: Int) =
    FallbackValue(value = this, fallbackId = fallbackId)