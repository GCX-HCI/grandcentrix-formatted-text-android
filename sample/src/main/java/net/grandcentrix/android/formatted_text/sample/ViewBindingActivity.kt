package net.grandcentrix.android.formatted_text.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.grandcentrix.android.formatted_text.FormattedText
import net.grandcentrix.android.formatted_text.sample.databinding.ActivityViewBindingBinding

/**
 * Sample activity which shows the work between [FormattedText], [FallbackValue], activity, fragment and view model.
 */
class ViewBindingActivity : AppCompatActivity() {

    private val viewModel = MyAwesomeViewModel()
    private lateinit var binding: ActivityViewBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.formattedNumber.text = viewModel.formattedNumber.resolveString(this)
        binding.formattedNumberWithFallback.text =
            viewModel.formattedNumberWithFallback.resolveString(this)
        binding.formattedNullNumberWithFallback.text =
            viewModel.formattedNullNumberWithFallback.resolveString(this)
        binding.valueWithFallback.text = viewModel.valueWithFallback.resolveString(this)
        binding.nullValueWithFallback.text = viewModel.nullValueWithFallback.resolveString(this)
    }
}