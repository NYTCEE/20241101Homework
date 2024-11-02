package com.example.rebuild4

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.rebuild4.databinding.ActivitySecBinding

class SecActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSubmitButton()
    }

    private fun setupSubmitButton() {
        binding.btnSend.setOnClickListener {
            if (validateInput()) {
                sendResult()
            }
        }
    }

    private fun validateInput(): Boolean {
        return when {
            binding.edDrink.text.isEmpty() -> {
                showToast("請輸入飲料名稱")
                false
            }
            binding.rgSugar.checkedRadioButtonId == -1 -> {
                showToast("請選擇甜度")
                false
            }
            binding.rgIce.checkedRadioButtonId == -1 -> {
                showToast("請選擇冰塊")
                false
            }
            else -> true
        }
    }

    private fun sendResult() {
        val drinkOrder = DrinkOrder(
            drink = binding.edDrink.text.toString(),
            sugar = getSelectedRadioButtonText(binding.rgSugar),
            ice = getSelectedRadioButtonText(binding.rgIce)
        )

        setResult(Activity.RESULT_OK, createResultIntent(drinkOrder))
        finish()
    }

    private fun getSelectedRadioButtonText(radioGroup: RadioGroup): String {
        val selectedId = radioGroup.checkedRadioButtonId
        return findViewById<RadioButton>(selectedId).text.toString()
    }

    private fun createResultIntent(order: DrinkOrder): Intent {
        return Intent().apply {
            putExtras(bundleOf(
                KEY_DRINK to order.drink,
                KEY_SUGAR to order.sugar,
                KEY_ICE to order.ice
            ))
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val KEY_DRINK = "drink"
        private const val KEY_SUGAR = "sugar"
        private const val KEY_ICE = "ice"

        fun createIntent(context: Context) = Intent(context, SecActivity::class.java)
    }

    private data class DrinkOrder(
        val drink: String,
        val sugar: String,
        val ice: String
    )
}