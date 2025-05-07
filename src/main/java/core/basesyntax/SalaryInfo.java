package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PER_HOUR_INDEX = 3;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String info : data) {
                String[] parts = info.split(" ");
                LocalDate workingDays = LocalDate.parse(parts[DATE_INDEX], DATE_FORMATTER);
                String employeeName = parts[NAME_INDEX];
                int workedHours = Integer.parseInt(parts[HOURS_INDEX]);
                int payPerHour = Integer.parseInt(parts[PER_HOUR_INDEX]);
                if (employeeName.equals(name)
                        && (workingDays.isAfter(fromDate) || workingDays.isEqual(fromDate))
                        && (workingDays.isBefore(toDate) || workingDays.isEqual(toDate))) {
                    salary += payPerHour * workedHours;
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
