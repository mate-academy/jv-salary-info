package core.basesyntax;

import static java.time.LocalDate.parse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final int DAY_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromFormatter = parse(dateFrom, FORMATTER);
        LocalDate dateToFormatter = parse(dateTo, FORMATTER);
        StringBuilder resultBuilder = new StringBuilder("Report for period ");
        resultBuilder.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int totalSalary = 0;
            for (String dataLine : data) {
                String[] dataElement = dataLine.split(" ");
                LocalDate day = parse(dataElement[DAY_INDEX], FORMATTER);
                if ((dataElement[NAME_INDEX].equals(name))
                        && (day.isAfter(dateFromFormatter)
                        || day.isEqual(dateFromFormatter))
                        && (day.isBefore(dateToFormatter)
                        || day.isEqual(dateToFormatter))) {
                    totalSalary += Integer.parseInt(dataElement[HOUR_INDEX])
                                * Integer.parseInt(dataElement[SALARY_PER_HOUR_INDEX]);
                }
            }
            resultBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
            totalSalary = 0;
        }
        return resultBuilder.toString();
    }
}
