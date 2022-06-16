package ru.dw.homeworktext

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.dw.homeworktext.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.LargeText.typeface =
            Typeface.createFromAsset(this.assets, "font/Lobster-Regular.ttf")

        spannable()
        changeColor()
    }

    private fun spannable() {
        //val spannedString: SpannedString // только хранит
        //var spannableString: SpannableString //может менять
        var spannableStringBuilder: SpannableStringBuilder//объеденяет все
        spannableStringBuilder = SpannableStringBuilder(getString(R.string.large_text))


        binding.LargeText.setText(spannableStringBuilder, TextView.BufferType.EDITABLE)//Buffer
        spannableStringBuilder = binding.LargeText.text as SpannableStringBuilder


        backgroundColorSpan(spannableStringBuilder)
        strikethroughSpan(spannableStringBuilder)


    }

    private fun changeColor() {
        val spannableStringBuilder = SpannableStringBuilder(getString(R.string.large_text))

        lifecycleScope.launch(Dispatchers.Main) {
            var i = 0
            spannableStringBuilder.forEachIndexed { index, _ ->
                spannableColor(spannableStringBuilder, index, rainbow[i])
                i++
                if (i > 6) i = 0
            }
        }

    }

    private val rainbow = listOf(
        R.color.red,
        R.color.orange,
        R.color.yellow,
        R.color.green,
        R.color.light_blue,
        R.color.blue,
        R.color.violet
    )


    private suspend fun spannableColor(
        spannableString: SpannableStringBuilder,
        startIndex: Int,
        color: Int
    ) {
        val flag = 0  // 0: no flag;
        delay(100L)
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, color)),
            startIndex,
            startIndex + 1,
            flag
        )
//        delay(100L)
//        spannableString.setSpan(
//            ForegroundColorSpan(ContextCompat.getColor(this, R.color.black)),
//            startIndex,
//            startIndex + 1,
//            flag
//        )
        binding.LargeText.text = spannableString

    }


    private fun strikethroughSpan(spannableString: SpannableStringBuilder) {
        val startIndex = 30
        val endIndex = 50
        val flag = 0  // 0: no flag;
        spannableString.setSpan(StrikethroughSpan(), startIndex, endIndex, flag)

    }

    private fun backgroundColorSpan(spannableString: SpannableStringBuilder) {
        val startIndex = 6
        val endIndex = 9
        val flag = 0  // 0: no flag;
        val color = ContextCompat.getColor(this, android.R.color.holo_blue_light)
        spannableString.setSpan(BackgroundColorSpan(color), startIndex, endIndex, flag)

    }
}