package com.ghostdev.resumeapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.ghostdev.resumeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.back)

        binding.editFab.setOnClickListener {
            val intent = Intent(this@MainActivity, EditActivity::class.java)
            startActivity(intent)
        }
        sharedPreferences = getSharedPreferences("cv_app", Context.MODE_PRIVATE)

        val edit = sharedPreferences.getBoolean("edit", false)
        val name = sharedPreferences.getString("name", "Ikeaba Adrian")
        val position = sharedPreferences.getString("position","Android developer")
        val location = sharedPreferences.getString("location", "Lagos, NG")
        val slack = sharedPreferences.getString("slack", "Ikeaba Adrian")
        val github = sharedPreferences.getString("github", "AdrianIkeaba")
        val dob = sharedPreferences.getString("dob", "05-07-2004")
        val bio = sharedPreferences.getString("bio", "I am an experienced android developer with over a decade of experience in tech and 4 years as an android developer. Proficient in Kotlin with an understadning of Java as well. I have profound knowledge in API integration, databases, firebase and architectural patterns such as MVVM. Currently a student at Babcock University studying Computer science at 300 level.")

        if (edit) {
            binding.nameTxt.text = name
            binding.positionTxt.text = position
            binding.locationTxt.text = location
            binding.dobTxt.text = dob
            binding.slackTxt.text = slack
            binding.gitHubTxt.text = github
            binding.bioText.text = bio

        }

    }
}