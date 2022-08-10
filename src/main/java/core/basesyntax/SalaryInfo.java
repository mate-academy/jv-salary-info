package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate dateFromInDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToInDate = LocalDate.parse(dateTo, FORMATTER);
        String lineSeparator = System.lineSeparator();
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        String[] dataLines;
        for (String name: names) {
            int salary = 0;
            for (String line: data) {
                dataLines = line.split(" ");
                LocalDate currentDate = LocalDate.parse(dataLines[0], FORMATTER);
                if (dateFromInDate.compareTo(currentDate) <= 0
                && dateToInDate.compareTo(currentDate) >= 0
                && dataLines[1].equals(name)) {
                    salary += (Integer.parseInt(dataLines[2])
                            * Integer.parseInt(dataLines[3]));
                }
            }
            result.append(lineSeparator).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
