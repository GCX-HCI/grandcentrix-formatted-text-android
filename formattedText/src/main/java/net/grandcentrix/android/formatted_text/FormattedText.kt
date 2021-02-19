package net.grandcentrix.android.util.formatted_text

import android.content.Context
import androidx.annotation.StringRes
import java.io.Serializable

/**
 * A formatted text by [java.util.Formatter] and [java.lang.String.format].
 */
class FormattedText(@StringRes val string: Int, vararg val arguments: Any?) : Serializable {

    /**
     * Returns a localized formatted string from the application's package's
     * default string table, substituting the format arguments as defined in
     * [java.util.Formatter] and [java.lang.String.format].
     *
     * @param context Context of the application
     * @return The string data associated with the resource, formatted and
     *         stripped of styled text information.
     */
    fun resolveString(context: Context) = context.getString(string, *arguments)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FormattedText) return false

        if (string != other.string) return false
        if (!arguments.contentEquals(other.arguments)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = string
        result = 31 * result + arguments.contentHashCode()
        return result
    }

    override fun toString(): String {
        return "${this::class.java.simpleName}(string = $string, arguments = $arguments)"
    }
}

/**
 * Returns a localized formatted string from the application's package's
 * default string table, substituting the format arguments as defined in
 * {@link java.util.Formatter} and {@link java.lang.String#format}.
 *
 * @param resId Resource id for the format string
 * @param formatArgs The format arguments that will be used for
 *                   substitution.
 * @return The string data associated with the resource, formatted and
 *         stripped of styled text information.
 */
fun Any?.formatWithFallback(@StringRes formatString: Int, @StringRes stringIfNull: Int) =
    when (this) {
        null -> FormattedText(stringIfNull)
        else -> FormattedText(formatString, this)
    }