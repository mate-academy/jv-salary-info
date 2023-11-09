package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String entry : data) {
                String [] parts = entry.split(" ");
                LocalDate dataDate = LocalDate.parse(parts[0], FORMATTER);
                String employeeName = parts[1];
                int hours = Integer.parseInt(parts[2]);
                int salaryPerHour = Integer.parseInt(parts[3]);
                if (name.equals(employeeName)
                        && dataDate.isAfter(localDateDateFrom)
                        && dataDate.isBefore(localDateDateTo.plusDays(1))) {
                    salary += salaryPerHour * hours;
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
