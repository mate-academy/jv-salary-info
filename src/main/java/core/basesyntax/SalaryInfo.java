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
        LocalDate dayFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dayTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int salary = 0;
            result.append(name).append(" - ");
            for (String item : data) {
                String[] processData = item.split(" ");
                LocalDate empDays = LocalDate.parse(processData[DATE_INDEX], FORMATTER);
                boolean nameInNames = name.equals(processData[NAME_INDEX]);
                boolean dateInPeriod = ((empDays.isEqual(dayFrom) || empDays.isAfter(dayFrom))
                        && (empDays.isBefore(dayTo) || empDays.isEqual(dayTo)));
                if (nameInNames && dateInPeriod) {
                    salary += (Integer.parseInt(processData[HOUR_INDEX])
                            * Integer.parseInt(processData[RATE_INDEX]));
                }
            }
            result.append(salary).append(System.lineSeparator());
        }
        return result.toString().stripTrailing();
    }
}
