package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        LocalDate start = LocalDate.parse(dateFrom, formatter);
        LocalDate finish = LocalDate.parse(dateTo, formatter);
        if (start.compareTo(finish) > 0) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuilder result = new StringBuilder();
        result.append("Отчёт за период ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String element : data) {
                String[] splitted = element.split(" ");
                LocalDate date = LocalDate.parse(splitted[0], formatter);
                if (name.equals(splitted[1]) && !date.isBefore(start) && !date.isAfter(finish)) {
                    int hours = Integer.parseInt(splitted[2]);
                    int rate = Integer.parseInt(splitted[3]);
                    salary += hours * rate;
                }
            }
            result.append("\n").append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
