package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int totalSalary = 0;
            for (String datum : data) {
                String[] record = datum.split(" ");
                LocalDate currentDate = LocalDate.parse(record[0], FORMATTER);
                if (name.equals(record[1])
                            && !currentDate.isBefore(localDateFrom)
                        && !currentDate.isAfter(localDateTo)) {
                    totalSalary += Integer.parseInt(record[2])
                            * Integer.parseInt(record[3]);
                }
            }
            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        return report.toString();
    }
}
