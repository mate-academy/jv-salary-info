package Tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String[] data = new String[]{"26.04.2019 John 4 50", "05.04.2019 Andrew 3 200",
                "10.04.2019 John 7 100", "22.04.2019 Kate 9 100", "25.06.2019 John 11 50"
                , "26.04.2019 Andrew 3 150", "13.02.2019 John 7 100", "26.04.2019 Kate 9 100"};

        String[] names = new String[]{"John", "Andrew", "Kate"};
        String dateFrom = "01.04.2019";
        String dateTo = "30.04.2019";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder builderOfValidRange = new StringBuilder();

        for (String dataItem : data) {
            String[] parts = dataItem.split(" ");
            String dateString = parts[0];
            try {
                Date date = dateFormat.parse(dateString);
                Date fromDate = dateFormat.parse(dateFrom);
                Date toDate = dateFormat.parse(dateTo);
                if (date.compareTo(fromDate) >= 0 && date.compareTo(toDate) <= 0) {
                    builderOfValidRange.append(parts[1] + " ")
                            .append(Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]) + ",").toString();
                }
            } catch (ParseException e) {
                System.out.println("date format exseption");
            }
        }
        StringBuilder fin = new StringBuilder("Report for period " + dateFrom + " - " + dateTo + "\n");
        String[] records = builderOfValidRange.toString().split(",");
        Map<String, Integer> totalSumMap = new HashMap<>();
        for (String name : names) {
            totalSumMap.put(name, 0);
        }

        for (String record : records) {
            String[] parts = record.split(" ");
            String name = parts[0];
            int amount = Integer.parseInt(parts[1].replace(",", ""));
            totalSumMap.put(name, totalSumMap.get(name) + amount);
        }
        for (String name : names) {
            fin.append(name ).append(" ").append(totalSumMap.get(name)).append("\n");
        }
        fin.toString();
        System.out.println(fin);
    }
}

