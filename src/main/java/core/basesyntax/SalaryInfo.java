package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int SALARY_PER_MONTH = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int totalSalary = 0;
            for (String datum : data) {
                String[] record = datum.split(" ");
                LocalDate currentDate = LocalDate.parse(record[DATE], FORMATTER);
                if (name.equals(record[NAME])
                            && !currentDate.isBefore(localDateFrom)
                        && !currentDate.isAfter(localDateTo)) {
                    totalSalary += Integer.parseInt(record[HOURS])
                            * Integer.parseInt(record[SALARY_PER_MONTH]);
                }
            }
            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        return report.toString();
    }
}
