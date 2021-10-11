package dev.goobar.discoverandroidproject1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_result)

    val extras = intent.extras!!

    val value1 = extras.getInt(EXTRA_VALUE1)
    val value2 = extras.getInt(EXTRA_VALUE2)
    val result = extras.getInt(EXTRA_RESULT)

    val resultText: TextView = findViewById(R.id.resultText)
    resultText.text = "$value1 + $value2 = $result"
  }

  companion object {

    private const val EXTRA_VALUE1 = "extra_value1"
    private const val EXTRA_VALUE2 = "extra_value2"
    private const val EXTRA_RESULT = "extra_result"

    fun createIntent(context: Context, value1: Int, value2: Int, result: Int): Intent {
      val intent = Intent(context, ResultActivity::class.java)
      intent.putExtra(EXTRA_VALUE1, value1)
      intent.putExtra(EXTRA_VALUE2, value2)
      intent.putExtra(EXTRA_RESULT, result)
      return intent
    }
  }
}