package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final String HEADER = "Report for period ";
    private static final String SEPARATOR = " - ";
    private static final String DELIMITER = " ";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder()
                .append(HEADER)
                .append(dateFrom)
                .append(SEPARATOR)
                .append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String information : data) {
                String[] informationArray = information.split(DELIMITER);
                LocalDate workDate = LocalDate
                        .parse(informationArray[DATE_INDEX], FORMATTER);
                int hours = Integer.parseInt(informationArray[HOUR_INDEX]);
                int incomePerHour = Integer.parseInt(informationArray[INCOME_INDEX]);
                if (name.equals(informationArray[NAME_INDEX])
                        && !workDate.isAfter(endDate)
                        && !workDate.isBefore(startDate)) {
                    salary += hours * incomePerHour;
                }
            }
            result.append(System.lineSeparator()).append(name).append(SEPARATOR).append(salary);
        }
        return result.toString();
    }
}
