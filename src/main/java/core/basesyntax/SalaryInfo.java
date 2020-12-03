package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER).plusDays(1);
        StringBuilder result = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo + "\n");
        for (String name : names) {
            int salary = 0;
            for (String informData : data) {
                String[] line = informData.split(" ");
                LocalDate factDate = LocalDate.parse(line[0], FORMATTER);
                if (line[1].equals(name) && (factDate.isBefore(to) && factDate.isAfter(from))) {
                    salary += Integer.parseInt(line[2]) * Integer.parseInt(line[3]);
                }
            }
            result.append(name).append(" - ").append(salary).append("\n");
        }
        return result.toString().trim();
    }
}
