package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String entry : data) {
                String [] parts = entry.split(" ");
                LocalDate dataDate = LocalDate.parse(parts[DATE_INDEX], FORMATTER);
                String employeeName = parts[NAME_INDEX];
                int hours = Integer.parseInt(parts[HOURS_INDEX]);
                int incomePerHour = Integer.parseInt(parts[INCOME_PER_HOUR_INDEX]);
                if (name.equals(employeeName)
                        && !dataDate.isBefore(localDateDateFrom)
                        && !dataDate.isAfter(localDateDateTo)) {
                    salary += incomePerHour * hours;
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
