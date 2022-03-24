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
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pd;
    private final String JSON = "https://www.cbr-xml-daily.ru/daily_json.js";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JsonTask().execute(JSON);
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

    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder buffer = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                    Log.d("Response: ", "> " + line);

                }

                return buffer.toString();


            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()) {
                pd.dismiss();
            }
            Gson gson = new Gson();
            Currency.Page page = gson.fromJson(result, Currency.Page.class);

            HashMap<String, BigDecimal> valutesValueList = new HashMap<>(Currency.getCodesValuesList(page));
            String currencyDate = Currency.getCurrentDate(page);

            Button converterButton = findViewById(R.id.open_converter);
            converterButton.setOnClickListener(view -> {
                Intent converterIntent = new Intent(MainActivity.this, ConverterActivity.class);
                converterIntent.putExtra(ConverterActivity.EXTRA_LIST, valutesValueList);
                startActivity(converterIntent);
            });

            Button currencyListButton = findViewById(R.id.open_currency_list);
            currencyListButton.setOnClickListener(view -> {
                Intent currencyListIntent = new Intent(MainActivity.this, CurrencyListActivity.class);
                currencyListIntent.putExtra(CurrencyListActivity.EXTRA_LIST, valutesValueList);
                currencyListIntent.putExtra(CurrencyListActivity.EXTRA_DATE, currencyDate);
                startActivity(currencyListIntent);
            });
        }
    }
}
