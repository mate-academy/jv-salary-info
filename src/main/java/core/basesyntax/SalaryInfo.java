package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + System.lineSeparator());
        int totalNames = names.length;
        for (int i = 0; i < totalNames; i++) {
            String name = names[i];
            int totalSalary = 0;
            LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
            LocalDate dateFinal = LocalDate.parse(dateTo, FORMATTER).plusDays(1);
            for (String entry : data) {
                String[] parts = entry.split(" ");
                LocalDate date = LocalDate.parse(parts[0], FORMATTER);
                if (!date.isBefore(dateStart) && date.isBefore(dateFinal)
                        && name.equals(parts[1])) {
                    int hours = Integer.parseInt(parts[2]);
                    int incomePerHour = Integer.parseInt(parts[3]);
                    totalSalary += hours * incomePerHour;
                }
            }
            result.append(name).append(" - ").append(totalSalary);
            if (i < totalNames - 1) {
                result.append(System.lineSeparator());
            }
        }
        return result.toString();
    }
}
