package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER).plusDays(1);
        StringBuilder result = new StringBuilder().append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String informData : data) {
                String[] line = informData.split(" ");
                LocalDate factDate = LocalDate.parse(line[0], FORMATTER);
                if (line[1].equals(name) && (factDate.isBefore(to) && factDate.isAfter(from))) {
                    salary += Integer.parseInt(line[2]) * Integer.parseInt(line[3]);
                }
            }
            result.append("\n").append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
