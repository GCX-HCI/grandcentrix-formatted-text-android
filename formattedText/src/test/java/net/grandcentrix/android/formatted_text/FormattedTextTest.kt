package net.grandcentrix.android.formatted_text

import android.content.Context
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

    private val resId = 123
    private val fallbackId = 321
    private val contextText = "mock String with arguments"
    private val text = "a text"

    private val mockContext = mockk<Context> {
        every { getString(resId, any()) } returns String.format(contextText)
    }

    @Test
    fun `when formatted text create with value should return values`() {
        val formattedText = FormattedText(resId, text)
        assertEquals(resId, formattedText.stringResId)
        val arguments = formattedText.arguments
        assertEquals(1, arguments.size)
        assertEquals(text, formattedText.arguments[0])
    }

    @Test
    fun `when different formatted text with same arguments should be equals`() {
        val formattedText1 = FormattedText(resId, text)
        val formattedText2 = FormattedText(resId, text)
        assertEquals(formattedText1, formattedText2)
    }

    @Test
    fun `when different formatted text with different arguments should not be equals`() {
        val formattedText1 = FormattedText(resId, text)
        val formattedText2 = FormattedText(12, text)
        assertNotEquals(formattedText1, formattedText2)
    }

    @Test
    fun `when different formatted text with same arguments should have same has code`() {
        val formattedText1 = FormattedText(resId, text)
        val formattedText2 = FormattedText(resId, text)
        assertEquals(formattedText1.hashCode(), formattedText2.hashCode())
    }

    @Test
    fun `when different formatted text with different arguments should not have same has code`() {
        val formattedText1 = FormattedText(resId, text)
        val formattedText2 = FormattedText(12, text)
        assertNotEquals(formattedText1.hashCode(), formattedText2.hashCode())
    }

    @Test
    fun `when resolve formatted text should call get string with right parameter`() {
        val formattedText = FormattedText(resId, text)
        val resolved = formattedText.resolveString(mockContext)
        assertEquals(contextText, resolved)
        verify(exactly = 1) { mockContext.getString(resId, any()) }
    }

    @Test
    fun `when value is not null should return formatted text`() {
        val formattedText = text.formatWithFallback(resId, fallbackId)
        val expected = FormattedText(resId, text)
        assertEquals(expected, formattedText)
    }

    @Test
    fun `when value is null should return fallback`() {
        val aString: String? = null
        val formattedText = aString.formatWithFallback(resId, fallbackId)
        val expected = FormattedText(fallbackId)
        assertEquals(expected, formattedText)
    }

    @Test
    fun `when value format should create right FormattedText`() {
        val formattedString = text.format(resId)
        val expected = FormattedText(resId, text)
        assertEquals(expected, formattedString)
    }
}