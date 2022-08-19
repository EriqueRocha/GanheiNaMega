package com.example.ganheinamega

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ganheinamega.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity(){

private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        preferences = getSharedPreferences("DB", Context.MODE_PRIVATE)
        val result = preferences.getString("result",null)
        if (result != null){
            binding.txtResult.text = "Ultimos números: $result"
        }

        binding.btnGenerate.setOnClickListener {
            numberGenerator()
        }
    }

    private fun numberGenerator() {
    val text = binding.editTNumber.text.toString()
        if(text.isNotEmpty()){
            if(text.toInt() in 6..15){
                val numbers = mutableSetOf<Int>()
                val random = Random()
                while (true){
                    val number = random.nextInt(60)
                    numbers.add(number+1)

                    if(numbers.size == text.toInt()){
                        break
                    }
                }
                binding.txtResult.text = numbers.joinToString(" - ")

                val editor = preferences.edit()
                editor.putString("result", binding.txtResult.text.toString())
                editor.apply()

                /*
                OUTRA MANEIRA DE FAZER
                preferences.edit(),apply{
                putString("result", binding.txtResult.text.toString())
                apply()
                }
                */

            }else{
                Toast.makeText(this, "Informe um valor entre 6 e 15", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Informe um valor entre 6 e 15", Toast.LENGTH_SHORT).show()
        }
    }
}
