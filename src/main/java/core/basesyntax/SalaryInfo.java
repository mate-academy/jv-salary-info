package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo);
        final byte dateIndex = 0;
        final byte nameIndex = 1;
        final byte workingDaysIndex = 2;
        final byte hourlyRateIndex = 3;
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dtFrom = LocalDate.parse(dateFrom, formatter).minusDays(1);
        LocalDate dtTo = LocalDate.parse(dateTo, formatter).plusDays(1);
        int[] salary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String z:data) {
                String[] dataArray = z.split(" ");
                LocalDate neededDt = LocalDate.parse(dataArray[dateIndex], formatter);
                if (neededDt.isAfter(dtFrom) && neededDt.isBefore(dtTo)
                        && names[i].equals(dataArray[nameIndex])) {
                    salary[i] += Integer.parseInt(dataArray[workingDaysIndex])
                            * Integer.parseInt(dataArray[hourlyRateIndex]);
                }
            }
            report.append(System.lineSeparator() + names[i] + " - " + salary[i]);
        }
        return report.toString();
    }
}
