package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo
                + System.lineSeparator());

        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMATTER);

        for (String name : names) {
            int totalSalary = 0;

            for (String records : data) {
                String[] dataParts = records.split(" ");
                LocalDate currentDate = LocalDate.parse(dataParts[0], DATE_FORMATTER);
                String currentName = dataParts[1];
                int currentHours = Integer.parseInt(dataParts[2]);
                int currentRate = Integer.parseInt(dataParts[3]);
                int salary = currentHours * currentRate;

                if (name.equals(currentName)
                        && !currentDate.isBefore(from)
                        && !currentDate.isAfter(to)) {
                    totalSalary += salary;
                }
            }
            result.append(name)
                    .append(" - ")
                    .append(totalSalary)
                    .append(System.lineSeparator());
        }
        result.setLength(result.length() - System.lineSeparator().length());
        return result.toString();
    }

}
