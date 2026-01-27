package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {

        int[] totalSalaries = new int[names.length];

        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);

        for (String record : data) {
            String[] parts = record.split("\\s+");

            LocalDate recordDate = LocalDate.parse(parts[DATE_INDEX], FORMATTER);
            String name = parts[NAME_INDEX];
            int hours = Integer.parseInt(parts[HOURS_INDEX]);
            int rate = Integer.parseInt(parts[RATE_INDEX]);

            if (recordDate.isBefore(fromDate) || recordDate.isAfter(toDate)) {
                continue;
            }

            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(name)) {
                    totalSalaries[i] += hours * rate;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        String lineSep = System.lineSeparator();
        sb.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            sb.append(lineSep).append(names[i]).append(" - ").append(totalSalaries[i]);
        }

        return sb.toString().trim();
    }
}

