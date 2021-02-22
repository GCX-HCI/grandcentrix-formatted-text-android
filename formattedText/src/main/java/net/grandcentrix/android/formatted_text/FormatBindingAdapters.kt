package net.grandcentrix.android.formatted_text

import android.widget.TextView
import androidx.databinding.BindingAdapter
import net.grandcentrix.android.util.formatted_text.FallbackValue

/**
 * If the method is called with null the text view's text is set with null.
 * Else the text is resolved by [FormattedText.resolveString] and set as text.
 *
 * @param textView the view which should display the text
 * @param formattedText the text witch should display
 * @see [FormattedText.resolveString]
 */
@BindingAdapter("formattedText")
fun setFormattedText(textView: TextView, formattedText: FormattedText?) {
    textView.text = formattedText?.resolveString(textView.context)
}

/**
 * If the method is called with null the text view's text is set with null.
 * Else the text is resolved by [FallbackValue.resolveString] and set as [TextView.setText].
 * Also if the [FallbackValue.value] is a [String] and [isBlank] the text [FallbackValue.fallbackResId] is set as text.
 *
 * @param textView the view which should display the text
 * @param fallbackValue the text witch should display
 * @see [FallbackValue.resolveString]
 */
@BindingAdapter("fallbackValue")
fun setFallbackValue(textView: TextView, fallbackValue: FallbackValue?) {
    if (fallbackValue == null) {
        textView.text = null
    } else if (fallbackValue.value is String && fallbackValue.value.isBlank()) {
        textView.text = fallbackValue.copy(value = null).resolveString(textView.context)
    } else {
        textView.text =
            fallbackValue.value?.toString() ?: fallbackValue.resolveString(textView.context)
    }
}