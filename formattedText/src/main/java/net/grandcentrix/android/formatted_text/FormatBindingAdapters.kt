package net.grandcentrix.android.formatted_text

import android.content.Context
import android.widget.TextView
import androidx.databinding.BindingAdapter
import net.grandcentrix.android.util.formatted_text.FallbackValue

/**
 * Returns a localized formatted string from the application's package's
 * default string table, substituting the format arguments as defined in
 * [java.util.Formatter] and [java.lang.String.format].
 *
 * @param context Context of the application
 * @see [Context.getString]
 * @see [FormattedText.resolveString]
 * @return The string data associated with the resource, formatted and
 *         stripped of styled text information.
 */
@BindingAdapter("formattedText")
fun setFormattedText(textView: TextView, formattedText: FormattedText?) {
    textView.text = formattedText?.resolveString(textView.context)
}

/**
 * Returns the given value as string if it not null.
 * Otherwise returns a localized string from the application's package's
 * default string table.
 *
 * @param context Context of the application
 * @see [Context.getString]
 * @return The given value as string if it is not null,
 * otherwise string data associated with the resource. A blank string will also return the fallback value.
 */
@BindingAdapter("fallbackValue")
fun setFallbackValue(textView: TextView, fallbackValue: FallbackValue?) {
    if (fallbackValue == null) {
        textView.text = null
    } else if (fallbackValue.value is String && fallbackValue.value.isBlank()) {
        textView.text = fallbackValue.resolveString(textView.context)
    } else {
        textView.text =
            fallbackValue.value?.toString() ?: fallbackValue.resolveString(textView.context)
    }
}