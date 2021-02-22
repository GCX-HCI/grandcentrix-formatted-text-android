package net.grandcentrix.android.formatted_text

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FallbackValueTest {
    private val fallbackResId = 321
    private val fallbackText = "fallback"

    private val mockContext = mockk<Context> {
        every { getString(fallbackResId) } returns String.format(fallbackText)
    }

    @Test
    fun `when create fallback value should return right values`() {
        val fallbackValue = FallbackValue(32, fallbackResId)
        assertEquals(32, fallbackValue.value)
        assertEquals(fallbackResId, fallbackValue.fallbackResId)
    }

    @Test
    fun `when fallback value is null should get string return value`() {
        val fallbackValue = FallbackValue(32, fallbackResId)
        val resolved = fallbackValue.resolveString(mockContext)
        assertEquals("32", resolved)
        verify(exactly = 0) { mockContext.getString(any()) }
    }

    @Test
    fun `when fallback value is null should get string return fallback`() {
        val nullValue: Int? = null
        val fallbackValue = FallbackValue(nullValue, fallbackResId)
        val resolved = fallbackValue.resolveString(mockContext)
        assertEquals(fallbackText, resolved)
        verify(exactly = 1) { mockContext.getString(fallbackResId) }
    }

    @Test
    fun `when use withFallback should create right object`() {
        val withFallback = 32.withFallback(fallbackResId)
        val fallbackValue = FallbackValue(32, fallbackResId)
        assertEquals(fallbackValue, withFallback)
    }
}