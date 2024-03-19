package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        StringBuilder stringBuilder = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo);

        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);

        final int dataIndex = 0;
        final int nameIndex = 1;
        final int hoursIndex = 2;
        final int salaryPerHourIndex = 3;

        for (String name : names) {
            int salary = 0;
            for (String entry : data) {
                String[] entrySplit = entry.split(" ");
                LocalDate entryDate = LocalDate.parse(entrySplit[dataIndex], formatter);
                String nameFromEntrySplit = entrySplit[nameIndex];

                if (!(entryDate.isBefore(localDateFrom) || entryDate.isAfter(localDateTo))) {
                    if (nameFromEntrySplit.equals(name)) {
                        int hoursInData = Integer.parseInt(entrySplit[hoursIndex]);
                        int salaryPerHour = Integer.parseInt(entrySplit[salaryPerHourIndex]);

                        salary += hoursInData * salaryPerHour;
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
