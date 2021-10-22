package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, df);
        LocalDate to = LocalDate.parse(dateTo, df);
        StringBuilder sb = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + "\n");
        int salary = 0;

        for (String name : names) {
            for (String dates : data) {
                String[] split = dates.split(" ");
                LocalDate localDate = LocalDate.parse(split[0], df);
                if (name.equals(split[1]) && (localDate.isAfter(from) || localDate.equals(from))
                        && (localDate.isBefore(to) || localDate.equals(to))) {
                    salary += Integer.parseInt(split[2]) * Integer.parseInt(split[3]);
                }
            }
            sb.append(name + " - " + salary + System.lineSeparator());
            salary = 0;
        }
        return sb.toString().trim();
    }
}