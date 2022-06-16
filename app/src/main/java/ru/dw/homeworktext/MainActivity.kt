package ru.dw.homeworktext

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.SpannedString
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

    }

    private fun spannable() {
        val spannedString: SpannedString // только хранит
        var spannableString: SpannableString //может менять
        val spannableStringBuilder: SpannableStringBuilder//объеденяет все


        spannableString = SpannableString(getString(R.string.large_text))
        spannableString = changeColor(spannableString)
        spannableString = backgroundColorSpan(spannableString)
        spannableString = strikethroughSpan(spannableString)
        binding.LargeText.text = spannableString
    }

    private fun strikethroughSpan(spannableString: SpannableString): SpannableString {
        val startIndex = 30
        val endIndex = 50
        val flag = 0  // 0: no flag;
        spannableString.setSpan(StrikethroughSpan(), startIndex, endIndex, flag)
        return spannableString
    }

    private fun backgroundColorSpan(spannableString: SpannableString): SpannableString {
        val startIndex = 6
        val endIndex = 9
        val flag = 0  // 0: no flag;
// Change text background color
        val color = ContextCompat.getColor(this, android.R.color.holo_blue_light)
        spannableString.setSpan(BackgroundColorSpan(color), startIndex, endIndex, flag)
        return spannableString
    }

    private fun changeColor(spannableString: SpannableString): SpannableString {
        val startIndex = 0
        val endIndex = 1
        val flag = 0  // 0: no flag;
        val color = ContextCompat.getColor(this, android.R.color.holo_blue_light)
        spannableString.setSpan(ForegroundColorSpan(color), startIndex, endIndex, flag)
        return spannableString
    }
}