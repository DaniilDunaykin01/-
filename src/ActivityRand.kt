package com.example.lab2task2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class ActivityRand : AppCompatActivity() {
    private lateinit var head: TextView
    private lateinit var question: TextView
    private var number_question = 1
    private val random = Random()
    private var first_num = 0
    private var second_num = 0
    private var curToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)

        head = this.findViewById(R.id.textView2)
        val cur_msg = head.text.toString() + number_question
        head.text = cur_msg

        val min = 1; val max = 9
        randGenerate(max, min)

        question = findViewById(R.id.textView3)
        val que = "$first_num * $second_num = "
        question.text = que

        val res = findViewById<EditText>(R.id.editTextText9)
        val results = ArrayList<Boolean>()

        findViewById<TextView>(R.id.button4).setOnClickListener {
            val count_questions = 20

            curToast?.cancel()

            if (first_num * second_num == res.text.toString().toInt()) {
                curToast = Toast.makeText(this, "Правильный ответ", Toast.LENGTH_LONG)
                curToast?.show()
                results.add(true)
            } else {
                curToast = Toast.makeText(this, "Неправильный ответ", Toast.LENGTH_LONG)
                curToast?.show()
                results.add(false)
            }

            if (number_question == count_questions) {
                val countTrue = results.count { it }
                val countFalse = results.count { !it }
                var x = countTrue * 100.0 / count_questions
                val trueString = findViewById<TextView>(R.id.textView4)
                var msg = "${trueString.text} $countTrue это $x%"
                trueString.text = msg

                x = countFalse * 100.0 / count_questions
                val falseString = findViewById<TextView>(R.id.textView6)
                msg = "${falseString.text} $countFalse это $x%"
                falseString.text = msg

                res.isEnabled = false
                findViewById<Button>(R.id.button4).isEnabled = false
                head.text = "Тест завершен"
                return@setOnClickListener
            }

            randGenerate(max, min)
            res.text.clear()
            val newQue = "$first_num * $second_num = "
            question.text = newQue
            number_question++
            val newHead = head.text.toString().substring(0, if (number_question < 11) head.text.toString().length - 1 else head.text.toString().length - 2) + number_question
            head.text = newHead
        }
    }

    private fun randGenerate(max: Int, min: Int) {
        first_num = random.nextInt(max - min + 1) + min
        second_num = random.nextInt(max - min + 1) + min
    }
}