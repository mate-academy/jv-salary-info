package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        int[] salaries = new int[names.length];
        StringBuilder result = new StringBuilder();

        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            for (String partOfData : data) {
                String[] parts = partOfData.split(" ");
                LocalDate date = LocalDate.parse(parts[DATE_INDEX], formatter);
                String nameOfEmployee = parts[NAME_INDEX];
                int hours = Integer.parseInt(parts[HOURS_INDEX]);
                int rate = Integer.parseInt(parts[RATE_INDEX]);

                if (names[i].equals(nameOfEmployee)
                        && !date.isBefore(fromDate)
                        && !date.isAfter(toDate)) {
                    salaries[i] += hours * rate;
                }
            }
            result.append(names[i]).append(" - ").append(salaries[i]);
            if (i < names.length - 1) {
                result.append(System.lineSeparator());
            }
        }

        return result.toString();
    }
}
