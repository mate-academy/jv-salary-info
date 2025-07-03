package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];
        for (String workingRecord : data) {
            String[] recordParts = workingRecord.split(" ");
            String workingDate = recordParts[DATE_INDEX];
            if (isDateWithinRange(workingDate, dateFrom, dateTo)) {
                String employeeName = recordParts[NAME_INDEX];
                int hoursWorked = Integer.parseInt(recordParts[HOURS_INDEX]);
                int hourlyRate = Integer.parseInt(recordParts[RATE_INDEX]);
                int dailySalary = hoursWorked * hourlyRate;
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(employeeName)) {
                        salaries[i] += dailySalary;
                        break;
                    }
                }
            }
        }
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }
        return report.toString();
    }

    private boolean isDateWithinRange(String targetDate, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, FORMATTER);
        LocalDate end = LocalDate.parse(endDate, FORMATTER);
        LocalDate target = LocalDate.parse(targetDate, FORMATTER);
        return (target.isEqual(start) || target.isAfter(start))
                && (target.isEqual(end) || target.isBefore(end));
    }
}
