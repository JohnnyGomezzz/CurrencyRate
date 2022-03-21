package ru.johnnygomezzz.currencyrate;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Map;

public class Currency {

    public class Page {
        @SerializedName("Timestamp")
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

        @Override
        public String toString() {
            return "\n" + value +
                    " за " + nominal + " " + name;
        }
    }

    public String getCurrencyValue(Page page, String code) {
        return page.codes.get(code).name + " "
                + page.codes.get(code).value.toString()
                + " за " + page.codes.get(code).nominal
                + " у.е.";
    }

    public static String getAllValues(Page page) {
        return page.codes.values().toString().replaceAll("(^\\[|\\]$)", "");
    }

    public static String getCurrentDate(Page page) {
        return String.format(page.date, new SimpleDateFormat("d, MMMM, yyyy"));
    }


}
