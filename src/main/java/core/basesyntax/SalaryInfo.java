package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_WORKING_HOURS = 2;
    private static final int INDEX_AMOUNT = 3;
    private static final String SEPARATOR = " ";
    private static final String DATA_FORMATTER_PATTERN = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        report.append(createReports(names, data, dateFrom, dateTo));
        return report.toString();
    }

    private String createReports(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATA_FORMATTER_PATTERN);
        StringBuilder report = new StringBuilder();
        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);
        for (String employee : names) {
            int salary = 0;
            for (String entry : data) {
                String[] parts = entry.split(SEPARATOR);
                String date = parts[INDEX_DATE];
                String name = parts[INDEX_NAME];
                int hours = Integer.parseInt(parts[INDEX_WORKING_HOURS]);
                int incomePerHour = Integer.parseInt(parts[INDEX_AMOUNT]);
                LocalDate entryDate = LocalDate.parse(date, dateFormatter);

                if (compareDates(entryDate, fromDate, toDate)) {
                    if (employee.equals(name)) {
                        salary += hours * incomePerHour;
                    }
                }
            }
            report.append(System.lineSeparator()).append(employee).append(" - ").append(salary);
        }
        return report.toString();
    }

    private boolean compareDates(LocalDate entryDate, LocalDate fromDate, LocalDate toDate) {
        return !entryDate.isBefore(fromDate) && !entryDate.isAfter(toDate);
    }
}
