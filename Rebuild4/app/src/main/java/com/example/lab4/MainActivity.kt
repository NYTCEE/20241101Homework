package com.example.rebuild4

import android.app.Activity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.rebuild4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val drinkOrderLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { intent ->
                updateOrderDisplay(
                    drink = intent.getStringExtra("drink").orEmpty(),
                    sugar = intent.getStringExtra("sugar").orEmpty(),
                    ice = intent.getStringExtra("ice").orEmpty()
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnChoice.setOnClickListener {
            drinkOrderLauncher.launch(SecActivity.createIntent(this))
        }
    }

    private fun updateOrderDisplay(drink: String, sugar: String, ice: String) {
        binding.tvMeal.text = buildString {
            append("飲料：").append(drink)
            append("\n\n甜度：").append(sugar)
            append("\n\n冰塊：").append(ice)
        }
    }
}