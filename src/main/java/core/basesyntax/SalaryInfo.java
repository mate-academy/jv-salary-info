package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom,FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo,FORMATTER);
        StringBuilder stringBuilder = new StringBuilder(
                String.format("Report for period %s - %s",dateFrom,dateTo)
        );
        for (String name : names) {
            int employeeSalaryForPeriod = 0;
            for (String employeesData : data) {
                String[] employeeData = employeesData.split("\\s");
                String currentEmployeeName = employeeData[1];
                if (name.equals(currentEmployeeName)) {
                    LocalDate currentDate = LocalDate.parse(employeeData[0],FORMATTER);
                    if ((currentDate.isAfter(localDateFrom)
                            || currentDate.equals(localDateFrom))
                            && (currentDate.isBefore(localDateTo)
                            || currentDate.equals(localDateTo))) {
                        int employeeWorkedHours = Integer.parseInt(employeeData[2]);
                        int employeeSalaryPerHour = Integer.parseInt(employeeData[3]);
                        employeeSalaryForPeriod += employeeWorkedHours * employeeSalaryPerHour;
                    }
                }
            }
            stringBuilder.append(
                    String.format("%s%s - %d",System.lineSeparator(),name,employeeSalaryForPeriod)
            );
        }
        return stringBuilder.toString();
    }
}
