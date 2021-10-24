package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS = 2;
    private static final int UNITS_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate start = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate end = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder report = new StringBuilder("Report for period ");
        report.append(dateFrom).append(" - ").append(dateTo);

        int salary = 0;

        for (String name : names) {
            for (String employee : data) {
                String[] info = employee.split(" ");
                LocalDate workDate = LocalDate.parse(info[DATE_INDEX],DATE_TIME_FORMATTER);
                if (name.equals(info[NAME_INDEX]) && (workDate.isAfter(start)
                        || workDate.equals(start)) && (workDate.isBefore(end)
                        || workDate.equals(end))) {
                    salary += Integer.parseInt(info[WORKING_HOURS])
                            * Integer.parseInt(info[UNITS_PER_HOUR]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
            salary = 0;
        }
        return report.toString();
    }
}
