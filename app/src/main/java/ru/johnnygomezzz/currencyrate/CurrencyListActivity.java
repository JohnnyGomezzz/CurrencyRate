package ru.johnnygomezzz.currencyrate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CurrencyListActivity extends AppCompatActivity {

    private TextView textView;
    private TextView dateView;
//    private ProgressDialog pd;
//    private final String JSON = "https://www.cbr-xml-daily.ru/daily_json.js";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);

        textView = findViewById(R.id.list);
        dateView = findViewById(R.id.date);

//        new JsonTask().execute(JSON);
    }

//    private class JsonTask extends AsyncTask<String, String, String> {
//
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            pd = new ProgressDialog(CurrencyListActivity.this);
//            pd.setMessage("Please wait");
//            pd.setCancelable(false);
//            pd.show();
//        }
//
//        protected String doInBackground(String... params) {
//
//            HttpURLConnection connection = null;
//            BufferedReader reader = null;
//
//            try {
//                URL url = new URL(params[0]);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.connect();
//
//
//                InputStream stream = connection.getInputStream();
//
//                reader = new BufferedReader(new InputStreamReader(stream));
//
//                StringBuilder buffer = new StringBuilder();
//                String line;
//
//                while ((line = reader.readLine()) != null) {
//                    buffer.append(line).append("\n");
//                    Log.d("Response: ", "> " + line);
//
//                }
//
//                return buffer.toString();
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (connection != null) {
//                    connection.disconnect();
//                }
//                try {
//                    if (reader != null) {
//                        reader.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//
//        @SuppressLint("SetTextI18n")
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            if (pd.isShowing()){
//                pd.dismiss();
//            }
//            Gson gson = new Gson();
//            Currency.Page page = gson.fromJson(result, Currency.Page.class);
//
//            HashMap<String, Float> valutesValueList = new HashMap<String, Float>();
//            valutesValueList.putAll(Currency.getCodesValuesList(page));
//
//            Intent converterIntent = new Intent(CurrencyListActivity.this, ConverterActivity.class);
//            converterIntent.putExtra(ConverterActivity.EXTRA_LIST, valutesValueList);
//            startActivity(converterIntent);
//
//            dateView.setText("Курс валют на " + Currency.getCurrentDate(page));
//            textView.setText(Currency.getAllValues(page));
//        }
//    }
}