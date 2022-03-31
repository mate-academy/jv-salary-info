package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDay = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate lastDay = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                LocalDate currentDate = LocalDate.parse(splittedLine[0], FORMATTER);
                if (((currentDate.isAfter(firstDay) || currentDate.equals(firstDay))
                        && (currentDate.isBefore(lastDay) || currentDate.equals(lastDay)))
                        && splittedLine[NAME_INDEX].equals(name)) {
                    salary += Integer.parseInt(splittedLine[WORKING_HOURS_INDEX])
                        * Integer.parseInt(splittedLine[INCOME_PER_HOUR_INDEX]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
