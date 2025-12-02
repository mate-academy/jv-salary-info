package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate start = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate end = LocalDate.parse(dateTo, DATE_FORMATTER);

        int[] salarys = new int[names.length];

        for (String record : data) {
            String[] parts = record.split(" ");

            LocalDate currentDate = LocalDate.parse(parts[DATE_INDEX], DATE_FORMATTER);
            String name = parts[NAME_INDEX];
            int hours = Integer.parseInt(parts[HOURS_INDEX]);
            int perHour = Integer.parseInt(parts[SALARY_INDEX]);

            if (!currentDate.isBefore(start) && !currentDate.isAfter(end)) {
                for (int i = 0; i < names.length; i++) {
                    if (name.equals(names[i])) {
                        salarys[i] += hours * perHour;
                        break;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ").append(salarys[i])
                    .append(System.lineSeparator());
        }

        return report.toString().trim();
    }
}

