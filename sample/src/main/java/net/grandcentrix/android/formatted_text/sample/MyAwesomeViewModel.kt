package net.grandcentrix.android.formatted_text.sample

import net.grandcentrix.android.formatted_text.FormattedText
import net.grandcentrix.android.formatted_text.NullFallbackText
import net.grandcentrix.android.formatted_text.format
import net.grandcentrix.android.formatted_text.textIfNull

/**
 * A simple view model witch demonstrate the [FormattedText] and [NullFallbackText].
 */
class MyAwesomeViewModel {
    private val nullInt: Int? = null

    val formattedNumber = 16.format(R.string.formatted_number)
    val formattedNumberWithFallback =
        32.format(R.string.formatted_number, R.string.fallback)
    val formattedNullNumberWithFallback =
        nullInt.format(R.string.formatted_number, R.string.fallback)
    val valueWithFallback = 32.textIfNull(R.string.fallback)
    val nullValueWithFallback = nullInt.textIfNull(R.string.fallback)
}