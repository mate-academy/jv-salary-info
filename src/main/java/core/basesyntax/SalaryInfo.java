package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        HashMap<String, Integer> employeesSalary = new HashMap<>();
        Integer moneyPerDay;
        Integer currentValue;
        for (String date: data) {
            String[] splitData = date.split(" ");
            if (LocalDate.parse(splitData[0], DATE_TIME_FORMATTER)
                    .compareTo(LocalDate.parse(dateFrom, DATE_TIME_FORMATTER)) >= 0
                    && LocalDate.parse(splitData[0], DATE_TIME_FORMATTER)
                    .compareTo(LocalDate.parse(dateTo, DATE_TIME_FORMATTER)) <= 0) {
                moneyPerDay = Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                currentValue = employeesSalary.putIfAbsent(splitData[1], moneyPerDay);
                if (currentValue != null) {
                    // Updating current value
                    employeesSalary.replace(splitData[1], currentValue + moneyPerDay);
                }
            }
        }

        ArrayList<String> result = new ArrayList<>();
        result.add(String.format("Report for period %s - %s", dateFrom, dateTo));
        for (String name : names) {
            result.add(name + " - " + employeesSalary.getOrDefault(name, 0));
        }
        return String.join("\n", result);
    }
}
