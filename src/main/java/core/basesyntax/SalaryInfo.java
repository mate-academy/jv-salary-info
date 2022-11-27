package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOUR = 2;
    private static final int INDEX_OF_INCOME = 3;
    private static final int INDEX_OF_EMPLOYEE = -1;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Report for period %s - %s", dateFrom, dateTo))
                .append(System.lineSeparator());
        int[] salaryInfo = new int[names.length];
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        int indexEmployee = INDEX_OF_EMPLOYEE;
        for (String name : names) {
            indexEmployee++;
            for (String line : data) {
                String[] info = line.split(" ");
                LocalDate date = LocalDate.parse(info[INDEX_OF_DATE], formatter);
                if (date.compareTo(localDateFrom) >= 0
                        && date.compareTo(localDateTo) <= 0
                        && name.equals(info[INDEX_OF_NAME])) {
                    int hour = Integer.parseInt(info[INDEX_OF_HOUR]);
                    int income = Integer.parseInt(info[INDEX_OF_INCOME]);
                    salaryInfo[indexEmployee] += hour * income;
                }
            }
            stringBuilder.append(name)
                    .append(" - ")
                    .append(salaryInfo[indexEmployee]);
            if (indexEmployee < names.length - 1) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }
}
