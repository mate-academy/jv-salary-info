package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_FOR_NAMES = 0;
    private static final int INDEX_FOR_DATA = 0;
    private static final int INDEX_FOR_DATE_IN_DATA = 0;
    private static final int INDEX_FOR_NAME_IN_DATA = 1;
    private static final int INDEX_FOR_HOURS_IN_DATA = 2;
    private static final int INDEX_FOR_SALARY_PER_HOUR_IN_DATA = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        for (int i = INDEX_FOR_NAMES; i < names.length; i++) {
            for (int j = INDEX_FOR_DATA; j < data.length; j++) {
                String[] temp = data[j].split(" ");
                LocalDate currentDate = LocalDate.parse(temp[INDEX_FOR_DATE_IN_DATA], FORMATTER);
                if (temp[INDEX_FOR_NAME_IN_DATA].equals(names[i])
                        && ((currentDate.isEqual(localDateFrom) || currentDate.isEqual(localDateTo))
                        || (currentDate.isAfter(localDateFrom)
                        && currentDate.isBefore(localDateTo)))) {
                    salaries[i] += Integer.parseInt(temp[INDEX_FOR_HOURS_IN_DATA])
                            * Integer.parseInt(temp[INDEX_FOR_SALARY_PER_HOUR_IN_DATA]);
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = INDEX_FOR_NAMES; i < names.length; i++) {
            builder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }
        return builder.toString();
    }
}
