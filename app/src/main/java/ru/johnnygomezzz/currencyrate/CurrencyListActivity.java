package ru.johnnygomezzz.currencyrate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class CurrencyListActivity extends AppCompatActivity {

    public static final String EXTRA_LIST = "list";
    public static final String EXTRA_DATE = "date";
    private HashMap<Float, String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);

        TextView textView = findViewById(R.id.list);
        TextView dateView = findViewById(R.id.date);

        Intent intent = getIntent();
        hashMap = (HashMap<Float, String>) intent.getSerializableExtra("list");
        String date = "Курс валют на " + intent.getStringExtra(EXTRA_DATE);

        dateView.setText(date);
        textView.setText(getList());
    }

    public String getList() {
        StringBuilder listItem = new StringBuilder();
        for (Map.Entry<Float, String> entry : hashMap.entrySet()) {
            Float key = entry.getKey();
            String value = entry.getValue();
            listItem.append(value).append(" : ").append(key).append("\n");
        }
        return listItem.toString();
    }
}