package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate beginningDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endingDate = LocalDate.parse(dateTo, FORMATTER);
        int[] salaries = new int[names.length];
        StringBuilder salaryInfo = new StringBuilder().append("Report for period ")
                .append(beginningDate.format(FORMATTER))
                .append(" - ")
                .append(endingDate.format(FORMATTER))
                .append(System.lineSeparator());

        for (int i = 0; i < data.length; i++) {
            String[] parts = data[i].split(" ");
            LocalDate dataDate = LocalDate.parse(parts[0], FORMATTER);
            if (dataDate.isEqual(beginningDate)
                    || (dataDate.isAfter(beginningDate) && dataDate.isBefore(endingDate))
                    || dataDate.isEqual(endingDate)) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(parts[1])) {
                        salaries[j] += Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
                    }
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            salaryInfo.append(names[i])
                    .append(" - ")
                    .append(salaries[i])
                    .append(System.lineSeparator());
        }
        return salaryInfo.toString().trim();
    }
}
