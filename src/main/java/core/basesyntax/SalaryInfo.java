package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int [] salaries = new int[names.length];

        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        for (int i = 0; i < data.length; i++) {
            String[] parts = data[i].split(" ");
            String dateMonth = parts[DATE_INDEX];
            String person = parts[NAME_INDEX];
            int hours = Integer.parseInt(parts[HOURS_INDEX]);
            int rate = Integer.parseInt(parts[RATE_INDEX]);

            LocalDate localDateMonth = LocalDate.parse(dateMonth, formatter);

            if (!localDateMonth.isBefore(from) && !localDateMonth.isAfter(to)) {
                int earned = hours * rate;
                if (person.equals("John")) {
                    salaries[0] += earned;
                } else if (person.equals("Andrew")) {
                    salaries[1] += earned;
                } else if (person.equals("Kate")) {
                    salaries[2] += earned;
                }
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
