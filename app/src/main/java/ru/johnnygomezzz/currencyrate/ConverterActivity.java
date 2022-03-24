package ru.johnnygomezzz.currencyrate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.HashMap;

public class ConverterActivity extends AppCompatActivity {

    public static final String EXTRA_LIST = "list";
    private HashMap<String, BigDecimal> hashMap;

    private Spinner valutesList;
    private EditText sum;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        Intent intent = getIntent();
        hashMap = (HashMap<String, BigDecimal>)intent.getSerializableExtra("list");

        valutesList = findViewById(R.id.valutes_list);
        sum = findViewById(R.id.sum);
        result = findViewById(R.id.result_field);

        initResultButtonOnClickListener();
    }

    private void initResultButtonOnClickListener() {
        Button resultButton = findViewById(R.id.button_result);
        resultButton.setOnClickListener(view -> {
            String valuteCode = String.valueOf(valutesList.getSelectedItem());
            BigDecimal rubleSum = new BigDecimal(sum.getText().toString());

            BigDecimal resultText = rubleSum.divide(hashMap.get(valuteCode), 2, BigDecimal.ROUND_DOWN);

            result.setText(resultText.toString());
        });
    }
}