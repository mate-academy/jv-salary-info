package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);

        int[] salaries = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            String employee = names[i];

            for (String record : data) {
                String[] parts = record.split(" ");
                if (parts.length == 4) {
                    try {
                        LocalDate workDate = LocalDate.parse(parts[0], FORMATTER);
                        String recordName = parts[1];
                        int hours = Integer.parseInt(parts[2]);
                        int rate = Integer.parseInt(parts[3]);

                        if (recordName.equals(employee)
                                && (workDate.isEqual(from) || workDate.isAfter(from))
                                && (workDate.isEqual(to) || workDate.isBefore(to))) {
                            salaries[i] += hours * rate;
                        }
                    } catch (Exception ignored) {
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();
        String lineSep = System.lineSeparator();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append(lineSep);

        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ").append(salaries[i]).append(lineSep);
        }

        return report.toString().trim();
    }
}
