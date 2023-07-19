package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE = 0;
    public static final int EMPLOYEE_NAME = 1;
    public static final int EMPLOYEE_WORKED_HOURS = 2;
    public static final int EMPLOYEE_INCOME = 3;
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String HYPHEN_CHARACTER = " - ";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        LocalDate startDate;
        LocalDate endDate;
        startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        endDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        reportBuilder.append("Report for period ")
                .append(dateFrom)
                .append(HYPHEN_CHARACTER)
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
                LocalDate entryDateByUserName = LocalDate.parse(employeeWorkedDate, DATE_FORMATTER);
                if (entryDateByUserName.isEqual(startDate)
                        || entryDateByUserName.isAfter(startDate)
                        && (entryDateByUserName.isBefore(endDate)
                        || entryDateByUserName.isEqual(endDate))
                        && employeeName.equals(name)) {
                    totalSalary += employeeWorkedHours * employeeIncomePerHour;
                }
            }
            reportBuilder.append(name)
                    .append(HYPHEN_CHARACTER)
                    .append(totalSalary)
                    .append(System.lineSeparator());
        }
        return reportBuilder.toString().trim();
    }
}
