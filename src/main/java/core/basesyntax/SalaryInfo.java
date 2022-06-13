package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final String DATA_SEPARATOR = " ";
    private static final String MINUS_SEPARATOR = " - ";
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] sum = new int[names.length];
        int i = 0;
        StringBuilder report = new StringBuilder("Report for period ");
        report.append(dateFrom).append(MINUS_SEPARATOR).append(dateTo);
        // initiate date variables
        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.parse(dateFrom, formatter);
            endDate = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Date is not parsable.");
        }
        for (String name : names) {
            for (String dataValue : data) {
                String[] splittedData = dataValue.split(DATA_SEPARATOR);
                LocalDate currentDate = LocalDate.parse(splittedData[0], formatter);
                try {
                    LocalDate.parse(splittedData[0], formatter);
                } catch (DateTimeParseException e) {
                    throw new RuntimeException("Date is not parsable.");
                }
                if (name.equals(splittedData[1]) && currentDate.compareTo(startDate) >= 0
                        && currentDate.compareTo(endDate) <= 0) {
                    int hour = Integer.parseInt(splittedData[2]);
                    int payPerHour = Integer.parseInt(splittedData[3]);
                    sum[i] += hour * payPerHour;
                }
            }
            report.append(System.lineSeparator()).append(name)
                    .append(MINUS_SEPARATOR).append(sum[i]);
            i++;
        }
        return report.toString();
    }
}
