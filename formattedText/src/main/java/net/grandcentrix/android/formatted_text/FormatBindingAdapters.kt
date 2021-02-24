package net.grandcentrix.android.formatted_text

import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * If the method is called with null the text view's text is set with null.
 * Else the text is resolved by [FormattedText.resolveString] and set as text.
 *
 * @param textView the view which should display the text
 * @param formattedText the text witch should display
 * @see [FormattedText.resolveString]
 */
@BindingAdapter("android:text")
fun setFormattedText(textView: TextView, formattedText: FormattedText?) {
    textView.text = formattedText?.resolveString(textView.context)
}

/**
 * If the method is called with null the text view's text is set with null.
 * Else the text is resolved by [NullFallbackText.resolveString] and set as [TextView.setText].
 * Also if the [NullFallbackText.value] is a [String] and [isBlank] the text [NullFallbackText.fallbackResId] is set as text.
 *
 * @param textView the view which should display the text
 * @param nullFallbackText the text witch should display
 * @see [NullFallbackText.resolveString]
 */
@BindingAdapter("android:text")
fun setNullFallbackText(textView: TextView, nullFallbackText: NullFallbackText?) {
    if (nullFallbackText == null) {
        textView.text = null
    } else {
        textView.text =
            nullFallbackText.value?.toString() ?: nullFallbackText.resolveString(textView.context)
    }
}