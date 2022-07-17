package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX_ = 2;
    private static final int SALARY_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER).plusDays(1);
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            report.append(System.lineSeparator());
            for (String line : data) {
                String[] value = line.split(" ");
                if (value[1].equals(name)) {
                    LocalDate workDay = LocalDate.parse(value[0], FORMATTER);
                    if (workDay.isAfter(startDate) && workDay.isBefore(endDate)) {
                        salary = salary + Integer.parseInt(value[2])
                                * Integer.parseInt(value[3]);
                    }
                }
            }
            report.append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
