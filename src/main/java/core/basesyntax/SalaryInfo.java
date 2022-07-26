package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String stringSeparator = " ";
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static String dateFromData;
    private static String employeeName;
    private static int hoursWorkedPerDay;
    private static int incomePerHour;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);

        for (String name : names) {
            int salary = 0;

            for (String getData : data) {
                splitData(getData);
                if (isInDate(dateFromData, dateFrom, dateTo)) {
                    salary += calculateSalary(name);
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }

        return salaryInfo.toString();
    }

    private int calculateSalary(String name) {
        return employeeName.equals(name) ? hoursWorkedPerDay * incomePerHour : 0;
    }

    private boolean isInDate(String dataDate, String dateFrom, String dateTo) {
        LocalDate dateFromData = LocalDate.parse(dataDate, dateFormat);
        LocalDate dateInRangeFrom = LocalDate.parse(dateFrom, dateFormat);
        LocalDate dateInRangeTo = LocalDate.parse(dateTo, dateFormat);

        return dateFromData != null && dateFromData.compareTo(dateInRangeFrom) >= 0
                && dateFromData.compareTo(dateInRangeTo) <= 0;
    }

    private void splitData(String data) {
        String[] infoData = data.split(stringSeparator);
        dateFromData = infoData[0];
        employeeName = infoData[1];
        hoursWorkedPerDay = Integer.parseInt(infoData[2]);
        incomePerHour = Integer.parseInt(infoData[3]);
    }
}
