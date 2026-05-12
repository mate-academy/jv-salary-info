package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        int[] salaries = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String dataRow : data) {
                String[] row = dataRow.split(" ");
                LocalDate date = LocalDate.parse(row[0], FORMATTER);
                String name = row[1];
                int workingHours = Integer.parseInt(row[2]);
                int salary = Integer.parseInt(row[3]);
                if (names[i].equals(name) && !date.isBefore(from) && !date.isAfter(to)) {
                    salaries[i] += salary * workingHours;
                }
            }
            salaryInfo.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }
        return salaryInfo.toString();
    }
}
