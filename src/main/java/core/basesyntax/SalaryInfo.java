package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static LocalDate workingDay;
    private static LocalDate parseDateFrom;
    private static LocalDate parseDateTo;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        parseDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        parseDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            builder.append(System.lineSeparator()).append(name).append(" - ");
            int total = 0;
            for (String line : data) {
                String[] lines = line.split(" ");
                workingDay = LocalDate.parse(lines[0], FORMATTER);
                int salary = 0;
                if (name.equals(lines[1])
                        && parseDateFrom.minusDays(1).isBefore(workingDay)
                        && workingDay.isBefore(parseDateTo.plusDays(1))) {
                    salary = Integer.parseInt(lines[2]) * Integer.parseInt(lines[3]);
                }
                total = total + salary;
            }
            builder.append(String.valueOf(total));
        }
        return builder.toString();
    }
}
