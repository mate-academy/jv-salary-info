package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)  {
        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final LocalDate FROM_LOCAL_DATE = LocalDate.parse(dateFrom,FORMATTER);
        final LocalDate TO_LOCAL_DATE = LocalDate.parse(dateTo,FORMATTER);
        StringBuilder stringBuilder = new StringBuilder(String.format("Report for period %s - %s",dateFrom,dateTo));
        for (String name : names) {
            int employeeSalaryForPeriod = 0;
            for (String employeesData : data) {
                String[] employeeData = employeesData.split("\\s");
                String currentEmployeeName = employeeData[1];
                if (name.equals(currentEmployeeName)) {
                    LocalDate currentDate = LocalDate.parse(employeeData[0],FORMATTER);
                    if ((currentDate.isAfter(FROM_LOCAL_DATE) || currentDate.equals(FROM_LOCAL_DATE)) && (currentDate.isBefore(TO_LOCAL_DATE) || currentDate.equals(TO_LOCAL_DATE))) {
                        int employeeWorkedHours = Integer.parseInt(employeeData[2]);
                        int employeeSalaryPerHour = Integer.parseInt(employeeData[3]);
                        employeeSalaryForPeriod += employeeWorkedHours * employeeSalaryPerHour;
                    }
                }
            }
            stringBuilder.append(String.format("%s%s - %d",System.lineSeparator(),name,employeeSalaryForPeriod));
        }
        return stringBuilder.toString();
    }
}
