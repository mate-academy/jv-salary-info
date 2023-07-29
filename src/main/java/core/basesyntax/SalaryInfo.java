package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

    private static int getEmployeeIndex(StringBuilder employeeNamesIndexes, String employeeName) {
        int startPosition =
                employeeNamesIndexes.indexOf(":",
                        employeeNamesIndexes.indexOf(employeeName)) + 1;
        int endPosition =
                employeeNamesIndexes.indexOf("|", employeeNamesIndexes.indexOf(employeeName));
        return Integer.parseInt(employeeNamesIndexes.substring(startPosition, endPosition));
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate;
        LocalDate endDate;
        StringBuilder employeeNamesIndexes = new StringBuilder();
        int[] usersSalaries = new int[names.length];
        int employeeNameIndex = 0;
        try {
            startDate = LocalDate.parse(dateFrom, formatter);
            endDate = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException("Wrong date formats.");
        }
        final int dataEmployeeNameIndex = 1;
        final int dataEmployeeWorkingHoursIndex = 2;
        final int dataEmployeeIncomePerHourIndex = 3;
        LocalDate parsedDate;
        for (String dataRecord : data) {
            String[] dataRecordSplit = dataRecord.split(" ");
            String employeeName = dataRecordSplit[dataEmployeeNameIndex];
            String employeeWorkingHours = dataRecordSplit[dataEmployeeWorkingHoursIndex];
            String employeeIncomePerHour = dataRecordSplit[dataEmployeeIncomePerHourIndex];
            try {
                parsedDate = LocalDate.parse(dataRecordSplit[0], formatter);
            } catch (DateTimeParseException exception) {
                throw new IllegalArgumentException("Wrong date formats in data.");
            }
            if (!String.valueOf(employeeNamesIndexes).contains(employeeName)) {
                employeeNamesIndexes.append(employeeName).append(":").append(employeeNameIndex)
                        .append("|");
                employeeNameIndex++;
            }
            if (parsedDate.toEpochDay() < startDate.toEpochDay()
                    || parsedDate.toEpochDay() > endDate.toEpochDay()) {
                continue;
            }
            int dateSalary = 0;
            try {
                dateSalary = Integer.parseInt(employeeWorkingHours) * Integer.parseInt(
                        employeeIncomePerHour);
            } catch (NumberFormatException exception) {
                throw new IllegalArgumentException("Wrong numeric values format in data.");
            }
            int index = getEmployeeIndex(employeeNamesIndexes, employeeName);
            usersSalaries[index] += dateSalary;
        }
        String reportHeader =
                String.format("Report for period %1$s - %2$s", startDate.format(formatter),
                        endDate.format(formatter));
        StringBuilder report = new StringBuilder(reportHeader);
        for (String name : names) {
            report.append(System.lineSeparator());
            report.append(name).append(" - ");
            int index = getEmployeeIndex(employeeNamesIndexes, name);
            report.append(usersSalaries[index]);
        }
        return String.valueOf(report);
    }
}
