package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder(String.format("Report for period %s - %s",
                dateFrom, dateTo));

        String[] foundData;
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String date = line.substring(0, 10);
                if (compareDates(dateFrom, date, dateTo)) {
                    if (line.contains(name)) {
                        foundData = line.split(" ");
                        salary += Integer.parseInt(foundData[2])
                                * Integer.parseInt(foundData[3]);
                    }
                }
            }
            report.append(String.format("%s%s - %d",
                    System.lineSeparator(), name, salary));
        }
        return report.toString();
    }

    public static boolean compareDates(String dateFrom, String dataDate, String dateTo) {
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate parsedDataDate = LocalDate.parse(dataDate, formatter);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, formatter);
        boolean compareResult = parsedDateFrom.compareTo(parsedDataDate) <= 0
                && parsedDateTo.compareTo(parsedDataDate) >= 0;

        return compareResult;
    }
}
