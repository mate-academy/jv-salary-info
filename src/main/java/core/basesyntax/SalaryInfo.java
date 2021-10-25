package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                // Get current value or put a new name with a value
                // if this name was not there before
                currentValue = employeesSalary.putIfAbsent(splitData[1], moneyPerDay);
                if (currentValue != null) {
                    // Update current value
                    employeesSalary.replace(splitData[1], currentValue + moneyPerDay);
                }
            }
        }

        StringBuilder result = new StringBuilder(
                String.format("Report for period %s - %s%s", dateFrom, dateTo, System.lineSeparator()));
        for (String name : names) {
            result.append(name)
                    .append(" - ")
                    .append(employeesSalary.getOrDefault(name, 0))
                    .append(name.equals(names[names.length - 1]) ? "" : System.lineSeparator());
        }
        return result.toString();
    }
}
