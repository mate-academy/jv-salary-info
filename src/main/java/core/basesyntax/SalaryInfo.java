package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String HEADING = "Report for period %s - %s";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder info = new StringBuilder(String.format(HEADING, dateFrom, dateTo));
        LocalDate firstLimit = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate secondLimit = LocalDate.parse(dateTo, FORMATTER);
        for (int i = 0; i < names.length; i++) {
            int generalEmployeeSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] employeeData = data[j].split(" ");
                LocalDate date = LocalDate.parse(employeeData[0], FORMATTER);
                String employeeName = employeeData[1];
                int countMonths = Integer.parseInt(employeeData[2]);
                int salary = Integer.parseInt(employeeData[3]);
                if (names[i].equals(employeeName)
                        && date.isAfter(firstLimit.minusDays(1))
                        && date.isBefore(secondLimit.plusDays(1))) {
                    generalEmployeeSalary += countMonths * salary;
                }
            }
            info.append(System.lineSeparator()).append(names[i]).append(" - ");
            info.append(generalEmployeeSalary);
        }
        return info.toString();
    }
}
