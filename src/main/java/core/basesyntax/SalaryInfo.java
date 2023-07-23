package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
    private static final String HYPHEN = " - ";
    private static final String SPACE = " ";
    private static final int INDEX = 1;
    private static final int HOUR = 2;
    private static final int RATE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] earn = new int[names.length];
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        for (String entry : data) {
            String[] dataEntry = entry.split(SPACE);
            LocalDate date = LocalDate.parse(dataEntry[0], FORMATTER);
            if (!date.isBefore(fromDate) && !date.isAfter(toDate)) {
                int index = getIndex(names, dataEntry[INDEX]);
                int hours = Integer.parseInt(dataEntry[HOUR]);
                int rate = Integer.parseInt(dataEntry[RATE]);
                earn[index] += hours * rate;
            }
        }
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ")
                .append(dateFrom)
                .append(HYPHEN)
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            reportBuilder.append(System.lineSeparator())
                    .append(names[i])
                    .append(HYPHEN)
                    .append(earn[i]);
        }
        return reportBuilder.toString();
    }

    private int getIndex(String[] index, String toFind) {
        for (int i = 0; i < index.length; i++) {
            if (index[i].equals(toFind)) {
                return i;
            }
        }
        return -1;
    }
}
