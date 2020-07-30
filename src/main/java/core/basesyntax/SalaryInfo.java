
package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateEnd = LocalDate.parse(dateTo, FORMATTER);
        if (dateEnd.compareTo(dateStart) == -1) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuilder result = new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (int i = 0;i < data.length; i++) {
                String[] dataline = data[i].split(" ");
                LocalDate actual = LocalDate.parse(dataline[0], FORMATTER);
                if (dataline[1].equals(name) && dateEnd.compareTo(actual) >= 0
                        && dateStart.compareTo(actual) <= 0) {
                    salary += Integer.parseInt(dataline[2]) * Integer.parseInt(dataline[3]);
                }

            }
            result.append("\n").append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
