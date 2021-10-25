package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final int DATA = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int PAYMENT = 3;

    public String getSalaryInfo(String[] names, String[] data, String from, String to) {
        LocalDate dateFrom = LocalDate.parse(from, FORMATTER);
        LocalDate dateTo = LocalDate.parse(to, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom.format(FORMATTER)).append(" - ")
                .append(dateTo.format(FORMATTER)).append(System.lineSeparator());
        int counter = 0;
        for (String name : names) {
            stringBuilder.append(name).append(" - ");
            int salary = 0;
            for (String pool : data) {
                String[] dataArray = pool.split(" ");
                LocalDate arrayDate = LocalDate.parse(dataArray[DATA], FORMATTER);
                if (arrayDate.isAfter(dateFrom.minusDays(1))
                        && arrayDate.isBefore(dateTo.plusDays(1)) && dataArray[NAME].equals(name)) {
                    int hours = Integer.parseInt(dataArray[HOURS]);
                    int payment = Integer.parseInt(dataArray[PAYMENT]);
                    salary += hours * payment;
                }
            }
            counter++;
            stringBuilder.append(salary);
            if (counter < names.length) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }
}
