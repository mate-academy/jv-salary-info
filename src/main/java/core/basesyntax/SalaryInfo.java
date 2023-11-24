package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_LENGTH = 10;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(localDateFrom.format(FORMATTER))
                .append(" - ").append(localDateTo.format(FORMATTER));

        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                if (line.contains(name)) {
                    String date = line.substring(0, DATE_LENGTH);
                    LocalDate localDate = LocalDate.parse(date, FORMATTER);
                    if (!localDate.isBefore(localDateFrom)
                            && !localDate.isAfter(localDateTo)) {
                        String[] parts = line.substring(DATE_LENGTH).trim().split(" ");
                        int salaryPerHour = Integer.parseInt(parts[2]);
                        int amountOfHours = Integer.parseInt(parts[1]);
                        salary += salaryPerHour * amountOfHours;
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
