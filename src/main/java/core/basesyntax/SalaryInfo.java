package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORM = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final int TOKENS_COUNT = 4;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORM);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORM);

        int[] salaries = new int[names.length];

        for (String record : data) {

            String[] parts = record.split("\\s+");

            if (parts.length != TOKENS_COUNT) {
                continue;
            }

            LocalDate date = LocalDate.parse(parts[DATE_INDEX], DATE_FORM);
            if (!date.isBefore(from) && !date.isAfter(to)) {
                String name = parts[NAME_INDEX];
                int hours = Integer.parseInt(parts[HOURS_INDEX]);
                int rate = Integer.parseInt(parts[RATE_INDEX]);
                int salary = hours * rate;

                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += salary;
                        break;
                    }
                }
            }
        }

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            reportBuilder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }

        return reportBuilder.toString();
    }
}
