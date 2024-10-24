package com.example.lab2task2

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

class ActivityChoose : AppCompatActivity() {
    private lateinit var head: TextView
    private lateinit var question: TextView
    private var number_question: Int = 1
    private var random: Random = Random()
    private var second_num: Int = 0
    private var curToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)

        val args = checkNotNull(this.intent.extras)
        val first_num = args.getInt("key1")

        head = findViewById(R.id.textView2)
        val cur_msg = head.text.toString() + number_question
        head.text = cur_msg

        val min = 1
        val max = 9
        randGenerate(max, min)

        question = findViewById(R.id.textView3)
        val que = "$first_num * $second_num = "
        question.text = que

        val res = findViewById<EditText>(R.id.editTextText9)
        val results = ArrayList<Boolean>()

        findViewById<View>(R.id.button4).setOnClickListener { view: View? ->
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
                // 20        - 100%
                // countTrue - x
                var x = countTrue * 100 / count_questions.toDouble()
                val trueString = findViewById<TextView>(R.id.textView4)
                var msg = trueString.text.toString() + " " + countTrue + " это " + x + "%"
                trueString.text = msg

                x = countFalse * 100 / count_questions.toDouble()
                val falseString = findViewById<TextView>(R.id.textView6)
                msg = falseString.text.toString() + " " + countFalse + " это " + x + "%"
                falseString.text = msg

                res.isEnabled = false
                findViewById<View>(R.id.button4).isEnabled = false
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
        second_num = random.nextInt(max - min + 1) + min
    }
}