package net.grandcentrix.android.util.formatted_text

import net.grandcentrix.android.formatted_text.sample.R

/**
 * A simple view model witch demonstrate the [FormattedText] and [FallbackValue].
 */
class MyAwesomeViewModel {
    private val nullInt: Int? = null

    var formattedNumberWithFallback =
        32.formatWithFallback(R.string.formatted_number, R.string.fallback)
    val formattedNullNumberWithFallback =
        nullInt.formatWithFallback(R.string.formatted_number, R.string.fallback)

    val valueWithFallback = 32.withFallback(R.string.fallback)
    val nullValueWithFallback = nullInt.withFallback(R.string.fallback)
}