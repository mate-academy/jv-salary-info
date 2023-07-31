package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final String REPORT_SEPARATOR = " - ";
    private static final int DATE_INDEX = 0;
    private static final int EMPLOYEE_WORKING_HOURS_INDEX = 2;
    private static final int EMPLOYEE_INCOME_PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.parse(dateFrom, formatter);
            endDate = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException("Wrong date formats.");
        }
        String reportHeader =
                String.format("Report for period %1$s - %2$s", startDate.format(formatter),
                        endDate.format(formatter));
        StringBuilder report = new StringBuilder(reportHeader);
        for (String name : names) {
            int employeeSalary = 0;
            for (String dataRecord : data) {
                LocalDate parsedDate = null;
                String[] dataRecordSplit = dataRecord.split(" ");
                String employeeWorkingHours = dataRecordSplit[EMPLOYEE_WORKING_HOURS_INDEX];
                String employeeIncomePerHour = dataRecordSplit[EMPLOYEE_INCOME_PER_HOUR_INDEX];
                if (dataRecord.contains(name)) {
                    try {
                        parsedDate = LocalDate.parse(dataRecordSplit[DATE_INDEX], formatter);
                    } catch (DateTimeParseException e) {
                        throw new RuntimeException("Wrong date formats in data.", e);
                    }
                }
                if (parsedDate != null && (parsedDate.isAfter(startDate) || parsedDate.isEqual(
                        startDate)) && (parsedDate.isBefore(endDate) || parsedDate.isEqual(
                        endDate))) {
                    try {
                        employeeSalary += Integer.parseInt(employeeWorkingHours) * Integer.parseInt(
                                employeeIncomePerHour);
                    } catch (DateTimeParseException e) {
                        throw new RuntimeException("Wrong date formats in data.", e);
                    }
                }
            }
            report.append(System.lineSeparator());
            report.append(name).append(REPORT_SEPARATOR).append(employeeSalary);
        }
        return String.valueOf(report);
    }
}
