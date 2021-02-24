package net.grandcentrix.android.formatted_text.sample

import net.grandcentrix.android.formatted_text.*

/**
 * A simple view model witch demonstrate the [FormattedText] and [NullFallbackText].
 */
class MyAwesomeViewModel {
    private val nullInt: Int? = null

    var formattedNumberWithFallback =
        32.formatWithFallback(R.string.formatted_number, R.string.fallback)
    val formattedNullNumberWithFallback =
        nullInt.formatWithFallback(R.string.formatted_number, R.string.fallback)

    val valueWithFallback = 32.ifNull(R.string.fallback)
    val nullValueWithFallback = nullInt.ifNull(R.string.fallback)
}