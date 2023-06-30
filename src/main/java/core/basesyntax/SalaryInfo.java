package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final String SPACE = " ";
    private static final String DOT = "\\.";
    private static final String DASH = " - ";
    private static final int DAY_INDEX = 0;
    private static final int MONTH_INDEX = 1;
    private static final int YEAR_INDEX = 2;
    private static final int DATE_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int MONEY_PER_HOUR_INDEX = 3;

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
                LocalDate localDate = LocalDate.of(getYear(date), getMonth(date), getDay(date));
                LocalDate fromDate = LocalDate.of(getYear(dateFrom),
                        getMonth(dateFrom), getDay(dateFrom));
                LocalDate toDate = LocalDate.of(getYear(dateTo), getMonth(dateTo), getDay(dateTo));
                if (!localDate.isBefore(fromDate) && !localDate.isAfter(toDate)) {
                    if (employeeName.equals(name)) {
                        salary += hours * moneyPerHour;
                    }
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name).append(DASH).append(salary);
        }
        return stringBuilder.toString();
    }

    private int getDay(String date) {
        String[] dates = date.split(DOT);
        return Integer.parseInt(dates[DAY_INDEX]);
    }

    private int getMonth(String date) {
        String[] dates = date.split(DOT);
        return Integer.parseInt(dates[MONTH_INDEX]);
    }

    private int getYear(String date) {
        String[] dates = date.split(DOT);
        return Integer.parseInt(dates[YEAR_INDEX]);
    }
}
