package net.grandcentrix.android.formatted_text.sample

import net.grandcentrix.android.formatted_text.*

/**
 * A simple view model witch demonstrate the [FormattedText] and [NullFallbackText].
 */
class MyAwesomeViewModel {
    private val nullInt: Int? = null

    // not formatted text
    val notFormatted = FormattedText(R.string.not_formatted)

    // formatted text
    val formattedNumber = 16.format(R.string.formatted_number)
    val formattedNumberWithFallback =
        32.format(R.string.formatted_number, R.string.fallback)
    val formattedNullNumberWithFallback =
        nullInt.format(R.string.formatted_number, R.string.fallback)

    // fallback
    val valueWithFallback = 32.textIfNull(R.string.fallback)
    val nullValueWithFallback = nullInt.textIfNull(R.string.fallback)

    // quantity
    val quantityOne = 16.formatQuantity(R.plurals.formatted_plural, 1)
    val quantityMany = 16.formatQuantity(R.plurals.formatted_plural, 2)
    val quantityWithFallback =
        nullInt.formatQuantity(R.plurals.formatted_plural, 2, R.string.fallback)
}