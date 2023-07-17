package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE = 0;
    public static final int EMPLOYEE_NAME = 1;
    public static final int EMPLOYEE_WORKED_HOURS = 2;
    public static final int EMPLOYEE_INCOME = 3;
    public static final String DATE_FORMAT = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate startDate;
        LocalDate endDate;
        startDate = LocalDate.parse(dateFrom, dateFormatter);
        endDate = LocalDate.parse(dateTo, dateFormatter);
        reportBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int totalSalary = 0;
            for (String entryData : data) {
                String[] splitDataArray = entryData.split(" ");
                String employeeWorkedDate = splitDataArray[DATE];
                String employeeName = splitDataArray[EMPLOYEE_NAME];
                int employeeWorkedHours = Integer.parseInt(splitDataArray[EMPLOYEE_WORKED_HOURS]);
                int employeeIncomePerHour = Integer.parseInt(splitDataArray[EMPLOYEE_INCOME]);
                LocalDate entryDateByUserName = LocalDate.parse(employeeWorkedDate, dateFormatter);
                if (entryDateByUserName.isEqual(startDate)
                        || entryDateByUserName.isAfter(startDate)
                        && (entryDateByUserName.isBefore(endDate)
                        || entryDateByUserName.isEqual(endDate))
                        && employeeName.equals(name)) {
                    totalSalary += employeeWorkedHours * employeeIncomePerHour;
                }
            }
            reportBuilder.append(name)
                    .append(" - ")
                    .append(totalSalary)
                    .append(System.lineSeparator());
        }
        return reportBuilder.toString().trim();
    }
}

