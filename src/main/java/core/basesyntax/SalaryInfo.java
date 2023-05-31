package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder resultMessage = new StringBuilder();
        resultMessage.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String period : data) {
                String[] dataElement = period.split(" ");
                LocalDate day = LocalDate.parse(dataElement[0], FORMATTER);
                if ((dataElement[1].equals(name))
                        && (day.isAfter(localDateFrom)
                        || day.isEqual(localDateTo))
                        && (day.isBefore(localDateTo)
                        || day.isEqual(localDateTo))) {
                    salary += Integer.parseInt(dataElement[2])
                            * Integer.parseInt(dataElement[3]);
                }
            }
            resultMessage.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return resultMessage.toString();
    }
}
