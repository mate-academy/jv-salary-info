package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_SALARY_PER_HOUR = 3;
    private static final String WHITE_SPACE = " ";
    private static final String ROW = " - ";
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (String currentData : data) {
                String[] array = currentData.split(WHITE_SPACE);
                LocalDate currentDate = LocalDate.parse(array[INDEX_OF_DATE], FORMATTER);
                if (names[i].equals(array[INDEX_OF_NAME])
                        && currentDate.compareTo(startDate) >= 0
                        && currentDate.compareTo(endDate) <= 0) {
                    salary += Integer.parseInt(array[INDEX_OF_HOURS])
                            * Integer.parseInt(array[INDEX_OF_SALARY_PER_HOUR]);
                }
            }
            if (i < names.length - 1) {
                builder.append(names[i])
                        .append(ROW).append(salary)
                        .append(System.lineSeparator());
            } else if (i == names.length - 1) {
                builder.append(names[i])
                        .append(ROW)
                        .append(salary);
            }
        }
        return "Report for period "
                + dateFrom
                + ROW
                + dateTo
                + System.lineSeparator()
                + builder;
    }
}
