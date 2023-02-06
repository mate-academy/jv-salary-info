package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_HOURS_INDEX = 2;
    private static final int HOURLY_SALARY_INDEX = 3;


    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate beginDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder salaryReport = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo + System.lineSeparator());

        for (String name : names) {
            int currentEmployeeSalary = 0;
            for (String currentData : data) {
                String[] dataArray = currentData.split(" ");
                LocalDate currentDate = LocalDate.parse(dataArray[DATE_INDEX], FORMATTER);
                String nameData = dataArray[NAME_INDEX];
                int hours = Integer.parseInt(dataArray[WORK_HOURS_INDEX]);
                int salary = Integer.parseInt(dataArray[HOURLY_SALARY_INDEX]);

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
