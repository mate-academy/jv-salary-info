package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final LocalDate date = LocalDate.parse(dateFrom, formatter);
        final LocalDate date2 = LocalDate.parse(dateTo, formatter);
        int[] sums = new int[names.length];
        for (String datum : data) {
            String[] dataSplitter;
            dataSplitter = datum.split(" ");
            final LocalDate date3 = LocalDate.parse(dataSplitter[0], formatter);
            if (isDateWithinRange(date3, date, date2)) {
                int sum;
                int hours = Integer.parseInt((dataSplitter[2]));
                int perHour = Integer.parseInt((dataSplitter[3]));
                for (int j = 0; j < names.length; j++) {
                    if (dataSplitter[1].equals(names[j])) {
                        sum = hours * perHour;
                        sums[j] += sum;
                    }
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period").append(" ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append("\n").append(names[i]).append(" - ").append(sums[i]);
        }
        return stringBuilder.toString();
    }

    public static boolean isDateWithinRange(LocalDate dateToCheck, LocalDate dateFrom, LocalDate dateTo) {
        return (dateToCheck.isEqual(dateFrom) || dateToCheck.isAfter(dateFrom))
                && (dateToCheck.isEqual(dateTo) || dateToCheck.isBefore(dateTo));
    }
}
