package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DELIMITER = " ";
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOUR = 2;
    private static final int INDEX_SALARY_PER_HOUR = 3;
    private static final String DELIMITER_FOR_BUILDER = " - ";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String date : data) {
                String[] split = date.split(DELIMITER);
                if (name.equals(split[INDEX_NAME]) && (parserDate(split[INDEX_DATE])
                        .isAfter(parserDate(dateFrom)) || parserDate(split[INDEX_DATE])
                        .equals(parserDate(dateFrom))) && (parserDate(split[INDEX_DATE])
                        .isBefore(parserDate(dateTo)) || parserDate(split[INDEX_DATE])
                        .equals(parserDate(dateTo)))) {
                    salary = salary + Integer.valueOf(split[INDEX_HOUR])
                        * Integer.valueOf(split[INDEX_SALARY_PER_HOUR]);
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name).append(DELIMITER_FOR_BUILDER)
                    .append(Integer.valueOf(salary).toString());
        }
        return salaryInfo.toString();
    }

    public static LocalDate parserDate(String date) {
        return LocalDate.parse(date, DATE_FORMAT);
    }
}
