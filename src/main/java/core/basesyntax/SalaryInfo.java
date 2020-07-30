package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        StringBuilder report = new StringBuilder("Отчёт за период " + dateFrom + " - "
                + dateTo + "\n");
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMAT);
        LocalDate dateFinish = LocalDate.parse(dateTo, FORMAT);

        if (dateStart.isAfter(dateFinish)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        dateStart = dateStart.minusDays(1);
        dateFinish = dateFinish.plusDays(1);
        int[] cashTotal = new int[names.length];
        for (String line : data) {
            String[] lineSepareted = line.split(" ");
            if (dateFinish.isAfter(LocalDate.parse(lineSepareted[0], FORMAT))
                    && LocalDate.parse(lineSepareted[0], FORMAT).isAfter(dateStart)) {
                cashTotal[Arrays.asList(names).indexOf(lineSepareted[1])] +=
                        Integer.parseInt(lineSepareted[2]) * Integer.parseInt(lineSepareted[3]);
            }
        }
        for (int i = 0; i < names.length; i++) {
            report.append(names[i]);
            report.append(" - ");
            report.append(cashTotal[i]);
            report.append("\n");
        }
        return report.toString().trim();

    }
}
