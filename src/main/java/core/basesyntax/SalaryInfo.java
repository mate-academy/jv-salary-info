package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final String separator = " - ";
    private final String systemLineSeparator = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final LocalDate dateDateFrom = LocalDate.parse(dateFrom, formatter);
        final LocalDate dateDateTo = LocalDate.parse(dateTo, formatter);
        final int[] salaries = new int[names.length];
        final StringBuilder output = new StringBuilder("Report for period ");
        output.append(dateFrom).append(separator).append(dateTo).append(systemLineSeparator);
        for (int i = 0; i < names.length; i++) {
            for (String dataLine : data) {
                final LocalDate date = LocalDate.parse(dataLine.split(" ")[0], formatter);
                final String name = dataLine.split(" ")[1];
                final int hours = Integer.parseInt(dataLine.split(" ")[2]);
                final int incomePerHour = Integer.parseInt(dataLine.split(" ")[3]);
                if (name.equals((names[i]))
                        && (dateDateFrom.isBefore(date) && dateDateTo.isAfter(date)
                        || dateDateFrom.isEqual(date) || dateDateTo.isEqual(date))) {
                    salaries[i] += hours * incomePerHour;
                }
            }
            output.append(names[i]).append(separator)
                    .append(salaries[i]).append(systemLineSeparator);
        }
        output.delete(output.indexOf(names[names.length - 1]) + names[names.length - 1].length()
                + Integer.toString(salaries[salaries.length - 1]).length() + 3, output.length());
        return output.toString();
    }
}
