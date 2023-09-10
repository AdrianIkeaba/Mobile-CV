package com.ghostdev.resumeapp

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.ghostdev.resumeapp.databinding.ActivityEditBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        sharedPreferences = getSharedPreferences("cv_app", Context.MODE_PRIVATE)



        binding.dobBtn.setOnClickListener {
            showDatePickerDialog(binding.dobBtn)
        }

        binding.saveTxt.setOnClickListener {
            val editTextList = listOf(
                binding.fullnameEdt,
                binding.positionEdt,
                binding.locationEd,
                binding.slackEd,
                binding.dobEd,
                binding.githubEd,
                binding.bioEd
            )

            var hasEmptyFields = false

            for (editText in editTextList) {
                if (editText.text!!.isEmpty()) {
                    editText.error = "Field cannot be empty"
                    hasEmptyFields = true
                }
            }


            if (!hasEmptyFields) {
                val editor = sharedPreferences.edit()
                editor.putString("name", binding.fullnameEdt.editableText.toString())
                editor.putString("position", binding.positionEdt.editableText.toString())
                editor.putString("location", binding.locationEd.editableText.toString())
                editor.putString("dob", binding.dobEd.editableText.toString())
                editor.putString("slack", binding.slackEd.editableText.toString())
                editor.putString("github", binding.githubEd.editableText.toString())
                editor.putString("bio", binding.bioEd.editableText.toString())
                editor.putBoolean("edit", true)
                editor.apply()

                val intent = Intent(this@EditActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    private fun showDatePickerDialog(view: View) {
        val datePicker = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                // Update the selected date in the TextView
                calendar.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(calendar.time)
                binding.dobEd.setText(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePicker.show()
    }
}
