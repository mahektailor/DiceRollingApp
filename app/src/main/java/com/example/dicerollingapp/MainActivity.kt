package com.example.dicerollingapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var diceRadioGroup: RadioGroup
    private lateinit var customDiceSpinner: Spinner
    private lateinit var rollButton: Button
    private lateinit var rollTwiceButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        diceRadioGroup = findViewById(R.id.diceRadioGroup)
        customDiceSpinner = findViewById(R.id.customDiceSpinner)
        rollButton = findViewById(R.id.rollButton)
        rollTwiceButton = findViewById(R.id.rollTwiceButton)
        resultTextView = findViewById(R.id.resultTextView)

        // Populate custom dice values in the spinner
        val customDiceValues = listOf(4, 6, 8, 10, 12, 20)
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, customDiceValues)
        customDiceSpinner.adapter = spinnerAdapter

        // Set click listeners for roll buttons
        rollButton.setOnClickListener {
            val selectedDice = getSelectedDice()
            val result = rollDice(selectedDice)
            displayResult(result)
        }

        rollTwiceButton.setOnClickListener {
            val selectedDice = getSelectedDice()
            val result1 = rollDice(selectedDice)
            val result2 = rollDice(selectedDice)
            displayResult("Result 1: $result1\nResult 2: $result2")
        }
    }

    // Get the selected dice value based on user input
    private fun getSelectedDice(): Int {
        val checkedRadioButtonId = diceRadioGroup.checkedRadioButtonId
        return when (checkedRadioButtonId) {
            R.id.radioButton4 -> 4
            R.id.radioButton6 -> 6
            R.id.radioButton8 -> 8
            R.id.radioButton10 -> 10
            R.id.radioButton12 -> 12
            R.id.radioButton20 -> 20
            else -> (customDiceSpinner.selectedItem as? Int) ?: 0
        }
    }

    // Roll the dice and return the result as a string
    private fun rollDice(maxValue: Int): String {
        val result = Random.nextInt(1, maxValue + 1)
        return result.toString()
    }

    // Display the result in the TextView
    private fun displayResult(result: String) {
        resultTextView.text = result
    }
}
