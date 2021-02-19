package net.grandcentrix.android.formatted_text.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.grandcentrix.android.formatted_text.sample.databinding.ActivityDataBindingBinding
import net.grandcentrix.android.util.formatted_text.MyAwesomeViewModel

class DataBindingActivity : AppCompatActivity() {

    private val viewModel = MyAwesomeViewModel()
    private lateinit var binding: ActivityDataBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel
    }
}