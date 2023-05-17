package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
            .append(dateFrom).append(" - ").append(dateTo).append("\n");

        for (int i = 0; i < names.length; i++) {
            int totalSalary = 0;
            for (String line : data) {
                String[] parts = line.split(" ");
                LocalDate date = LocalDate.parse(parts[DATE_INDEX], DATE_FORMATTER);
                String employeeName = parts[NAME_INDEX];
                int hours = Integer.parseInt(parts[HOURS_INDEX]);
                int rate = Integer.parseInt(parts[RATE_INDEX]);

                if (employeeName.equals(names[i])
                        && !date.isBefore(fromDate) && !date.isAfter(toDate)) {
                    int salary = hours * rate;
                    totalSalary += salary;
                }
            }
            result.append(names[i]).append(" - ")
                .append(totalSalary).append(i != names.length - 1 ? "\n" : "");
        }
        return result.toString();
    }
}
