package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final String REPORT_HEADER = "Report for period ";
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate from = parseDate(dateFrom);
        LocalDate to = parseDate(dateTo);
        int[] salaries = calculateSalaries(names, data, from, to);
        return buildReport(names, salaries, dateFrom, dateTo);
    }

    private LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + date, e);
        }
    }

    private int[] calculateSalaries(String[] names, String[] data,
                                    LocalDate from, LocalDate to) {
        int[] salaries = new int[names.length];

        for (String record : data) {
            String[] parts = record.split(" ");
            if (parts.length < 4) {
                throw new IllegalArgumentException("Invalid record format: " + record);
            }

            LocalDate currentDate = parseDate(parts[0]);
            if (!currentDate.isBefore(from) && !currentDate.isAfter(to)) {
                updateSalary(names, salaries, parts);
            }
        }
        return salaries;
    }

    private void updateSalary(String[] names, int[] salaries, String[] parts) {
        String employeeName = parts[1];
        int hours = parseInt(parts[2], "hours");
        int rate = parseInt(parts[3], "rate");

        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(employeeName)) {
                salaries[i] += hours * rate;
                break;
            }
        }
    }

    private int parseInt(String value, String fieldName) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid " + fieldName + " format: " + value, e);
        }
    }

    private String buildReport(String[] names, int[] salaries, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder()
                .append(REPORT_HEADER)
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            report.append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
            if (i < names.length - 1) {
                report.append(System.lineSeparator());
            }
        }
        return report.toString();
    }
}
