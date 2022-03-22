package ru.johnnygomezzz.currencyrate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initConverterButtonOnClickListener();
        initCurrencyListButtonOnClickListener();
    }

    private void initConverterButtonOnClickListener() {
        Button converterButton = findViewById(R.id.open_converter);
        converterButton.setOnClickListener(view -> {
            startConverterIntent();
        });
    }

    private void startConverterIntent() {
        Intent converterIntent = new Intent(this, ConverterActivity.class);
        startActivity(converterIntent);
    }

    private void startCurrencyListIntent() {
        Intent currencyListIntent = new Intent(this, CurrencyListActivity.class);
        startActivity(currencyListIntent);
    }

    private void initCurrencyListButtonOnClickListener() {
        Button currencyListButton = findViewById(R.id.open_currency_list);
        currencyListButton.setOnClickListener(view -> {
            startCurrencyListIntent();
        });
    }
}