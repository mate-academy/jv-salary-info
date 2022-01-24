package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateTimeFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromD = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateToD = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder result = new StringBuilder();
        String[] arrayOfData;
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            result.append(System.lineSeparator()).append(name).append(" - ");
            for (String datum : data) {
                arrayOfData = datum.split(" ");
                LocalDate dateNow = LocalDate.parse(arrayOfData[0],
                        DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                if (name.equals(arrayOfData[1])
                        && dateNow.isAfter(dateFromD)
                        && dateNow.isBefore(dateToD.plusDays(1))) {
                    salary = salary + (Integer.parseInt(arrayOfData[2])
                            * Integer.parseInt(arrayOfData[3]));
                }
            }
            result.append(salary);
        }
        return result.toString();
    }
}
