package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DAY = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int PAY_HOURS = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToLocal = LocalDate.parse(dateTo, FORMATTER);
        for (String name: names) {
            int salary = 0;
            for (String date: data) {
                String [] dataNew = date.split(" ");
                LocalDate dateLocal = LocalDate.parse(dataNew[DAY], FORMATTER);
                String nameData = dataNew[NAME];
                if (name.equals(nameData)
                        && (dateLocal.isEqual(dateFromLocal)
                        || dateLocal.isAfter(dateFromLocal))
                        && (dateLocal.isEqual(dateToLocal)
                        || dateLocal.isBefore(dateToLocal))) {
                    salary += Integer.parseInt(dataNew[HOURS])
                            * Integer.parseInt(dataNew[PAY_HOURS]);
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return "Report for period " + dateFrom + " - " + dateTo + stringBuilder;
    }
}
