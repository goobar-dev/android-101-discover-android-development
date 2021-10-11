package dev.goobar.discoverandroidproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

  private val TAG = MainActivity::class.simpleName

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d(TAG, "onCreate()")
    setContentView(R.layout.activity_main)

    val root: View = findViewById(R.id.root)
    val field1: EditText = findViewById(R.id.value1Field)
    val field2: EditText = findViewById(R.id.value2Field)
    val resultButton: Button = findViewById(R.id.resultButton)

    resultButton.setOnClickListener {
      val value1 = field1.text.toString().toInt()
      val value2 = field2.text.toString().toInt()
      val result = value1 + value2

      startActivity(ResultActivity.createIntent(this, value1, value2, result))
    }
  }

  override fun onStart() {
    super.onStart()
    Log.d(TAG, "onStart()")
  }

  override fun onResume() {
    super.onResume()
    Log.d(TAG, "onResume()")
  }

  override fun onPause() {
    super.onPause()
    Log.d(TAG, "onPause()")
  }

  override fun onStop() {
    super.onStop()
    Log.d(TAG, "onStop()")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d(TAG, "onDestroy()")
  }
}