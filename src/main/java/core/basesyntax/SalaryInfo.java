package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        LocalDate dateF = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateT = LocalDate.parse(dateTo, FORMATTER);
        if (!(dateF.isBefore(dateT))) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuilder main = new StringBuilder("Отчёт за период "
                + dateFrom + " - " + dateTo + "\n");
        int count;
        for (String i : names) {
            main.append(i);
            count = 0;
            for (String j : data) {
                String[] str = j.split(" ");
                LocalDate strS = LocalDate.parse(str[0], FORMATTER);
                if (i.equals(str[1]) && ((strS.isBefore(dateT)) && dateF.isBefore(strS)
                        || (dateFrom.equals((str[0]))) || (dateTo.equals((str[0]))))) {
                    count += Integer.parseInt(str[2]) * Integer.parseInt(str[3]);
                }
            }
            main.append(" - ").append(count).append("\n");
        }
        return main.toString().substring(0, main.length() - 1);
    }
}
