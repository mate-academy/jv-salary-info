package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        LocalDate dateFirst = LocalDate.parse(dateFrom, format);
        LocalDate dateLast = LocalDate.parse(dateTo, format);
        if (dateFirst.isAfter(dateLast)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        StringBuilder result = new StringBuilder();
        result.append("Отчёт за период ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String value : data) {
                String[] buffer = value.split(" ");
                LocalDate dateFromData = LocalDate.parse(buffer[0], format);
                if ((!dateFromData.isBefore(dateFirst))
                        && (!dateFromData.isAfter(dateLast)
                        && buffer[1].equals(name))) {

                    salary += Integer.parseInt(buffer[2]) * Integer.parseInt(buffer[3]);
                }
            }
            result.append("\n").append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
