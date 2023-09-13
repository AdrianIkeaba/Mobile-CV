package com.ghostdev.resumeapp

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

        if (!sharedPreferences.getBoolean("edit", false)) {
            binding.fullnameEdt.setText("Ikeaba Adrian")
            binding.positionEdt.setText("Android developer")
            binding.locationEd.setText("Lagos, NG")
            binding.slackEd.setText("Ikeaba Adrian")
            binding.dobEd.setText("05-07-2004")
            binding.githubEd.setText("AdrianIkeaba")
            binding.bioEd.setText("I am an experienced android developer with over a decade of experience in tech and 4 years as an android developer. Proficient in Kotlin with an understadning of Java as well. I have profound knowledge in API integration, databases, firebase and architectural patterns such as MVVM. Currently a student at Babcock University studying Computer science at 300 level.")
        } else {
            val name = sharedPreferences.getString("name", "Ikeaba Adrian")
            val position = sharedPreferences.getString("position","Android developer")
            val location = sharedPreferences.getString("location", "Lagos, NG")
            val slack = sharedPreferences.getString("slack", "Ikeaba Adrian")
            val github = sharedPreferences.getString("github", "AdrianIkeaba")
            val dob = sharedPreferences.getString("dob", "05-07-2004")
            val bio = sharedPreferences.getString("bio", "I am an experienced android developer with over a decade of experience in tech and 4 years as an android developer. Proficient in Kotlin with an understadning of Java as well. I have profound knowledge in API integration, databases, firebase and architectural patterns such as MVVM. Currently a student at Babcock University studying Computer science at 300 level.")

            binding.fullnameEdt.setText(name)
            binding.positionEdt.setText(position)
            binding.locationEd.setText(location)
            binding.slackEd.setText(slack)
            binding.githubEd.setText(github)
            binding.dobEd.setText(dob)
            binding.bioEd.setText(bio)

        }


        binding.dobBtn.setOnClickListener {
            showDatePickerDialog()
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
    private fun showDatePickerDialog() {
        val datePicker = DatePickerDialog(
            this,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
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
