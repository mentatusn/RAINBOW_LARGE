package ru.dw.homeworktext

import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.dw.homeworktext.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var spannableRainbow: SpannableString//объеденяет все
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.LargeText.typeface =
            Typeface.createFromAsset(this.assets, "font/Lobster-Regular.ttf")
        spannableRainbow = SpannableString(getString(R.string.large_text))
        rainbow(1)
    }


    fun rainbow(i:Int=1) {
        var currentCount = i
        val x = object : CountDownTimer(20000, 200) {
            override fun onTick(millisUntilFinished: Long) {
                colorText(currentCount)
                currentCount = if (++currentCount>5) 1 else currentCount
            }
            override fun onFinish() {
                rainbow(currentCount)
            }
        }
        x.start()
    }


    private fun colorText(colorFirstNumber:Int){
        binding.LargeText.setText(spannableRainbow, TextView.BufferType.SPANNABLE)
        spannableRainbow = binding.LargeText.text as SpannableString
        val map = mapOf(
            0 to ContextCompat.getColor(this, R.color.red),
            1 to ContextCompat.getColor(this, R.color.orange),
            2 to ContextCompat.getColor(this, R.color.yellow),
            3 to ContextCompat.getColor(this, R.color.green),
            4 to ContextCompat.getColor(this, R.color.blue),
            5 to ContextCompat.getColor(this, R.color.purple_700),
            6 to ContextCompat.getColor(this,R.color.purple_500)
        )
        val spans = spannableRainbow.getSpans(
            0, spannableRainbow.length,
            ForegroundColorSpan::class.java
        )
        for (span in spans) {
            spannableRainbow.removeSpan(span)
        }

        var colorNumber = colorFirstNumber
        for (i in 0 until binding.LargeText.text.length) {
            if (colorNumber == 5) colorNumber = 0 else colorNumber += 1
            spannableRainbow.setSpan(
                ForegroundColorSpan(map.getValue(colorNumber)),
                i, i + 1,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
        }
    }

}