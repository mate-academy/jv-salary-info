package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final String separator = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int totalSalary;
        LocalDate workingDateFrom = LocalDate.from(FORMATTER.parse(dateFrom));
        LocalDate workingDateTo = LocalDate.from(FORMATTER.parse(dateTo));
        StringBuilder employeeSalary = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo);
        for (String name : names) {
            totalSalary = 0;
            for (String dataField : data) {
                final int dateIndex = 0;
                final int nameIndex = 1;
                final int workingHoursIndex = 2;
                final int salaryPerHourIndex = 3;
                String[] dataFields = dataField.split(" ");
                LocalDate workingDay = LocalDate.from(FORMATTER.parse(dataFields[dateIndex]));
                String employeeName = dataFields[nameIndex];
                int workingHours = Integer.parseInt(dataFields[workingHoursIndex]);
                int salaryPerHour = Integer.parseInt(dataFields[salaryPerHourIndex]);
                boolean validWorkingDay = workingDay.compareTo(workingDateFrom) >= 0
                        && workingDay.compareTo(workingDateTo) <= 0;
                if (name.equals(employeeName) && validWorkingDay) {
                    totalSalary += workingHours * salaryPerHour;
                }
            }
            employeeSalary.append(separator).append(name).append(" - ").append(totalSalary);
        }
        return employeeSalary.toString();
    }
}
