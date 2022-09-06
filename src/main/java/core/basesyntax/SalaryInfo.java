package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateOne = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateTwo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name: names) {
            int salary = 0;
            for (String line: data) {
                String[] partsLine = line.split(" ");
                LocalDate date = LocalDate.parse(partsLine[0], FORMATTER);
                if (name.equals(partsLine[1]) && dateOne.compareTo(date) <= 0
                        && dateTwo.compareTo(date) >= 0) {
                    salary += Integer.valueOf(partsLine[2]) * Integer.valueOf(partsLine[3]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
