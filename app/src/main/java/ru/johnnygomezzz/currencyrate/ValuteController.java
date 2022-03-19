package ru.johnnygomezzz.currencyrate;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class ValuteController extends AsyncTask<String, Void, String> {

//    private String readUrl(String urlString) throws Exception {
//        BufferedReader reader = null;
//        try {
//            URL url = new URL(urlString);
//            reader = new BufferedReader(new InputStreamReader(url.openStream()));
//            StringBuffer buffer = new StringBuffer();
//            int read;
//            char[] chars = new char[1024];
//            while ((read = reader.read(chars)) != -1)
//                buffer.append(chars, 0, read);
//
//            return buffer.toString();
//        } finally {
//            if (reader != null)
//                reader.close();
//        }
//    }

    @Override
    protected String doInBackground(String... urls) {
        BufferedReader reader = null;
        try {
            URL url = new URL(urls[0]);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public class Page {
        @SerializedName("Date")
        public String date;

        @SerializedName("Valute")
        public Map<String, Valute> codes;
    }

    public class Valute {
        @SerializedName("CharCode")
        public String charCode;

        @SerializedName("Nominal")
        public Integer nominal;

        @SerializedName("Name")
        public String name;

        @SerializedName("Value")
        public Float value;
    }

    public String getCurrencyValue(String code) {
        String json = doInBackground("https://www.cbr-xml-daily.ru/daily_json.js");

        Gson gson = new Gson();
        Page page = gson.fromJson(json, Page.class);

        return page.codes.get(code).name + " " + page.codes.get(code).value;
    }

}
