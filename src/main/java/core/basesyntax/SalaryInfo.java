package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SPACE = " ";
    private static final String DASH = " - ";
    private static final int DATE_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int MONEY_PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(DASH).append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String employeeData : data) {
                String[] separatedData = employeeData.split(SPACE);
                String date = separatedData[DATE_INDEX];
                String employeeName = separatedData[EMPLOYEE_NAME_INDEX];
                int hours = Integer.parseInt(separatedData[HOURS_INDEX]);
                int moneyPerHour = Integer.parseInt(separatedData[MONEY_PER_HOUR_INDEX]);
                LocalDate localDate = LocalDate.parse(date, FORMATTER);
                LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
                if (!localDate.isBefore(fromDate) && !localDate.isAfter(toDate)
                        && employeeName.equals(name)) {
                        salary += hours * moneyPerHour;
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name).append(DASH).append(salary);
        }
        return stringBuilder.toString();
    }
}
