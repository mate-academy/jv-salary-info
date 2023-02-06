package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate beginDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder salaryReport = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo + System.lineSeparator());

        for (String name : names) {
            int currentEmployeeSalary = 0;
            for (String currentData : data) {
                String[] dataArray = currentData.split(" ");
                LocalDate currentDate = LocalDate.parse(dataArray[0], FORMATTER);
                String nameData = dataArray[1];
                int hours = Integer.parseInt(dataArray[2]);
                int salary = Integer.parseInt(dataArray[3]);

                if (nameData.equals(name)) {
                    if (!currentDate.isAfter(endDate) && !currentDate.isBefore(beginDate)) {
                        int currentPeriodSalary = hours * salary;
                        currentEmployeeSalary += currentPeriodSalary;
                    }
                }
            }
            salaryReport.append(name).append(
                " - ").append(currentEmployeeSalary).append(System.lineSeparator());
        }

        return salaryReport.toString().trim();
    }
}
