package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SEPARATOR = "\\s";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Map<String, Integer> employeesSalary = new LinkedHashMap<>();
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);

        for (String name : names) {
            employeesSalary.put(name, 0);
        }

        for (String dataItem : data) {
            String[] splitData = dataItem.split(SEPARATOR);
            LocalDate date = LocalDate.parse(splitData[DATE_INDEX], FORMATTER);
            String name = splitData[NAME_INDEX];
            int hours = Integer.parseInt(splitData[HOURS_INDEX]);
            int incomePerHour = Integer.parseInt(splitData[INCOME_PER_HOUR_INDEX]);
            int isDateAfter = date.compareTo(startDate);
            int isDateBefore = date.compareTo(endDate);

            if (isDateAfter >= 0 && isDateBefore <= 0) {
                int salary = hours * incomePerHour;
                employeesSalary.computeIfPresent(name, (key, value) -> value + salary);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (Map.Entry<String, Integer> entry : employeesSalary.entrySet()) {
            stringBuilder.append(System.lineSeparator()).append(entry.getKey()).append(" - ");
            stringBuilder.append(entry.getValue());
        }

        return stringBuilder.toString();
    }
}
