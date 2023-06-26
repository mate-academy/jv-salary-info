package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public static final String SPACE = " ";
    public static final String DOT = "\\.";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String employeeData : data) {
                String[] separatedData = employeeData.split(SPACE);
                String date = separatedData[0];
                String employeeName = separatedData[1];
                int hours = Integer.parseInt(separatedData[2]);
                int moneyPerHour = Integer.parseInt(separatedData[3]);
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
            stringBuilder.append(System.lineSeparator()).append(name)
                    .append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }

    private int getDay(String date) {
        String[] dates = date.split(DOT);
        return Integer.parseInt(dates[0]);
    }

    private int getMonth(String date) {
        String[] dates = date.split(DOT);
        return Integer.parseInt(dates[1]);
    }

    private int getYear(String date) {
        String[] dates = date.split(DOT);
        return Integer.parseInt(dates[2]);
    }
}
