package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salaryInfo = 0;
            for (String employeeData : data) {
                String[] splitData = employeeData.split(" ");
                String date = splitData[0];
                String employeeName = splitData[1];
                int hours = Integer.parseInt(splitData[2]);
                int moneyForHour = Integer.parseInt(splitData[3]);
                LocalDate localDate = LocalDate.of(getYear(date), getMonth(date), getDay(date));
                LocalDate fromDate = LocalDate.of(getYear(dateFrom),
                        getMonth(dateFrom), getDay(dateFrom));
                LocalDate toDate = LocalDate.of(getYear(dateTo), getMonth(dateTo), getDay(dateTo));
                if (!localDate.isBefore(fromDate) && !localDate.isAfter(toDate)) {
                    if (employeeName.equals(name)) {
                        salaryInfo += hours * moneyForHour;
                    }
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salaryInfo);
        }
        return String.valueOf(result);
    }

    private int getDay(String date) {
        String[] dates = date.split("\\.");
        return Integer.parseInt(dates[0]);
    }

    private int getMonth(String date) {
        String[] dates = date.split("\\.");
        return Integer.parseInt(dates[1]);
    }

    private int getYear(String date) {
        String[] dates = date.split("\\.");
        return Integer.parseInt(dates[2]);
    }
}
