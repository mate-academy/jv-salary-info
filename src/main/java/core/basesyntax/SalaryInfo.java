package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final int DAY_INDEX = 0;
    private static final int EMPLOYEE_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PAY_FOR_HOUR_INDEX = 3;

    private static Boolean isDayInRange(String day, String startOfRange, String finalOfRange) {
        LocalDate startDay = LocalDate.parse(startOfRange, FORMATTER);
        LocalDate finalDay = LocalDate.parse(finalOfRange, FORMATTER);
        LocalDate checkDay = LocalDate.parse(day, FORMATTER);
        return (checkDay.equals(startDay) || checkDay.isAfter(startDay))
                && (checkDay.equals(finalDay)
                || checkDay.isBefore(finalDay));
    }

    private static StringBuilder getStartOfReport(String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        return builder;
    }

    private static String getEmployee(String record) {
        return record.split(" ")[EMPLOYEE_INDEX];
    }

    private static String getWorkingDay(String record) {
        return record.split(" ")[DAY_INDEX];
    }

    private static int countDayPayment(String record) {
        int workingHours = Integer.parseInt(record.split(" ")[HOURS_INDEX]);
        int payForHour = Integer.parseInt(record.split(" ")[PAY_FOR_HOUR_INDEX]);
        return workingHours * payForHour;
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = getStartOfReport(dateFrom, dateTo);
        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                if (name != null && name.equals(getEmployee(record))) {
                    if (isDayInRange(getWorkingDay(record), dateFrom, dateTo)) {
                        salary += countDayPayment(record);
                    }
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
