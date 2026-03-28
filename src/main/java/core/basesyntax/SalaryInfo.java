package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int MONEY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate start = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate end = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int totalSalaryForEmployee = 0;

            for (String record : data) {
                String[] parts = record.split(" ");
                LocalDate recordDate = LocalDate.parse(parts[DATE_INDEX], FORMATTER);
                String employeeName = parts[NAME_INDEX];

                if (employeeName.equals(name)
                        && !recordDate.isBefore(start)
                        && !recordDate.isAfter(end)) {
                    int hours = Integer.parseInt(parts[HOUR_INDEX]);
                    int moneyPerHour = Integer.parseInt(parts[MONEY_PER_HOUR_INDEX]);
                    totalSalaryForEmployee += hours * moneyPerHour;
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalaryForEmployee);
        }

        return stringBuilder.toString();
    }
}
