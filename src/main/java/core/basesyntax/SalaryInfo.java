package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + System.lineSeparator());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDay = LocalDate.parse(dateFrom, dateTimeFormatter).minusDays(1);
        LocalDate endDay = LocalDate.parse(dateTo, dateTimeFormatter).plusDays(1);
        int salary;
        for (String name : names) {
            salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] dataLine = data[i].split(" ");
                if (data[i].contains(name)
                        && LocalDate.parse(dataLine[0], dateTimeFormatter).isAfter(startDay)
                        && LocalDate.parse(dataLine[0], dateTimeFormatter).isBefore(endDay)) {
                    salary += Integer.parseInt(dataLine[2]) * Integer.parseInt(dataLine[3]);
                }
            }
            builder.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
