package net.grandcentrix.android.formatted_text

import android.content.Context
import android.widget.TextView
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class BindingAdapterTest {
    private val resId = 123
    private val fallbackId = 321
    private val fallbackText = "fallback"
    private val mockContext = mockk<Context> {
        every { getString(resId, any()) } returns "a text"
        every { getString(fallbackId) } returns fallbackText
    }
    private val mockTextView = mockk<TextView>(relaxed = true) {
        every { context } returns mockContext
    }

    @Test
    fun `when set formatted text with formatted text should set text`() {
        val formattedText = FormattedText(resId, fallbackText)
        setFormattedText(mockTextView, formattedText)
        verify(exactly = 1) { formattedText.resolveString(mockContext) }
        verify(exactly = 1) { mockTextView.text = "a text" }
    }

    @Test
    fun `when set formatted text with null should set text to null`() {
        setFormattedText(mockTextView, null)
        verify(exactly = 1) { mockTextView.text = null }
    }

    @Test
    fun `when set fallback text with null should set text to null`() {
        setNullFallbackText(mockTextView, null)
        verify(exactly = 1) { mockTextView.text = null }
    }

    @Test
    fun `when set fallback text should set value as text`() {
        val fallbackValue = NullFallbackText(32, fallbackId)
        setNullFallbackText(mockTextView, fallbackValue)
        verify(exactly = 1) { mockTextView.text = "32" }
    }

    @Test
    fun `when set fallback text with null should set fallback as text `() {
        val nullInt: Int? = null
        val fallbackValue = NullFallbackText(nullInt, fallbackId)
        setNullFallbackText(mockTextView, fallbackValue)
        verify(exactly = 1) { fallbackValue.resolveString(mockContext) }
        verify(exactly = 1) { mockTextView.text = fallbackText }
    }

    @Test
    fun `when set fallback text with blank text should set fallback as text`() {
        val fallbackValue = " ".nullIfBlank().textIfNull(fallbackId)
        setNullFallbackText(mockTextView, fallbackValue)
        verify(exactly = 1) { mockTextView.text = fallbackText }
    }
}