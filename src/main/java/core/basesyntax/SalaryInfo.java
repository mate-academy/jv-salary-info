package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final int INDEX_OF_ARRAY_ZERO = 0;
    private static final int INDEX_OF_ARRAY_ONE = 1;
    private static final int INDEX_OF_ARRAY_TWO = 2;
    private static final int INDEX_OF_ARRAY_THREE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder();
        result.append("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                String[] recordArray = record.split(" ");
                if (name.equals(recordArray[INDEX_OF_ARRAY_ONE])
                        && isBetweenDate(recordArray[INDEX_OF_ARRAY_ZERO], startDate, endDate)) {
                    salary += Integer.parseInt(recordArray[INDEX_OF_ARRAY_TWO])
                            * Integer.parseInt(recordArray[INDEX_OF_ARRAY_THREE]);
                }
            }
            result.append(System.lineSeparator() + name + " - " + salary);
        }
        return result.toString();
    }

    private boolean isBetweenDate(String date, LocalDate startDate, LocalDate endDate) {
        LocalDate localDate = LocalDate.parse(date, FORMATTER);
        return startDate.compareTo(localDate) <= 0 && endDate.compareTo(localDate) >= 0;
    }
}
