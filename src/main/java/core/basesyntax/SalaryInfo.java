package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATA_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            int salary = calculateSalaryForEmployee(name, data, fromDate, toDate);
            builder.append(String.format("%s - %d%n", name, salary));
        }
        return builder.toString().trim();
    }

    private int calculateSalaryForEmployee(String name, String[] data, LocalDate fromDate,
                                           LocalDate toDate) {
        int totalSalary = 0;
        for (String item : data) {
            String[] items = item.split(" ");
            LocalDate itemDate = LocalDate.parse(items[DATA_INDEX], FORMATTER);
            if (!itemDate.isBefore(fromDate) && !itemDate.isAfter(toDate)
                    && name.equals(items[NAME_INDEX])) {
                int hours = Integer.parseInt(items[HOURS_INDEX]);
                int incomePerHour = Integer.parseInt(items[INCOME_INDEX]);
                totalSalary += hours * incomePerHour;
            }
        }
        return totalSalary;
    }
}
