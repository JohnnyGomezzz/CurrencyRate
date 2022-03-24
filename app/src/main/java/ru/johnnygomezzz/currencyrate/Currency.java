package ru.johnnygomezzz.currencyrate;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.HashMap;
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

        @NonNull
        @Override
        public String toString() {
            return "\n" + value +
                    " рублей за " + nominal + " " + name;
        }
    }

    public static Map<String, BigDecimal> getCodesValuesList(Page page) {
        Map<String, BigDecimal> codesValues = new HashMap<>();

        for (Map.Entry<String, Valute> entry : page.codes.entrySet()) {
            Valute val = entry.getValue();

            BigDecimal value = new BigDecimal(Float.toString(val.value));
            BigDecimal nominal = new BigDecimal(Float.toString(val.nominal));

            codesValues.put(val.charCode, value.setScale(2, BigDecimal.ROUND_DOWN)
                    .divide(nominal, 2, BigDecimal.ROUND_DOWN));
        }
        return codesValues;
    }

    public static Map<Float, String> getNamesValuesList(Page page) {
        Map<Float, String> namesValues = new HashMap<>();

        for (Map.Entry<String, Valute> entry : page.codes.entrySet()) {
            Valute val = entry.getValue();

            String nominalAndName = val.nominal + " " + val.name + " (" + val.charCode + ") ";
            namesValues.put(val.value, nominalAndName);
        }
        return namesValues;
    }

    public static String getCurrentDate(Page page) {
        String[] date = page.date.split("T");
        String[] rightDate = date[0].split("-");
        return rightDate[2] + "." + rightDate[1] + "." + rightDate[0];
    }


}
