package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final int arrayDate = 0;
    private final int arrayName = 1;
    private final int arrayHours = 2;
    private final int arrayBid = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        LocalDate dateFromData;
        Long totals = 0L;
        String nameData;
        long hours = 0;
        long bid = 0;

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            totals = 0L;
            for (int j = 0; j < data.length; j++) {

                if (data[j] != null) {
                    String[] splitData = data[j].split("\\s+");
                    dateFromData = LocalDate.parse(splitData[arrayDate], formatter);
                    nameData = splitData[arrayName];
                    hours = Long.parseLong(splitData[arrayHours]);
                    bid = Long.parseLong(splitData[arrayBid]);
                    if (names[i].equals(nameData)
                            && (!dateFromData.isBefore(from)
                            && !dateFromData.isAfter(to))) {
                        totals += (hours * bid);
                    }
                }
            }
            report.append(names[i])
                    .append(" - ")
                    .append(totals)
                    .append(System.lineSeparator());
        }
        int sepLen = System.lineSeparator().length();
        report.setLength(report.length() - sepLen);
        return report.toString();
    }
}
