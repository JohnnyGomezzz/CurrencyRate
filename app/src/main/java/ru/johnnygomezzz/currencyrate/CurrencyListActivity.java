package ru.johnnygomezzz.currencyrate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CurrencyListActivity extends AppCompatActivity {

    public static final String EXTRA_LIST = "list";
    public static final String EXTRA_DATE = "date";
    private HashMap<String, BigDecimal> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);

        TextView textView = findViewById(R.id.list);
        TextView dateView = findViewById(R.id.date);

        Intent intent = getIntent();
        hashMap = (HashMap<String, BigDecimal>) intent.getSerializableExtra("list");
        String date = "Курс валют на " + intent.getStringExtra(EXTRA_DATE);

        dateView.setText(date);
        textView.setText(getList());
    }

    public String getList() {
        String listItem = "";
        for (Map.Entry<String, BigDecimal> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            BigDecimal value = entry.getValue();
            listItem = listItem + "\n" + key + " : " + value;
        }
        return listItem;
    }
}