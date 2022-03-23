package ru.johnnygomezzz.currencyrate;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
                    " рублей за " + nominal + " " + name;
        }
    }

    public Float getCurrencyValue(Page page, String code) {
        return Objects.requireNonNull(page.codes.get(code)).value;
    }

    public String getCurrencyCode(Page page, String code) {
        return Objects.requireNonNull(page.codes.get(code)).charCode;
    }

    public static Map<String, Float> getCodesValuesList(Page page) {
        Map<String, Float> codesValues = new HashMap<>();

        for (Map.Entry<String, Valute> entry : page.codes.entrySet()) {
            Valute number = entry.getValue();
            codesValues.put(number.charCode, number.value);
        }
        return codesValues;
    }

    public static String getAllValues(Page page) {
        return page.codes.values().toString().replaceAll("(^\\[|\\]$)", "");
    }

    public static String getCurrentDate(Page page) {
        return String.format(page.date, new SimpleDateFormat("d, MMMM, yyyy"));
    }


}
