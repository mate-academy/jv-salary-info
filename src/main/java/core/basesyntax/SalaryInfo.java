package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        LocalDate dataFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dataTo = LocalDate.parse(dateTo, FORMATTER);
        if (dataFrom.isAfter(dataTo)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuilder heading = new StringBuilder();
        heading.append("Отчёт за период ").append(dateFrom).append(" - ")
                .append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splitData = line.split(" ");
                LocalDate localDate = LocalDate.parse(splitData[0], FORMATTER);
                if (line.contains(name) && (localDate.isAfter(dataFrom)
                        || localDate.isEqual(dataFrom))
                        && (localDate.isBefore(dataTo) || localDate.isEqual(dataTo))) {
                    salary += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            heading.append("\n").append(name).append(" - ").append(salary);
        }
        return heading.toString();
    }
}
