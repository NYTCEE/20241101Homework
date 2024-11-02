package com.example.rebuild6

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val dialogItems = arrayOf("選項 1", "選項 2", "選項 3", "選項 4", "選項 5")

    // 使用 lazy 延遲初始化按鈕
    private val btnToast by lazy { findViewById<Button>(R.id.btnToast) }
    private val btnSnackBar by lazy { findViewById<Button>(R.id.btnSnackBar) }
    private val btnDialog1 by lazy { findViewById<Button>(R.id.btnDialog1) }
    private val btnDialog2 by lazy { findViewById<Button>(R.id.btnDialog2) }
    private val btnDialog3 by lazy { findViewById<Button>(R.id.btnDialog3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setupWindowInsets()
        setupClickListeners()
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
    }

    private fun setupClickListeners() {
        btnToast.setOnClickListener { showToast("預設 Toast") }
        btnSnackBar.setOnClickListener { showButtonSnackbar(it) }
        btnDialog1.setOnClickListener { showButtonDialog() }
        btnDialog2.setOnClickListener { showListDialog() }
        btnDialog3.setOnClickListener { showSingleChoiceDialog() }
    }

    private fun showButtonSnackbar(view: View) {
        Snackbar.make(view, "按鈕式 Snackbar", Snackbar.LENGTH_SHORT)
            .setAction("按鈕") { showToast("已回應") }
            .show()
    }

    private fun showButtonDialog() {
        AlertDialog.Builder(this).apply {
            setTitle("按鈕式 AlertDialog")
            setMessage("AlertDialog 內容")
            setNeutralButton("左按鈕") { _, _ -> showToast("左按鈕") }
            setNegativeButton("中按鈕") { _, _ -> showToast("中按鈕") }
            setPositiveButton("右按鈕") { _, _ -> showToast("右按鈕") }
        }.show()
    }

    private fun showListDialog() {
        AlertDialog.Builder(this).apply {
            setTitle("列表式 AlertDialog")
            setItems(dialogItems) { _, position ->
                showToast("你選的是${dialogItems[position]}")
            }
        }.show()
    }

    private fun showSingleChoiceDialog() {
        var selectedPosition = 0
        AlertDialog.Builder(this).apply {
            setTitle("單選式 AlertDialog")
            setSingleChoiceItems(dialogItems, 0) { _, position ->
                selectedPosition = position
            }
            setPositiveButton("確定") { _, _ ->
                showToast("你選的是${dialogItems[selectedPosition]}")
            }
        }.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}