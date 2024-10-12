package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        LocalDate localDateFrom = parseDateFromString(dateFrom);
        LocalDate localDateTo = parseDateFromString(dateTo);

        report = addReportHeader(report, dateFrom, dateTo);

        for (String currentEmployee : names) {
            int salaryAmount = calculateEmployeeSalary(data, currentEmployee, localDateFrom, localDateTo);

            report = addReportLine(report, currentEmployee, salaryAmount);
        }

        return report.toString();
    }

    private StringBuilder addReportHeader(StringBuilder report, String dateFrom, String dateTo) {
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        return report;
    }

    private StringBuilder addReportLine(StringBuilder report, String employee, int salaryAmount) {
        report.append(System.lineSeparator())
                .append(employee)
                .append(" - ")
                .append(salaryAmount);

        return report;
    }

    private int calculateEmployeeSalary(String[] data, String employee, LocalDate dateFrom, LocalDate dateTo) {
        int salaryAmount = 0;

        for (String record : data) {
            if (record == null || record.isEmpty()) {
                continue;
            }

            String[] parseRecord = record.split(" ");
            LocalDate day = parseDateFromString(parseRecord[0]);
            String name = parseRecord[1];
            int hours = parseNumberFromString(parseRecord[2]);
            int daySalary = parseNumberFromString(parseRecord[3]);

            if (isDateInPeriod(day, dateFrom, dateTo) && employee.equals(name)) {
                salaryAmount += hours * daySalary;
            }
        }

        return salaryAmount;
    }

    private boolean isDateInPeriod(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return (date.equals(dateFrom) || date.isAfter(dateFrom))
                && (date.equals(dateTo) || date.isBefore(dateTo));
    }

    private LocalDate parseDateFromString(String parseValue) {
        try {
            return LocalDate.parse(parseValue, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("We cann't parse date from the string:" + parseValue);
        }
    }

    private int parseNumberFromString(String parseValue) {
        try {
            return Integer.parseInt(parseValue);
        } catch (NumberFormatException e) {
            throw new RuntimeException("We cann't parse number from the string:" + parseValue);
        }
    }
}
