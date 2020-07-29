package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMAT);
        if (startDate.isAfter(endDate)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuilder result = new StringBuilder();
        result.append("Отчёт за период ").append(dateFrom)
                .append(" - ").append(dateTo);
        LocalDate ld;
        int salary = 0;
        for (String name : names) {
            for (String info : data) {
                String[] information = info.split(" ");
                ld = LocalDate.parse(information[0], DATE_FORMAT);
                if (name.equals(information[1]) && (ld.isBefore(endDate) || ld.equals(endDate)) && (ld.isAfter(startDate) || ld.equals(startDate))) {
                    salary += Integer.parseInt(information[2]) * Integer.parseInt(information[3]);
                }
            }
            result.append("\n").append(name).append(" - ").append(salary);
            salary = 0;
        }
        return new String(result);
    }
}
