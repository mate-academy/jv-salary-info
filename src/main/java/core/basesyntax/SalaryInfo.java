package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate to = LocalDate.parse(dateTo, formatter);
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String day : data) {
                String[] info = day.split(" ");
                LocalDate workDay = LocalDate.parse(info[0], formatter);
                if (info[1].equals(name) && (workDay.isEqual(from) || workDay.isEqual(to)
                        || (workDay.isAfter(from) & workDay.isBefore(to)))) {
                    salary += Integer.parseInt(info[2]) * Integer.parseInt(info[3]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
