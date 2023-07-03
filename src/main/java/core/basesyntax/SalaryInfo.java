package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder infoAboutEmployer = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String employeeData : data) {
                String[] info = employeeData.split(" ");
                String date = info[0];
                String employeeName = info[1];
                int hours = Integer.parseInt(info[2]);
                int moneyPerHour = Integer.parseInt(info[3]);
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
                    .append(" - ").append(salary);
        }
        return infoAboutEmployer.toString();
    }

    private int getYear(String data) {
        String[] dates = data.split("\\.");
        return Integer.parseInt(dates[2]);
    }

    private int getMonth(String data) {
        String[] dates = data.split("\\.");
        return Integer.parseInt(dates[1]);
    }

    private int getDay(String data) {
        String[] dates = data.split("\\.");
        return Integer.parseInt(dates[0]);
    }
}
