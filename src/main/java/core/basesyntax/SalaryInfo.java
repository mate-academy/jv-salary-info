package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_COLUMN = 0;
    private static final int NAME_COLUMN = 1;
    private static final int AMOUNT_HOURS = 2;
    private static final int RATE_COLUMN = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        LocalDate dateFromFormat = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToFormat = LocalDate.parse(dateTo, FORMATTER);
        int sumSalary = 0;
        for (String name : names) {
            for (int i = 0; i < data.length; i++) {
                String[] record = data[i].split(" ");
                if (record[NAME_COLUMN].equals(name)
                        && !LocalDate.parse(record[DATE_COLUMN], FORMATTER)
                        .isBefore(dateFromFormat)
                        && !LocalDate.parse(record[DATE_COLUMN], FORMATTER)
                        .isAfter(dateToFormat)) {
                    int salary = Integer.parseInt(record[AMOUNT_HOURS])
                            * Integer.parseInt(record[RATE_COLUMN]);
                    sumSalary += salary;
                }
            }
            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(sumSalary);
            sumSalary = 0;
        }
        return report.toString();
    }
}
