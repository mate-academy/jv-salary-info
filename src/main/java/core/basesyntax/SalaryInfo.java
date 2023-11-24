package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int NUMBER_OF_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final String DELIMITER = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] splitData = data[j].split(DELIMITER);
                LocalDate startDate = LocalDate.parse(dateFrom, formatter);
                LocalDate endDate = LocalDate.parse(dateTo, formatter);
                LocalDate currentDate = LocalDate.parse(splitData[DATE_INDEX], formatter);
                if (splitData[NAME_INDEX].equals(names[i])
                        && !currentDate.isBefore(startDate) && !currentDate.isAfter(endDate)) {
                    salary += Integer.parseInt(splitData[NUMBER_OF_HOURS_INDEX])
                            * Integer.parseInt(splitData[INCOME_PER_HOUR_INDEX]);
                }
            }
            result.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary);
        }
        return result.toString();
    }
}
