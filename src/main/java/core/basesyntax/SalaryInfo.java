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
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + ROW + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String currentData : data) {
                String[] array = currentData.split(WHITE_SPACE);
                LocalDate currentDate = LocalDate.parse(array[INDEX_OF_DATE], FORMATTER);
                if (name.equals(array[INDEX_OF_NAME])
                        && !currentDate.isBefore(startDate)
                        && !currentDate.isAfter(endDate)) {
                    salary += Integer.parseInt(array[INDEX_OF_HOURS])
                            * Integer.parseInt(array[INDEX_OF_SALARY_PER_HOUR]);
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name)
                    .append(ROW).append(salary);
        }
        return salaryInfo.toString();
    }
}
