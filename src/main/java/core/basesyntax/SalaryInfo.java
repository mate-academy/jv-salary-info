package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int HOURLY_WAGE = 3;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SEPARATOR = " ";
    
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate lastDate = LocalDate.parse(dateTo, DATE_FORMAT);

        StringBuilder result = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        for (String name : names) {
            int totalSalary = 0;

            for (int i = 0; i < data.length; i++) {
                String[] employeeInformation = data[i].split(SEPARATOR);
                LocalDate workDayDate = LocalDate.parse(employeeInformation[DATE], DATE_FORMAT);
                
                if (employeeInformation[NAME].equals(name)
                        && (workDayDate.isAfter(startDate) || workDayDate.isEqual(startDate))
                        && (workDayDate.isBefore(lastDate) || workDayDate.isEqual(lastDate))) {
                    totalSalary += Integer.valueOf(employeeInformation[HOURS])
                            * Integer.valueOf(employeeInformation[HOURLY_WAGE]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
        return result.toString();
    }
}
