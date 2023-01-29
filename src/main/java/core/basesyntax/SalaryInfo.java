package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDate;
        LocalDate localDateFrom = getLocalDate(dateFrom);
        LocalDate localDateTo = getLocalDate(dateTo);
        StringBuilder builder = new StringBuilder();
        int salary = 0;
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            for (String line : data) {
                localDate = getLocalDate(line);
                if ((localDateFrom.equals(localDate) || localDate.equals(localDateTo)
                        || (localDate.isAfter(localDateTo) && localDate.isBefore(localDateFrom)))
                        && line.contains(name)) {
                    salary += getSalaryOfDay(line, name);
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }

    public LocalDate getLocalDate(String line) {
        String[] date = line.split(" ");
        return LocalDate.parse(date[0], FORMATTER);
    }

    public int getSalaryOfDay(String line, String name) {
        String[] dataLine = line.split(" ");
        return Integer.parseInt(dataLine[3]) * Integer.parseInt(dataLine[2]);
    }
}
