package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] earnings = new int[names.length];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        for (String entry : data) {
            String[] dataEntry = entry.split(" ");
            LocalDate date = LocalDate.parse(dataEntry[0], formatter);

            if (!date.isBefore(fromDate) && !date.isAfter(toDate)) {
                int index = getIndex(names, dataEntry[1]);
                int hours = Integer.parseInt(dataEntry[2]);
                int rate = Integer.parseInt(dataEntry[3]);
                earnings[index] += hours * rate;
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator());
            report.append(names[i]).append(" - ").append(earnings[i]);
        }
        return report.toString();
    }

    private int getIndex(String[] array, String toFind) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(toFind)) {
                return i;
            }
        }

        return -1;
    }
}
