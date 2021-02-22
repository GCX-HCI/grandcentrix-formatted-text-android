package net.grandcentrix.android.formatted_text

import android.content.Context
import androidx.annotation.StringRes

/**
 * Create a new FallbackValue with a given [value] and [fallbackResId].
 * @param [value] which should be displayed
 * @param [fallbackResId] resource id a string
 */
data class FallbackValue(val value: Any?, @StringRes val fallbackResId: Int) {
    /**
     * Returns the given value as string if it not null.
     * Otherwise returns a localized string from the application's package's
     * default string table.
     *
     * @param context Context of the application
     * @see [Context.getString]
     * @return The given value as string if it is not null,
     * otherwise string data associated with the resource.
     */
    fun resolveString(context: Context) = value?.toString() ?: context.getString(fallbackResId)
}

/**
 * Creates a [FallbackValue] from the given value with [fallbackResId] as fallback.
 * @see [FallbackValue]
 */
fun Any?.withFallback(@StringRes fallbackResId: Int) =
    FallbackValue(value = this, fallbackResId = fallbackResId)