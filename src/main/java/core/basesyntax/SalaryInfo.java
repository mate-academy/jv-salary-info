package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate datef = LocalDate.parse(dateFrom, formatter);
        LocalDate dateT = LocalDate.parse(dateTo, formatter);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        int counter = 0;
        for (String name : names) {
            stringBuilder.append(name).append(" - ");
            int salary = 0;
            for (String pool : data) {
                String[] dataArray = pool.split(" ");
                LocalDate arrayDate = LocalDate.parse(dataArray[0], formatter);
                if (arrayDate.isAfter(datef.minusDays(1))
                        && arrayDate.isBefore(dateT.plusDays(1)) && dataArray[1].equals(name)) {
                    int x = Integer.parseInt(dataArray[2]);
                    int payment = Integer.parseInt(dataArray[3]);
                    salary += x * payment;
                }
            }
            stringBuilder.append(salary);
            if (counter < 2) {
                stringBuilder.append(System.lineSeparator());
            }
            counter++;
        }
        return stringBuilder.toString();
    }
}
