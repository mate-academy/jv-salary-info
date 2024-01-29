package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date fromDate;
        Date toDate;
        try {
            fromDate = dateFormat.parse(dateFrom);
            toDate = dateFormat.parse(dateTo);
        } catch (ParseException e) {
            return "Invalid date format";
        }

        Map<String, Integer> earnings = new HashMap<>();

        for (int i = 0; i < data.length; i++) {
            String[] parts = data[i].split(" ");
            String currentDate = parts[0];
            String currentName = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int incomePerHour = Integer.parseInt(parts[3]);

            try {
                Date currentDateParsed = dateFormat.parse(currentDate);
                if (currentDateParsed.compareTo(fromDate) >= 0 && currentDateParsed
                        .compareTo(toDate) <= 0) {
                    earnings.put(currentName, earnings.getOrDefault(currentName,
                            0) + (hoursWorked * incomePerHour));
                }
            } catch (ParseException e) {
                return "Invalid date format in data";
            }
        }

        StringBuilder result = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + "\n");

        for (String name : names) {
            int earned = earnings.getOrDefault(name, 0);
            result.append(name).append(" - ").append(earned).append("\n");
        }

        return result.toString().trim();
    }
}
