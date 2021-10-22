package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_HOURS_PER_DAY = 2;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_SALARY_PER_DAY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salary = new int[names.length];
        for (int i = 0; i < data.length; i++) {
            LocalDate dateData = LocalDate
                    .parse(data[i].substring(0, data[i].indexOf(' ')), DATE_FORMATTER);
            if (dateData.isAfter(LocalDate.parse(dateFrom, DATE_FORMATTER).minusDays(1))
                    && dateData.isBefore(LocalDate.parse(dateTo, DATE_FORMATTER).plusDays(1))) {
                for (int j = 0; j < names.length; j++) {
                    String[] generalInfo = data[i].split(" ");
                    if (names[j].equals(generalInfo[INDEX_OF_NAME])) {
                        salary[j] += Integer.parseInt(generalInfo[INDEX_HOURS_PER_DAY])
                                * Integer.parseInt(generalInfo[INDEX_SALARY_PER_DAY]);
                    }
                }
            }
        }
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            reportBuilder.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salary[i]);
        }
        return reportBuilder.toString();
    }
}
