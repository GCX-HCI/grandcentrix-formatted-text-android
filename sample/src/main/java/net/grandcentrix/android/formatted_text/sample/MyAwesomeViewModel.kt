package net.grandcentrix.android.util.formatted_text

import net.grandcentrix.android.formatted_text.format
import net.grandcentrix.android.formatted_text.formatWithFallback
import net.grandcentrix.android.formatted_text.sample.R
import net.grandcentrix.android.formatted_text.withFallback

/**
 * A simple view model witch demonstrate the [FormattedText] and [FallbackValue].
 */
class MyAwesomeViewModel {
    private val nullInt: Int? = null

    val formattedNumber = 16.format(R.string.formatted_number)
    val formattedNumberWithFallback =
        32.formatWithFallback(R.string.formatted_number, R.string.fallback)
    val formattedNullNumberWithFallback =
        nullInt.formatWithFallback(R.string.formatted_number, R.string.fallback)

    val valueWithFallback = 32.withFallback(R.string.fallback)
    val nullValueWithFallback = nullInt.withFallback(R.string.fallback)
}