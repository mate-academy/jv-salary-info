package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATA = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURES = 2;
    private static final int INDEX_OF_INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom,FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo,FORMATTER);
        StringBuilder result = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String lines : data) {
                String[] splittedLine = lines.split(" ");
                LocalDate currentDate = LocalDate.parse(splittedLine[INDEX_OF_DATA], FORMATTER);
                if ((currentDate.equals(localDateFrom) || currentDate.isAfter(localDateFrom))
                        && (currentDate.equals(localDateTo) || currentDate.isBefore(localDateTo))
                        && splittedLine[INDEX_OF_NAME].equals(name)) {
                    salary = salary + Integer.parseInt(splittedLine[INDEX_OF_HOURES])
                            * Integer.parseInt(splittedLine[INDEX_OF_INCOME_PER_HOUR]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
