package ru.johnnygomezzz.currencyrate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class ConverterActivity extends AppCompatActivity {

    private Spinner valutesList;
    private EditText sum;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        valutesList = findViewById(R.id.valutes_list);
        sum = findViewById(R.id.sum);
        result = findViewById(R.id.result_field);

        initResultButtonOnClickListener();
    }

    private void initResultButtonOnClickListener() {
        Button resultButton = findViewById(R.id.button_result);
        resultButton.setOnClickListener(view -> {
            String valuteCode = String.valueOf(valutesList.getSelectedItem());
            int rubleSum = Integer.parseInt(sum.getText().toString());

            String resultText = rubleSum + " / " + valuteCode;

            result.setText(resultText);
        });
    }
}