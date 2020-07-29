package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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
        for (String name : names) {
            int salary = 0;
            for (String info : data) {
                String[] information = info.split(" ");
                LocalDate currentDate = LocalDate.parse(information[0], DATE_FORMAT);
                if (name.equals(information[1])
                        && (currentDate.isBefore(endDate) || currentDate.equals(endDate))
                        && (currentDate.isAfter(startDate) || currentDate.equals(startDate))) {
                    salary += Integer.parseInt(information[2]) * Integer.parseInt(information[3]);
                }
            }
            result.append("\n").append(name).append(" - ").append(salary);
        }
        return new String(result);
    }
}
