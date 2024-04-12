package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int INCOME_INDEX = 2;
    private static final int HOURS_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateFinal = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int totalSalary = 0;
            for (String entry : data) {
                String[] parts = entry.split(" ");
                LocalDate date = LocalDate.parse(parts[DATE_INDEX], FORMATTER);
                if (!date.isBefore(dateStart) && !date.isAfter(dateFinal)
                        && name.equals(parts[NAME_INDEX])) {
                    int hours = Integer.parseInt(parts[INCOME_INDEX]);
                    int incomePerHour = Integer.parseInt(parts[HOURS_INDEX]);
                    totalSalary += hours * incomePerHour;
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
        return result.toString();
    }
}
