package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATA_SEPARATOR = " ";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_FROM_DATA_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int HOURS_WORKED_PER_DAY_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        LocalDate dateInRangeFrom = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate dateInRangeTo = LocalDate.parse(dateTo, DATE_FORMAT);

        for (String name : names) {
            int salary = getSalary(data, name, dateInRangeFrom, dateInRangeTo);
            salaryInfo.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return salaryInfo.toString();
    }

    private int getSalary(String[]data, String name, LocalDate dateInRangeFrom, LocalDate dateInRangeTo) {
        int salary = 0;
        for (String dataPiece : data) {
            String[] splitData = dataPiece.split(DATA_SEPARATOR);
            if (isDateValid(splitData[DATE_FROM_DATA_INDEX], dateInRangeFrom, dateInRangeTo)) {
                int hoursWorkedPerDay = Integer.parseInt(splitData[HOURS_WORKED_PER_DAY_INDEX]);
                int incomePerHour = Integer.parseInt(splitData[INCOME_PER_HOUR_INDEX]);
                salary += splitData[EMPLOYEE_NAME_INDEX].equals(name)
                        ? hoursWorkedPerDay * incomePerHour : 0;
            }
        }
        return salary;
    }

    private boolean isDateValid(String dataDate, LocalDate dateFrom, LocalDate dateTo) {
        LocalDate dateFromData = LocalDate.parse(dataDate, DATE_FORMAT);
        return dateFromData != null && dateFromData.compareTo(dateFrom) >= 0
                && dateFromData.compareTo(dateTo) <= 0;
    }
}
