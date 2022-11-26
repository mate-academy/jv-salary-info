package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
    {
        // Parsed dates for easier comparison
        final LocalDate parsedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        final LocalDate parsedDateTo = LocalDate.parse(dateTo, FORMATTER);

        int salary[] = new int[names.length];
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                // Data format - "date name hours income_per_hour"
                String[] dataSplit = data[j].split(" ");
                LocalDate currentDate = LocalDate.parse(dataSplit[0], FORMATTER);
                if (names[i].equals(dataSplit[1])) {
                    if (!currentDate.isBefore(parsedDateFrom) && !currentDate.isAfter(parsedDateTo)) {
                        salary[i] += Integer.parseInt(dataSplit[2]) * Integer.parseInt(dataSplit[3]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary[i]);
        }
        return builder.toString();
    }
}
