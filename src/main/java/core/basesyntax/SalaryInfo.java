package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            result.append(System.lineSeparator());
            int salary = 0;
            result.append(name).append(" - ");
            for (String line : data) {
                String[] processData = line.split(" ");
                LocalDate date = LocalDate.parse(processData[DATE_INDEX], FORMATTER);
                boolean isValidName = name.equals(processData[NAME_INDEX]);
                boolean isValidDate = ((date.isEqual(startDate) || date.isAfter(startDate))
                        && (date.isBefore(endDate) || date.isEqual(endDate)));
                if (isValidName && isValidDate) {
                    salary += (Integer.parseInt(processData[HOUR_INDEX])
                            * Integer.parseInt(processData[RATE_INDEX]));
                }
            }
            result.append(salary);
        }
        return result.toString();
    }
}
