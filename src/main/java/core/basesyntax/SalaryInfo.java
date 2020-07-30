
package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        StringBuilder header = new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo);
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateEnd = LocalDate.parse(dateTo, FORMATTER);
        if (dateEnd.compareTo(dateStart) == -1) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuilder result = new StringBuilder();
        for (String n : names) {
            int salary = 0;
            for (int i = 0;i < data.length; i++) {
                String[] str = data[i].split(" ");
                LocalDate actual = LocalDate.parse(str[0], FORMATTER);
                if (str[1].equals(n) && dateEnd.compareTo(actual) >= 0
                        && dateStart.compareTo(actual) <= 0) {
                    salary += Integer.parseInt(str[2]) * Integer.parseInt(str[3]);
                }

            }
            result.append("\n").append(n).append(" - ").append(salary);
        }
        return header.append(result.toString()).toString();
    }
}
