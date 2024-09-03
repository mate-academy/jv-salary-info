package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int WORKING_DAY_POSITION = 0;
    private static final int EMPLOEE_NAME_POSITION = 1;
    private static final int HOUR_COUNT_POSITION = 2;
    private static final int SALARY_PER_HOUR_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];
        for (String record : data) {
            String[] parsedData = record.split(" ");
            String workingDay = parsedData[WORKING_DAY_POSITION];
            String employeeName = parsedData[EMPLOEE_NAME_POSITION];
            int hoursWorked = Integer.parseInt(parsedData[HOUR_COUNT_POSITION]);
            int salaryPerHour = Integer.parseInt(parsedData[SALARY_PER_HOUR_POSITION]);
            if (isDateWithinRange(workingDay, dateFrom, dateTo)) {
                int earnedSalary = hoursWorked * salaryPerHour;
                for (int i = 0; i < names.length; i++) {
                    if (employeeName.equals(names[i])) {
                        salaries[i] += earnedSalary;
                        break;
                    }
                }
            }
        }
        return formatSalaryReport(names, dateFrom, dateTo, salaries);
    }

    private boolean isDateWithinRange(String dateToCheck, String dateFrom, String dateTo) {
        LocalDate startDay = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate endDay = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        LocalDate checkDay = LocalDate.parse(dateToCheck, DATE_TIME_FORMATTER);
        return (!checkDay.isBefore(startDay) && !checkDay.isAfter(endDay));
    }

    private String formatSalaryReport(String[] names, String dateFrom,
                                      String dateTo, int[] salaries) {
        StringBuilder resultMessage = new StringBuilder();
        resultMessage.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            resultMessage.append(System.lineSeparator())
                    .append(names[i]).append(" - ")
                    .append(salaries[i]);
        }
        return resultMessage.toString();
    }
}
