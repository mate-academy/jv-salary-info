package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
private static final int RECORDED_DATE = 0;
private static final int RECORDED_NAME = 1;
private static final int RECORDED_HOUR = 2;
private static final int RECORDED_RATE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        int[] salaries = new int[names.length];

        for (String records : data) {
            String[] parts = records.split(" ");
            LocalDate recordDate = LocalDate.parse(parts[RECORDED_DATE], FORMATTER);
            String recordName = parts[RECORDED_NAME];
            int recordHour = Integer.parseInt(parts[RECORDED_HOUR]);
            int recordRate = Integer.parseInt(parts[RECORDED_RATE]);

            if ((recordDate.isEqual(startDate) || recordDate.isAfter(startDate))
                    && (recordDate.isEqual(endDate) || recordDate.isBefore(endDate))) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(recordName)) {
                        salaries[i] += recordHour * recordRate;
                    }
                }

            }
        }
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            result.append(names[i])
                    .append(" - ")
                    .append(salaries[i])
                    .append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
