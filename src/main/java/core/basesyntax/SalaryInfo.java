package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SPACE = " ";
    private static final int DATE_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int MONEY_PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DASH = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateFinish = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder res = new StringBuilder("Report for period ").append(dateFrom)
                .append(DASH).append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String employeeData : data) {
                String[] dataArray = employeeData.split(SPACE);
                String date = dataArray[DATE_INDEX];
                LocalDate dateToCompare = LocalDate.parse(date, FORMATTER);
                String employeeName = dataArray[EMPLOYEE_NAME_INDEX];
                int hours = Integer.parseInt(dataArray[HOURS_INDEX]);
                int moneyPerHour = Integer.parseInt(dataArray[MONEY_PER_HOUR_INDEX]);
                if (!dateToCompare.isBefore(dateStart) && !dateToCompare.isAfter(dateFinish)
                        && employeeName.equals(name)) {
                    salary += hours * moneyPerHour;
                }
            }
            res.append(LINE_SEPARATOR).append(name).append(DASH).append(salary);
        }
        return res.toString();
    }
}
