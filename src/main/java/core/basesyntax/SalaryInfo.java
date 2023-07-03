package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final String DASH = " - ";
    private static final String SPACE = " ";
    private static final String DOT = "\\.";
    private static final int DAY_INDEX = 0;
    private static final int MONTH_INDEX = 1;
    private static final int YEAR_INDEX = 2;
    private static final int DATE_EMPLOYEE_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int MONEY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder infoAboutEmployer = new StringBuilder();
        infoAboutEmployer.append("Report for period ").append(dateFrom).append(DASH).append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String employeeData : data) {
                String[] info = employeeData.split(SPACE);
                String date = info[DATE_EMPLOYEE_INDEX];
                String employeeName = info[EMPLOYEE_NAME_INDEX];
                int hours = Integer.parseInt(info[HOURS_INDEX]);
                int moneyPerHour = Integer.parseInt(info[MONEY_PER_HOUR_INDEX]);
                LocalDate employeeDate = LocalDate.of(getYear(date), getMonth(date), getDay(date));
                LocalDate fromDate = LocalDate.of(getYear(dateFrom),
                        getMonth(dateFrom), getDay(dateFrom));
                LocalDate toDate = LocalDate.of(getYear(dateTo), getMonth(dateTo), getDay(dateTo));
                if (!employeeDate.isBefore(fromDate) && !employeeDate.isAfter(toDate)) {
                    if (employeeName.equals(name)) {
                        salary += hours * moneyPerHour;
                    }
                }
            }
            infoAboutEmployer.append(System.lineSeparator()).append(name)
                    .append(DASH).append(salary);
        }
        return infoAboutEmployer.toString();
    }

    private int getYear(String data) {
        String[] dates = data.split(DOT);
        return Integer.parseInt(dates[YEAR_INDEX]);
    }

    private int getMonth(String data) {
        String[] dates = data.split(DOT);
        return Integer.parseInt(dates[MONTH_INDEX]);
    }

    private int getDay(String data) {
        String[] dates = data.split(DOT);
        return Integer.parseInt(dates[DAY_INDEX]);
    }
}
