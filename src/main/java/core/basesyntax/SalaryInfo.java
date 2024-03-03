package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder report = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);

        if (dateFrom == null || dateFrom.isEmpty() || dateTo == null || dateTo.isEmpty()
                || names.length == 0 || data.length == 0) {
            throw new IllegalArgumentException("input data is not correct");
        }

        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (String entry : data) {
                String[] parts = entry.split(" ");
                if (parts.length == 4) {
                    LocalDate entryDate = LocalDate.parse(parts[DATE_INDEX], FORMATTER);
                    String name = parts[NAME_INDEX];
                    int hoursWorked = Integer.parseInt(parts[HOURS_INDEX]);
                    int incomePerHour = Integer.parseInt(parts[SALARY_INDEX]);
                    if (!entryDate.isAfter(toDate) && !entryDate.isBefore(fromDate)) {
                        if (names[i].equals(name)) {
                            salary += hoursWorked * incomePerHour;
                        }
                    }

                } else {
                    return "Invalid data entry format";
                }
            }
            report.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salary);
        }

        return report.toString();
    }
}
