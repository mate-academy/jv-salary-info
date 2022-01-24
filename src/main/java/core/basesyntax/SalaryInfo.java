package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_WORKING_HOUR = 2;
    private static final int INDEX_OF_INCOME = 3;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder report = new StringBuilder();
        int[] salary = new int[names.length];

        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            for (String currentData : data) {
                String[] splitData = currentData.split(" ");

                if (names[i].equals(splitData[INDEX_OF_NAME])
                        && LocalDate.parse(
                                splitData[INDEX_OF_DATE], formatter).isAfter(localDateFrom)
                        && localDateTo.compareTo(
                                LocalDate.parse(splitData[INDEX_OF_DATE], formatter)) >= 0) {
                    salary[i] += Integer.parseInt(splitData[INDEX_OF_WORKING_HOUR])
                            * Integer.parseInt(splitData[INDEX_OF_INCOME]);
                }
            }
            report.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salary[i]);
        }
        return report.toString();
    }
}
