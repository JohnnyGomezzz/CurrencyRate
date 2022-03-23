package ru.johnnygomezzz.currencyrate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConverterActivity extends AppCompatActivity {

    public static final String EXTRA_LIST = "list";
    private HashMap<String, Float> hashMap;

    private Spinner valutesList;
    private EditText sum;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        Intent intent = getIntent();
        hashMap = (HashMap<String, Float>)intent.getSerializableExtra("list");
//        Log.v("HashMapTest", hashMap.get("NOK"));

        valutesList = findViewById(R.id.valutes_list);
        sum = findViewById(R.id.sum);
        result = findViewById(R.id.result_field);

        result.setText(hashMap.toString());

        initResultButtonOnClickListener();
    }

    private void initResultButtonOnClickListener() {
        Button resultButton = findViewById(R.id.button_result);
        resultButton.setOnClickListener(view -> {
            String valuteCode = String.valueOf(valutesList.getSelectedItem());
            Float rubleSum = Float.parseFloat(sum.getText().toString());

            Float resultText = rubleSum / hashMap.get(valuteCode);

            result.setText(resultText.toString());
        });
    }
}