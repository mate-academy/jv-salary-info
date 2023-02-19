package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder(String.format("Report for period %s - %s",
                dateFrom, dateTo));

        String[] foundData;
        int[] salaries = new int[names.length];
        for (String d : data) {
            String date = d.substring(0, 10);
            if (compareDates(dateFrom, date, dateTo)) {
                for (int i = 0; i < names.length; i++) {
                    String name = names[i];
                    if (d.contains(name)) {
                        foundData = d.split(" ");
                        salaries[i] += Integer.parseInt(foundData[2])
                                * Integer.parseInt(foundData[3]);
                    }
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            report.append(String.format("%s%s - %d",
                    System.lineSeparator(), names[i], salaries[i]));
        }

        return report.toString();
    }

    public static boolean compareDates(String dateFrom, String dataDate, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate parsedDataDate = LocalDate.parse(dataDate, formatter);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, formatter);
        boolean compareResult = parsedDateFrom.compareTo(parsedDataDate) <= 0
                && parsedDateTo.compareTo(parsedDataDate) >= 0;

        return compareResult;
    }
}
