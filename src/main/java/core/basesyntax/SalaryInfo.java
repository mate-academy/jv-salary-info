package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_FIRST = 1;
    private static final int INDEX_SECOND = 2;
    private static final int INDEX_THIRD = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            int salary = getSalaryByName(data, name, localDateFrom, localDateTo);
            result.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return result.toString().trim();
    }

    private int getSalaryByName(String[] data, String name,
                                LocalDate localDateFrom, LocalDate localDateTo) {
        int sum = 0;
        for (String string : data) {
            String[] salaryInfo = string.split(" ");
            LocalDate verifiable = LocalDate.parse(salaryInfo[INDEX_ZERO], formatter);
            if (salaryInfo[INDEX_FIRST].equals(name)
                    && isInRange(verifiable, localDateFrom, localDateTo)) {
                sum += Integer.parseInt(salaryInfo[INDEX_SECOND])
                        * Integer.parseInt(salaryInfo[INDEX_THIRD]);
            }
        }
        return sum;
    }

    private boolean isInRange(LocalDate verifiable,
                              LocalDate localDateFrom, LocalDate localDateTo) {
        return (verifiable.isAfter(localDateFrom)
                || verifiable.equals(localDateFrom))
                && (verifiable.isBefore(localDateTo)
                || verifiable.equals(localDateTo));
    }
}
