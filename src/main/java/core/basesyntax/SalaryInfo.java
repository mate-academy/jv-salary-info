package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = parseDateFromString(dateFrom);
        LocalDate localDateTo = parseDateFromString(dateTo);

        return generateReport(names, data, localDateFrom, localDateTo);
    }

    private String generateReport(String[] names, String[] data,
                                  LocalDate dateFrom, LocalDate dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(DATE_FORMATTER.format(dateFrom))
                .append(" - ")
                .append(DATE_FORMATTER.format(dateTo));

        for (String currentName : names) {
            int salaryAmount = calculateSalaryForEmployee(currentName, data, dateFrom, dateTo);
            report.append(System.lineSeparator())
                    .append(currentName)
                    .append(" - ")
                    .append(salaryAmount);
        }

        return report.toString();
    }

    private int calculateSalaryForEmployee(String employeeName, String[] data,
                                           LocalDate dateFrom, LocalDate dateTo) {
        int totalSalary = 0;

        for (String record : data) {
            if (record == null || record.isEmpty()) {
                continue;
            }

            String[] parsedRecord = record.split(" ");
            LocalDate workDate = parseDateFromString(parsedRecord[0]);
            String name = parsedRecord[1];
            int hoursWorked = parseNumberFromString(parsedRecord[2]);
            int hourlyRate = parseNumberFromString(parsedRecord[3]);

            if (isWithinDateRange(workDate, dateFrom, dateTo) && employeeName.equals(name)) {
                totalSalary += hoursWorked * hourlyRate;
            }
        }

        return totalSalary;
    }

    private boolean isWithinDateRange(LocalDate workDate, LocalDate dateFrom, LocalDate dateTo) {
        return (workDate.isEqual(dateFrom) || workDate.isAfter(dateFrom))
                && (workDate.isEqual(dateTo) || workDate.isBefore(dateTo));
    }

    private LocalDate parseDateFromString(String parseValue) {
        try {
            return LocalDate.parse(parseValue, DATE_FORMATTER);
        } catch (Exception e) {
            throw new RuntimeException("Cannot parse date from the string: " + parseValue, e);
        }
    }

    private int parseNumberFromString(String parseValue) {
        try {
            return Integer.parseInt(parseValue);
        } catch (Exception e) {
            throw new RuntimeException("Cannot parse number from the string: " + parseValue, e);
        }
    }
}
