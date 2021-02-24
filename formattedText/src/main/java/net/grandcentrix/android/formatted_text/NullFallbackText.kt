package net.grandcentrix.android.formatted_text

import android.content.Context
import androidx.annotation.StringRes

/**
 * A type to abstract a fallback text if the value is null.
 * @see resolveString
 * @param [value] which should be displayed
 * @param [fallbackResId] resource id a string
 */
data class NullFallbackText(val value: Any?, @StringRes val fallbackResId: Int) {
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
 * Creates a [NullFallbackText] from the given value with [fallbackResId] as fallback.
 * @see [NullFallbackText]
 */
infix fun Any?.textIfNull(@StringRes fallbackResId: Int) =
    NullFallbackText(value = this, fallbackResId = fallbackResId)

/**
 * @return if string is null or blank return null else return this value
 */
fun String?.nullIfBlank() = if (isNullOrBlank()) null else this