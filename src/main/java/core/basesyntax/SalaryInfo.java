package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_PERIOD = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOUR = 2;
    private static final int INDEX_HOUR_RATE = 3;
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
        for (String dataLine : data) {
            String[] salaryInfo = dataLine.split(" ");
            LocalDate verifiable = LocalDate.parse(salaryInfo[INDEX_PERIOD], formatter);
            if (salaryInfo[INDEX_NAME].equals(name)
                    && isInRange(verifiable, localDateFrom, localDateTo)) {
                sum += Integer.parseInt(salaryInfo[INDEX_HOUR])
                        * Integer.parseInt(salaryInfo[INDEX_HOUR_RATE]);
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
