package net.grandcentrix.android.formatted_text

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import java.io.Serializable

class FormattedText : Serializable {

    @StringRes
    @PluralsRes
    private val stringResId: Int
    private val quantity: Int?
    private val arguments: Array<out Any?>

    /**
     * Creates a new [FormattedText] with given [stringResId] and [arguments].
     * @param [stringResId] Resource id for the format string
     * @param [arguments] The format arguments that will be used for
     *                   substitution.
     */
    constructor(@StringRes stringResId: Int, vararg arguments: Any?) {
        this.stringResId = stringResId
        this.quantity = null
        this.arguments = arguments
    }

    /**
     * Creates a new [FormattedText] with given [pluralResId] and [arguments].
     * @param [pluralResId] Resource id for the format plurals
     * @param [arguments] The format arguments that will be used for
     *                   substitution.
     */
    constructor(@PluralsRes pluralResId: Int, quantity: Int, vararg arguments: Any?) {
        this.stringResId = pluralResId
        this.quantity = quantity
        this.arguments = arguments
    }

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
    fun resolveString(context: Context) = when (quantity) {
        null -> context.getString(stringResId, *arguments)
        else -> context.resources.getQuantityString(stringResId, quantity, *arguments)
    }

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
        return "${this::class.java.simpleName}(string = $stringResId, " +
                "arguments = ${arguments.joinToString(separator = ",")})"
    }
}

/**
 * Returns a localized formatted string from the application's package's
 * default string table, substituting the format arguments as defined in
 * {@link java.util.Formatter} and {@link java.lang.String#format}.
 *
 * @param formatString Resource id for the format string
 * @param stringResIfNull Resource id with should use if [this] is null.
 * @return The string data associated with the resource, formatted and
 *         stripped of styled text information.
 * @see [FormattedText][format]
 */
@Deprecated(
    "use format instead",
    ReplaceWith("format(@StringRes formatString: Int, @StringRes stringResIfNull: Int)")
)
fun Any?.formatWithFallback(@StringRes formatString: Int, @StringRes stringResIfNull: Int) =
    when (this) {
        null -> FormattedText(stringResIfNull)
        else -> FormattedText(formatString, this)
    }

/**
 * Returns a localized formatted string from the application's package's
 * default string table, substituting the format arguments as defined in
 * {@link java.util.Formatter} and {@link java.lang.String#format}.
 *
 * @param formatString Resource id for the format string
 * @param stringResIfNull Resource id with should use if [this] is null.
 * @return The string data associated with the resource, formatted and
 *         stripped of styled text information.
 */
fun Any?.format(@StringRes formatString: Int, @StringRes stringResIfNull: Int) =
    when (this) {
        null -> FormattedText(stringResIfNull)
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

/**
 * Returns a localized formatted string from the application's package's
 * default string table, substituting the format arguments as defined in
 * {@link java.util.Formatter} and {@link java.lang.String#format}.
 *
 * @param pluralsRes Resource id for the format string
 * @param quantity The number used to get the correct string for the current language's plural rules.
 * @param stringResIfNull Resource id with should use if [this] is null.
 * @return The string data associated with the resource, formatted and
 *         stripped of styled text information.
 */
fun Any?.formatQuantity(
    @PluralsRes pluralsRes: Int,
    quantity: Int,
    @StringRes stringResIfNull: Int
) = when (this) {
    null -> FormattedText(stringResIfNull)
    else -> FormattedText(pluralsRes, quantity, this)
}

/**
 * Returns a localized formatted string from the application's package's
 * default string table, substituting the format arguments as defined in
 * {@link java.util.Formatter} and {@link java.lang.String#format}.
 *
 * @param pluralsRes Resource id for the format string
 * * @param quantity The number used to get the correct string for the current language's plural rules.
 * @return The string data associated with the resource, formatted and
 *         stripped of styled text information.
 */
fun Any.formatQuantity(@PluralsRes pluralsRes: Int, quantity: Int) =
    FormattedText(pluralsRes, quantity, this)
