package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        if (!(checkerDate(dateFrom, dateTo))) {
            throw new IllegalDateParametersException();
        }
        StringBuilder main = new StringBuilder("Отчёт за период "
                + dateFrom + " - " + dateTo + "\n");
        int count;
        for (String i : names) {
            main.append(i);
            count = 0;
            for (String j : data) {
                if (i.equals(j.split(" ")[1]) && checkerDate(j.split(" ")[0], dateTo)
                        && checkerDate(dateFrom, j.split(" ")[0])) {
                    count += Integer.parseInt(j.split(" ")[2]) * Integer.parseInt(j.split(" ")[3]);
                }
            }
            main.append(" - ").append(count).append("\n");
        }
        return main.toString().substring(0, main.length() - 1);
    }

    public Boolean checkerDate(String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return (dateFrom.equals(dateTo))
                || LocalDate.parse(dateFrom, formatter)
                .isBefore(LocalDate.parse(dateTo, formatter));

    }

}
