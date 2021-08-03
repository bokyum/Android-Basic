package basic.part2_chap03

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.AbsSavedState
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity: AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())

    private val diaryEditText: EditText by lazy {
        findViewById<EditText>(R.id.editText)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        val detailPreference = getSharedPreferences("diary", Context.MODE_PRIVATE)

        diaryEditText.setText(detailPreference.getString("detail", ""))

        val runnable = Runnable {
            getSharedPreferences("diary", Context.MODE_PRIVATE).edit {
                putString("detail", diaryEditText.text.toString())
            }

            Log.d("DiaryActivity", "changed !! ")
        }

        diaryEditText.addTextChangedListener {
            Log.d("DiaryActivity", "TextChanged :: ${it}")
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 500)
        }

    }
}