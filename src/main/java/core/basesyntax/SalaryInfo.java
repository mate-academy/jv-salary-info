package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try {
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);

            Map<String, Integer> earnings = IntStream.range(0, data.length)
                    .mapToObj(i -> data[i].split(" "))
                    .filter(parts -> {
                        try {
                            Date recordDate = dateFormat.parse(parts[0]);
                            return recordDate.compareTo(fromDate) >= 0 && recordDate.compareTo(toDate) <= 0;
                        } catch (ParseException e) {
                            e.printStackTrace();
                            return false;
                        }
                    })
                    .collect(Collectors.toMap(parts -> parts[1],
                            parts -> Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]),
                            Integer::sum));

            StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo + "\n");
            for (String name : names) {
                int earnedMoney = earnings.getOrDefault(name, 0);
                result.append(name).append(" - ").append(earnedMoney).append("\n");
            }

            return result.toString().trim();
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid date format";
        }
    }
}