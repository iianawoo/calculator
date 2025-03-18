package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private String currentInput = "";
    private String operator = "";
    private double firstValue = 0;
    private boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);

        setupButtonListeners();
    }

    private void setupButtonListeners() {
        int[] numberButtons = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        };

        int[] operatorButtons = {
                R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide
        };

        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(this::onNumberClick);
        }

        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(this::onOperatorClick);
        }

        findViewById(R.id.btnEqual).setOnClickListener(this::onEqualClick);
        findViewById(R.id.btnClear).setOnClickListener(this::onClearClick);
        findViewById(R.id.btnSign).setOnClickListener(this::onSignClick);
        findViewById(R.id.btnPercent).setOnClickListener(this::onPercentClick);
        findViewById(R.id.btnDot).setOnClickListener(this::onDotClick);
    }

    private void onNumberClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();

        if (isNewInput) {
            currentInput = buttonText;
            isNewInput = false;
        } else {
            currentInput += buttonText;
        }

        tvResult.setText(currentInput);
    }

    private void onOperatorClick(View view) {
        MaterialButton button = (MaterialButton) view;
        operator = button.getText().toString();

        firstValue = Double.parseDouble(currentInput);
        isNewInput = true;
    }

    private void onEqualClick(View view) {
        if (!operator.isEmpty() && !currentInput.isEmpty()) {
            double secondValue = Double.parseDouble(currentInput);
            double result = calculate(firstValue, secondValue, operator);
            tvResult.setText(String.valueOf(result));

            currentInput = String.valueOf(result);
            operator = "";
            isNewInput = true;
        }
    }

    private void onClearClick(View view) {
        currentInput = "";
        firstValue = 0;
        operator = "";
        isNewInput = true;
        tvResult.setText("0");
    }

    private void onSignClick(View view) {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput) * -1;
            currentInput = String.valueOf(value);
            tvResult.setText(currentInput);
        }
    }

    private void onPercentClick(View view) {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput) / 100;
            currentInput = String.valueOf(value);
            tvResult.setText(currentInput);
        }
    }

    private void onDotClick(View view) {
        if (!currentInput.contains(".")) {
            currentInput += ".";
            tvResult.setText(currentInput);
        }
    }

    private double calculate(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "×": return a * b;
            case "÷": return (b == 0) ? 0 : a / b;
            default: return 0;
        }
    }
}
