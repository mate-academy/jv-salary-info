package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_MONEY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int salary = 0;

        LocalDate date1 = LocalDate.parse(dateFrom, formatter);
        LocalDate date2 = LocalDate.parse(dateTo, formatter);

        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] parts = data[j].split(" ");
                LocalDate dateToCheck = LocalDate.parse(parts[INDEX_OF_DATE], formatter);
                if (parts[INDEX_OF_NAME].equals(names[i]) && !dateToCheck.isBefore(date1)
                        && !dateToCheck.isAfter(date2)) {
                    salary += Integer.parseInt(parts[INDEX_OF_HOURS])
                            * Integer.parseInt(parts[INDEX_OF_MONEY]);
                }
            }
            result.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary);
        }

        return result.toString();
    }
}
