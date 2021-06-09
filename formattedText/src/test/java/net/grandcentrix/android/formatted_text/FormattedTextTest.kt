package net.grandcentrix.android.formatted_text

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

/**
 * Test the [FormattedText] class.
 */
class FormattedTextTest {

    @StringRes
    private val stringResId = 123

    @PluralsRes
    private val pluralsResIs = 456
    private val fallbackId = 321
    private val contextText = "mock String with arguments"
    private val text = "a text"

    private val mockContext = mockk<Context> {
        every { getString(stringResId, any()) } returns contextText
        every { resources.getQuantityString(any(), any(), any()) } returns contextText
    }

    @Test
    fun `when formatted text create with value should return values`() {
        val formattedText = FormattedText(stringResId, text)
        assertEquals("FormattedText(string = 123, arguments = a text)", formattedText.toString())
    }

    @Test
    fun `when different formatted text with same arguments should be equals`() {
        val formattedText1 = FormattedText(stringResId, text)
        val formattedText2 = FormattedText(stringResId, text)
        assertEquals(formattedText1, formattedText2)
    }

    @Test
    fun `when different formatted text with different arguments should not be equals`() {
        val formattedText1 = FormattedText(stringResId, text)
        val formattedText2 = FormattedText(12, text)
        assertNotEquals(formattedText1, formattedText2)
    }

    @Test
    fun `when different formatted text with same arguments should have same has code`() {
        val formattedText1 = FormattedText(stringResId, text)
        val formattedText2 = FormattedText(stringResId, text)
        assertEquals(formattedText1.hashCode(), formattedText2.hashCode())
    }

    @Test
    fun `when different formatted text with different arguments should not have same has code`() {
        val formattedText1 = FormattedText(stringResId, text)
        val formattedText2 = FormattedText(12, text)
        assertNotEquals(formattedText1.hashCode(), formattedText2.hashCode())
    }

    @Test
    fun `when resolve formatted text should call get string with right parameter`() {
        val formattedText = FormattedText(stringResId, text)
        val resolved = formattedText.resolveString(mockContext)
        assertEquals(contextText, resolved)
        verify(exactly = 1) { mockContext.getString(stringResId, any()) }
    }

    @Test
    fun `when value is not null should return formatted text`() {
        val formattedText = text.format(stringResId, fallbackId)
        val expected = FormattedText(stringResId, text)
        assertEquals(expected, formattedText)
    }

    @Test
    fun `when value is null should return fallback`() {
        val aString: String? = null
        val formattedText = aString.format(stringResId, fallbackId)
        val expected = FormattedText(fallbackId)
        assertEquals(expected, formattedText)
    }

    @Test
    fun `when value format should create right FormattedText`() {
        val formattedString = text.format(stringResId)
        val expected = FormattedText(stringResId, text)
        assertEquals(expected, formattedString)
    }

    @Test
    fun `when value format quantity should create right FormattedText`() {
        val formattedQuantity = text.formatQuantity(pluralsResIs, quantity = 2)
        val expected = FormattedText(pluralsResIs, 2, text)
        assertEquals(expected, formattedQuantity)
    }

    @Test
    fun `when value format quantity with fallback should create right FormattedText`() {
        val formattedQuantity =
            text.formatQuantity(pluralsResIs, quantity = 3, stringResIfNull = fallbackId)
        val expected = FormattedText(pluralsResIs, 2, text)
        assertEquals(expected, formattedQuantity)
    }

    @Test
    fun `when value is null and format quantity with fallback should create right FormattedText`() {
        val aString: String? = null
        val formattedQuantity =
            aString.formatQuantity(pluralsResIs, quantity = 1, stringResIfNull = fallbackId)
        val expected = FormattedText(fallbackId)
        assertEquals(expected, formattedQuantity)
    }

    @Test
    fun `when resolve formatted text with quantity should call get plurals with right parameter`() {
        val formattedText = FormattedText(pluralsResIs, 1, text)
        val resolved = formattedText.resolveString(mockContext)
        assertEquals(contextText, resolved)
        verify(exactly = 1) { mockContext.resources.getQuantityString(pluralsResIs, 1, text) }
    }
}