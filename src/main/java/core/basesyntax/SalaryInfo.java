package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        HashMap<String, Integer> employeesSalary = new HashMap<>();
        for (String date: data) {
            String[] splitData = date.split(" ");
            if (LocalDate.parse(splitData[DATE_INDEX], DATE_TIME_FORMATTER)
                    .compareTo(LocalDate.parse(dateFrom, DATE_TIME_FORMATTER)) >= 0
                    && LocalDate.parse(splitData[DATE_INDEX], DATE_TIME_FORMATTER)
                    .compareTo(LocalDate.parse(dateTo, DATE_TIME_FORMATTER)) <= 0) {
                Integer moneyPerDay = Integer.parseInt(splitData[HOURS_INDEX])
                                * Integer.parseInt(splitData[INCOME_PER_HOUR_INDEX]);
                Integer currentValue = employeesSalary.putIfAbsent(splitData[NAME_INDEX], moneyPerDay);
                if (currentValue != null) {
                    employeesSalary.replace(splitData[NAME_INDEX], currentValue + moneyPerDay);
                }
            }
        }
        StringBuilder result = new StringBuilder(
                String.format("Report for period %s - %s", dateFrom, dateTo));
        for (String name : names) {
            result.append(System.lineSeparator()).append(name)
                    .append(" - ")
                    .append(employeesSalary.getOrDefault(name, 0));
        }
        return result.toString();
    }
}
