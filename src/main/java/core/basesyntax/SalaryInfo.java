package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int totalSalary;
        LocalDate workingDateFrom = LocalDate.from(FORMATTER.parse(dateFrom));
        LocalDate workingDateTo = LocalDate.from(FORMATTER.parse(dateTo));
        StringBuilder employeeSalary = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo);
        for (String name : names) {
            totalSalary = 0;
            for (String dataField : data) {
                String[] dataFields = dataField.split(" ");
                LocalDate workingDay = LocalDate.from(FORMATTER.parse(dataFields[DATE_INDEX]));
                String employeeName = dataFields[NAME_INDEX];
                int workingHours = Integer.parseInt(dataFields[WORKING_HOURS_INDEX]);
                int salaryPerHour = Integer.parseInt(dataFields[SALARY_PER_HOUR_INDEX]);
                boolean validWorkingDay = workingDay.isAfter(workingDateFrom)
                        && workingDay.isBefore(workingDateTo) || workingDay.isEqual(workingDateFrom)
                        || workingDay.isEqual(workingDateTo);
                if (name.equals(employeeName) && validWorkingDay) {
                    totalSalary += workingHours * salaryPerHour;
                }
            }
            employeeSalary.append(System.lineSeparator()).append(name).append(" - ")
                    .append(totalSalary);
        }
        return employeeSalary.toString();
    }
}
