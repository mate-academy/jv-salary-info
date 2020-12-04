package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int salary;

        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder info = new StringBuilder();
        info.append("Report for period " + dateFrom + " - " + dateTo);

        for (int i = 0; i < names.length; i++) {
            salary = 0;
            for (int k = 0; k < data.length; k++) {
                String[] date = data[k].split(" ");
                LocalDate current = LocalDate.parse(date[0], FORMATTER);

                if (names[i].equals(date[1]) && (from.isBefore(current)
                        && (current.isBefore(to) | current.equals(to)))) {
                    int hours = Integer.parseInt(date[2]);
                    int salaryPerHour = Integer.parseInt(date[3]);
                    salary += (salaryPerHour * hours);
                }
            }
            info.append("\n" + names[i]).append(" - ").append(salary);
        }
        return info.toString();
    }
}
