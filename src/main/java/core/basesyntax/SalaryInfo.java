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

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = getStartOfReport(dateFrom, dateTo);
        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                if (name != null && name.equals(getData(record, EMPLOYEE_INDEX))) {
                    if (isDayInRange(getData(record, DAY_INDEX), dateFrom, dateTo)) {
                        salary += countDayPayment(record);
                    }
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }

    private Boolean isDayInRange(String day, String startOfRange, String finalOfRange) {
        LocalDate startDay = LocalDate.parse(startOfRange, FORMATTER);
        LocalDate finalDay = LocalDate.parse(finalOfRange, FORMATTER);
        LocalDate checkDay = LocalDate.parse(day, FORMATTER);
        return (checkDay.equals(startDay) || checkDay.isAfter(startDay))
                && (checkDay.equals(finalDay)
                || checkDay.isBefore(finalDay));
    }

    private StringBuilder getStartOfReport(String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        return builder;
    }

    private int countDayPayment(String record) {
        int workingHours = Integer.parseInt(getData(record, HOURS_INDEX));
        int payForHour = Integer.parseInt(getData(record, PAY_FOR_HOUR_INDEX));
        return workingHours * payForHour;
    }

    private String getData(String record, int index) {
        return record.split(" ")[index];
    }
}
