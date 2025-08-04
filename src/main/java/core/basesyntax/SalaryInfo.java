package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                String dateTo) {
        StringBuilder salaryReport = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + System.lineSeparator());
        LocalDate firstDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate lastDate = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int fullSalary = 0;
            for (String entry : data) {
                String[] parts = entry.split(" ");
                LocalDate date = LocalDate.parse(parts[0], FORMATTER);
                if (!date.isBefore(firstDate) && !date.isAfter(lastDate)
                        && name.equals(parts[1])) {
                    int hours = Integer.parseInt(parts[2]);
                    int salaryPerHour = Integer.parseInt(parts[3]);
                    fullSalary += hours * salaryPerHour;
                }
            }
            salaryReport.append(name).append(" - ").append(fullSalary)
                    .append(System.lineSeparator());
        }
        salaryReport.deleteCharAt(salaryReport.length() - 1);
        return salaryReport.toString();
    }
}
