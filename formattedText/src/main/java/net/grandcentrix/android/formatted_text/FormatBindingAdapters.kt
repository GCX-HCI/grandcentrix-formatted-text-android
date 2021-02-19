package net.grandcentrix.android.formatted_text

import android.widget.TextView
import androidx.databinding.BindingAdapter
import net.grandcentrix.android.util.formatted_text.FallbackValue
import net.grandcentrix.android.util.formatted_text.FormattedText

@BindingAdapter("formattedText")
fun setFormattedText(textView: TextView, formattedText: FormattedText?) {
    textView.text = formattedText?.resolveString(textView.context)
}

@BindingAdapter("fallbackValue")
fun setFallbackValue(textView: TextView, fallbackValue: FallbackValue?) {
    if (fallbackValue == null) {
        textView.text = null
    } else if (fallbackValue.value is String && fallbackValue.value.isBlank()) {
        textView.text = fallbackValue.resolveFallback(textView.context)
    } else {
        textView.text =
            fallbackValue.value?.toString() ?: fallbackValue.resolveFallback(textView.context)
    }
}