package com.example.lab2task2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var curToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button3).setOnClickListener { view: View? ->
            val new_activity =
                Intent(
                    this@MainActivity,
                    ActivityRand::class.java
                )
            this.startActivity(new_activity)
        }

        findViewById<Button>(R.id.button2).setOnClickListener { view: View? ->

            curToast?.cancel()

            val param = findViewById<EditText>(R.id.editTextText)
            val value = param.text.toString()
            if (value.isEmpty() || value.toInt() < 2 || value.toInt() > 9) {
                curToast = Toast.makeText(this, "Введите число от 2 до 9", Toast.LENGTH_LONG)
                curToast?.show()
                return@setOnClickListener
            }

            val new_activity = Intent(
                this@MainActivity,
                ActivityChoose::class.java
            )
            new_activity.putExtra("key1", value.toInt())
            startActivity(new_activity)
        }
    }
}