package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter).plusDays(1);
        String result = "Report for period " + dateFrom + " - " + dateTo + "\n";
        for (String name : names) {
            int salary = 0;
            for (String informData : data) {
                String[] line = informData.split(" ");
                LocalDate factDate = LocalDate.parse(line[0], formatter);
                if (line[1].equals(name) && (factDate.isBefore(to) && factDate.isAfter(from))) {
                    salary += Integer.parseInt(line[2]) * Integer.parseInt(line[3]);
                }
            }
            result += name + " - " + salary + "\n";
        }

        return result.substring(0, result.length() - 1);
    }
}
