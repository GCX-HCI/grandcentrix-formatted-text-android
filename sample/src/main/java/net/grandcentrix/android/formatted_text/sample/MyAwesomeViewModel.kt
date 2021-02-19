package net.grandcentrix.android.util.formatted_text

import net.grandcentrix.android.formatted_text.sample.R

class MyAwesomeViewModel {
    private val nullInt: Int? = null

    var formattedText = FormattedText(R.string.lorem_ipsum)
    var formattedTextWith = 32.formatWithFallback(R.string.formatted_text, R.string.lorem_ipsum)
    val fallbackText =
        nullInt.formatWithFallback(R.string.formatted_text, R.string.lorem_ipsum)

    val fallbackValue = "a string".withFallback(R.string.lorem_ipsum)
    val nullWithFallbackValue = nullInt.withFallback(R.string.lorem_ipsum)
}