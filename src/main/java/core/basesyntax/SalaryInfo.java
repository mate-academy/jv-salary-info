package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    protected int totalSalary;
    protected int workingHours;
    protected int salaryPerHour;
    protected LocalDate workingDateFrom;
    protected LocalDate workingDateTo;
    protected LocalDate workingDay;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final String separator = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        workingDateFrom = LocalDate.from(formatter.parse(dateFrom));
        workingDateTo = LocalDate.from(formatter.parse(dateTo));
        StringBuilder employeeSalary = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo);
        for (String name : names) {
            totalSalary = 0;
            for (String dataField : data) {
                String[] dataFields = dataField.split(" ");
                workingDay = LocalDate.from(formatter.parse(dataFields[0]));
                String employeeName = dataFields[1];
                workingHours = Integer.parseInt(dataFields[2]);
                salaryPerHour = Integer.parseInt(dataFields[3]);
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
