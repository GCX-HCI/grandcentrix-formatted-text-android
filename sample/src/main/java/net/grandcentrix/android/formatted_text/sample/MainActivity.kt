package net.grandcentrix.android.formatted_text.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.grandcentrix.android.formatted_text.sample.databinding.ActivityMainBinding
import net.grandcentrix.android.util.formatted_text.MyAwesomeViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel = MyAwesomeViewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.first.text = viewModel.formattedText.resolveString(this)
        binding.second.text = viewModel.formattedTextWith.resolveString(this)
        binding.thrid.text = viewModel.fallbackText.resolveString(this)
        binding.fouth.text = viewModel.fallbackValue.resolveFallback(this)
        binding.fivth.text = viewModel.nullWithFallbackValue.resolveFallback(this)
    }
}