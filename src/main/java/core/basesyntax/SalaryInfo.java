package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        LocalDate localFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localTo = LocalDate.parse(dateTo, FORMATTER);

        if (localFrom.isAfter(localTo)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        StringBuilder result = new StringBuilder();
        result.append("Отчёт за период " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splitData = line.split(" ");
                LocalDate localDate = LocalDate.parse(splitData[0], FORMATTER);
                if (line.contains(name) && (localDate.isAfter(localFrom)
                        || localDate.isEqual(localFrom))
                        && (localDate.isBefore(localTo) || localDate.isEqual(localTo))) {
                    salary += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }

            }
            result.append("\n").append(name).append(" - ").append(salary);
        }
        return result.toString();

    }
}
