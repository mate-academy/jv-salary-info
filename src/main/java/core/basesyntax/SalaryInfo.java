package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder("Report for period " + dateFrom + " - "
                + dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter).minusDays(1);
        LocalDate toDate = LocalDate.parse(dateTo, formatter).plusDays(1);

        for (String employeeName : names) {
            int totalSalary = 0;
            for (String employeeData : data) {
                String[] splittedData = employeeData.split(" ");
                String dateOfWork = splittedData[0];
                String extractedName = splittedData[1];
                int workedHours = Integer.parseInt(splittedData[2]);
                int incomePerHour = Integer.parseInt(splittedData[3]);
                if (employeeName.equals(extractedName)) {
                    LocalDate workDate = LocalDate.parse(dateOfWork, formatter);
                    if (workDate.isAfter(fromDate) && workDate.isBefore(toDate)) {
                        totalSalary += workedHours * incomePerHour;
                    }
                }
            }
            salaryInfo.append(System.lineSeparator()).append(employeeName).append(" - ")
                    .append(totalSalary);
        }
        return salaryInfo.toString();
    }
}
