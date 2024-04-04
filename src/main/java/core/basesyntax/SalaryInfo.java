package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOURS_INDEX = 3;
    private static final String HEADER = "Report for period ";
    private static final String SEPARATOR = " - ";
    private static final String DATA_SEPARATOR = " ";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append(HEADER).append(dateFrom).append(SEPARATOR).append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            int moneyEarned = 0;
            for (String basicData : data) {
                String[] elements = basicData.split(DATA_SEPARATOR);
                LocalDate entryDate = LocalDate.parse(elements[DATE_INDEX], formatter);
                if (!entryDate.isAfter(localDateTo)
                        && !entryDate.isBefore(localDateFrom)
                        && elements[NAME_INDEX].equals(name)) {
                    moneyEarned += Integer.parseInt(elements[WORKING_HOURS_INDEX])
                            * Integer.parseInt(elements[INCOME_PER_HOURS_INDEX]);
                }
            }
            result.append(System.lineSeparator())
                    .append(name).append(SEPARATOR)
                    .append(moneyEarned);

        }
        return result.toString();
    }
}
