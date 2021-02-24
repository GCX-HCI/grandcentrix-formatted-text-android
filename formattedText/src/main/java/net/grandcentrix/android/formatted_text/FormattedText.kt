package net.grandcentrix.android.formatted_text

import android.content.Context
import androidx.annotation.StringRes
import java.io.Serializable

/**
 * Creates a new [FormattedText] with given [stringResId] and [arguments].
 * @param [resId] Resource id for the format string
 * @param [arguments] The format arguments that will be used for
 *                   substitution.
 */
class FormattedText(@StringRes val stringResId: Int, vararg val arguments: Any?) : Serializable {

    /**
     * Returns a localized formatted string from the application's package's
     * default string table, substituting the format arguments as defined in
     * [java.util.Formatter] and [java.lang.String.format].
     *
     * @param context Context of the application
     * @see [Context.getString]
     * @return The string data associated with the resource, formatted and
     *         stripped of styled text information.
     */
    fun resolveString(context: Context) = context.getString(stringResId, *arguments)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FormattedText) return false

        if (stringResId != other.stringResId) return false
        if (!arguments.contentEquals(other.arguments)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = stringResId
        result = 31 * result + arguments.contentHashCode()
        return result
    }

    override fun toString(): String {
        return "${this::class.java.simpleName}(string = $stringResId, arguments = $arguments)"
    }
}

/**
 * Returns a localized formatted string from the application's package's
 * default string table, substituting the format arguments as defined in
 * {@link java.util.Formatter} and {@link java.lang.String#format}.
 *
 * @param formatString Resource id for the format string
 * @param stringIfNull Resource id with should use if [this] is null.
 * @return The string data associated with the resource, formatted and
 *         stripped of styled text information.
 */
fun Any?.formatWithFallback(@StringRes formatString: Int, @StringRes stringIfNull: Int) =
    when (this) {
        null -> FormattedText(stringIfNull)
        else -> FormattedText(formatString, this)
    }

/**
 * Returns a localized formatted string from the application's package's
 * default string table, substituting the format arguments as defined in
 * {@link java.util.Formatter} and {@link java.lang.String#format}.
 *
 * @param formatString Resource id for the format string
 * @return The string data associated with the resource, formatted and
 *         stripped of styled text information.
 */
fun Any.format(@StringRes formatString: Int) = FormattedText(formatString, this)