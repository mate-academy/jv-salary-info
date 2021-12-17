package core.basesyntax.academy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        LocalDate start = LocalDate.parse(dateFrom, formatter);
        LocalDate stop = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] matrixFromstrData = datum.split(" ");
                LocalDate today = LocalDate.parse(matrixFromstrData[0], formatter);
                if ((matrixFromstrData[1].equals(name)) && (today.isAfter(start)
                            || start.equals(today))
                            && (today.isBefore(stop) || stop.equals(today))) {
                    salary = salary + (Integer.parseInt(matrixFromstrData[2])
                                * Integer.parseInt(matrixFromstrData[3]));
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ")
                        .append(salary);
        }
        return stringBuilder.toString();
    }
}
