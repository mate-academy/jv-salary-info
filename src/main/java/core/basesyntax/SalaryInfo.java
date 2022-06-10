package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.util.Date;

public class SalaryInfo {
    private static final String DATA_SEPARATOR = " ";
    private static final String MINUS_SEPARATOR = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] sum = new int[names.length];
        int i = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        StringBuilder report = new StringBuilder("Report for period ");
        report.append(dateFrom).append(MINUS_SEPARATOR).append(dateTo);
        for (String name : names) {
            for (String dataValue : data) {
                String[] splittedData = dataValue.split(DATA_SEPARATOR);
                LocalDate currentDate = LocalDate.parse(splittedData[0], formatter);
                LocalDate startDate = LocalDate.parse(dateFrom, formatter);
                LocalDate endDate = LocalDate.parse(dateTo, formatter);
                if (name.equals(splittedData[1]) && currentDate.isAfter(startDate.minusDays(1))
                        && currentDate.isBefore(endDate.plusDays(1))) {
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
