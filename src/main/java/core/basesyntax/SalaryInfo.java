package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOUR = 2;
    private static final int INCOME = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateOne = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateTwo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name: names) {
            int salary = 0;
            for (String line: data) {
                String [] parseLine = line.split(" ");
                LocalDate dates = LocalDate.parse(parseLine[DATE], FORMATTER);
                if (name.equals(parseLine[NAME]) && dateOne.compareTo(dates) <= 0
                            && dateTwo.compareTo(dates) >= 0) {
                    salary += Integer.valueOf(parseLine[HOUR]) * Integer.valueOf(parseLine[INCOME]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();

    }
}
