package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATA = 0;
    private static final int INDEX_OF_SALARY_PER_HOUR = 2;
    private static final int INDEX_OF_AMOUNT_HOURS = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);

        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);

        for (String name : names) {
            int amountSalary = 0;
            for (String info : data) {
                if (info.contains(name)) {
                    String[] array = info.split(" ");
                    LocalDate date = LocalDate.parse(array[INDEX_OF_DATA], FORMATTER);
                    if (date.compareTo(from) >= 0 && date.compareTo(to) <= 0) {
                        amountSalary += Integer.parseInt(array[INDEX_OF_SALARY_PER_HOUR]) * Integer.parseInt(array[INDEX_OF_AMOUNT_HOURS]);
                    }
                }
            }
            result.append("\n").append(name).append(" - ").append(amountSalary);
        }
        return result.toString();
    }
}
