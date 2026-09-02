package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate beginningDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endingDate = LocalDate.parse(dateTo, FORMATTER);
        int[] salaries = new int[names.length];

        StringBuilder dataFilter = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            String[] parts = data[i].split(" ");
            LocalDate dataDate = LocalDate.parse(parts[0]);
            if (dataDate.isEqual(beginningDate)
                    || (dataDate.isAfter(beginningDate) && dataDate.isBefore(endingDate))
                    || dataDate.isEqual(endingDate)) {
                dataFilter.append(parts[1])
                        .append(" ")
                        .append(Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]))
                        .append(System.lineSeparator());
            }
        }

        StringBuilder salaryInfo = new StringBuilder();
        String[] filtered = new String[]{dataFilter.toString()}; // NAME SALARY
        for (int i = 0; i < names.length; i++) {
            if ()
        }
    }
}
