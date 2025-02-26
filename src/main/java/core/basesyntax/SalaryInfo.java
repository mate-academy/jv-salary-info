package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate from;
        LocalDate to;

        try {
            from = LocalDate.parse(dateFrom, DATE_FORMATTER);
            to = LocalDate.parse(dateTo, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: "
                    + dateFrom + " or " + dateTo, e);
        }

        int[] salaries = new int[names.length];

        for (String record : data) {
            String[] parts = record.split(" ");
            if (parts.length < 4) {
                continue;
            }

            try {
                LocalDate currentDate = LocalDate.parse(parts[0], DATE_FORMATTER);
                if (!currentDate.isBefore(from) && !currentDate.isAfter(to)) {
                    String employeeName = parts[1];
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].equals(employeeName)) {
                            int hours = Integer.parseInt(parts[2]);
                            int rate = Integer.parseInt(parts[3]);
                            salaries[i] += hours * rate;
                            break;
                        }
                    }
                }
            } catch (DateTimeParseException | NumberFormatException e) {
                continue;
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
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
