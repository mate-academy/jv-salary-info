package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String dataSeparator = " ";
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_FROM_DATA_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int HOURS_WORKED_PER_DAY_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private String dateFromData;
    private String employeeName;
    private int hoursWorkedPerDay;
    private int incomePerHour;

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
        String[] infoData = data.split(dataSeparator);
        dateFromData = infoData[DATE_FROM_DATA_INDEX];
        employeeName = infoData[EMPLOYEE_NAME_INDEX];
        hoursWorkedPerDay = Integer.parseInt(infoData[HOURS_WORKED_PER_DAY_INDEX]);
        incomePerHour = Integer.parseInt(infoData[INCOME_PER_HOUR_INDEX]);
    }
}
