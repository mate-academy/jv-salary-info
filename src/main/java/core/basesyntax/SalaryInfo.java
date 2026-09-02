package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_HOURS = 2;
    private static final int INDEX_RATE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate beginningDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endingDate = LocalDate.parse(dateTo, FORMATTER);
        int[] salaries = new int[names.length];
        StringBuilder salaryInfo = new StringBuilder().append("Report for period ")
                .append(beginningDate.format(FORMATTER))
                .append(" - ")
                .append(endingDate.format(FORMATTER))
                .append(System.lineSeparator());

        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate dataDate = LocalDate.parse(parts[0], FORMATTER);
            if (dataDate.isEqual(beginningDate)
                    || (dataDate.isAfter(beginningDate) && dataDate.isBefore(endingDate))
                    || dataDate.isEqual(endingDate)) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(parts[1])) {
                        salaries[j] += Integer.parseInt(parts[INDEX_HOURS])
                                * Integer.parseInt(parts[INDEX_RATE]);
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
