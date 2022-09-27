package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_FOR_ARRAY_ZERO = 0;
    private static final int INDEX_FOR_ARRAY_ONE = 1;
    private static final int INDEX_FOR_ARRAY_TWO = 2;
    private static final int INDEX_FOR_ARRAY_THREE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        String[] arrayOfData;
        for (String name: names) {
            int salary = 0;
            for (String line: data) {
                arrayOfData = line.split(" ");
                LocalDate currentDate = LocalDate.parse(arrayOfData[INDEX_FOR_ARRAY_ZERO],
                        FORMATTER);
                if (localDateFrom.compareTo(currentDate) <= 0
                        && localDateTo.compareTo(currentDate) >= 0
                        && arrayOfData[INDEX_FOR_ARRAY_ONE].equals(name)) {
                    salary += (Integer.parseInt(arrayOfData[INDEX_FOR_ARRAY_THREE])
                            * Integer.parseInt(arrayOfData[INDEX_FOR_ARRAY_TWO]));
                }
            }
            result.append('\n').append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
