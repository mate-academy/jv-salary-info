package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SEPARATOR = " - ";
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_DATE = 0;
    private static final int MONEY_PER_DAY = 3;
    private static final int WORKING_DAYS = 2;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(SEPARATOR)
                .append(dateTo);
        String[] dateArray;
        for (String name : names) {
            int salary = 0;
            for (String dates : data) {
                dateArray = dates.split(" ");
                if (name.equals(dateArray[INDEX_OF_NAME])) {
                    LocalDate dateFromData = LocalDate.parse(dateArray[INDEX_OF_DATE], FORMATTER);
                    if ((dateFromData.isBefore(localDateTo) && dateFromData.isAfter(localDateFrom))
                            || dateFromData.isEqual(localDateTo)) {
                        salary += Integer.parseInt(dateArray[WORKING_DAYS])
                                * Integer.parseInt(dateArray[MONEY_PER_DAY]);
                    }
                }
            }
            result.append(System.lineSeparator()).append(name).append(SEPARATOR).append(salary);
        }
        return result.toString();
    }
}
