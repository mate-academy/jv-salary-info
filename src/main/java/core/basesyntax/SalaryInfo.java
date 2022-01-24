package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int salary;
        int hours;
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder =
                new StringBuilder("Report for period ")
                        .append(dateFrom).append(" - ").append(dateTo);
        for (String string : names) {
            int sumSalary = 0;
            String name;
            LocalDate date;
            for (String parameter : data) {
                String[] values = parameter.split(" ");
                date = LocalDate.parse(values[0], FORMATTER);
                name = values[1];
                hours = Integer.parseInt(values[2]);
                salary = Integer.parseInt(values[3]);
                if ((date.isAfter(fromDate) && date.isBefore(toDate)
                        || date.isEqual(toDate)) && string.equals(name)) {
                    sumSalary += hours * salary;
                }
            }
            stringBuilder.append(System.lineSeparator()).append(string)
                    .append(" - ").append(sumSalary);
        }
        return stringBuilder.toString();
    }
}
