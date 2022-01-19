package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_FROM_DATA = 0;
    private static final int NAME_FROM_DATA = 1;
    private static final int HOURS_FROM_DATA = 2;
    private static final int RATE_FROM_DATA = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder().append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int sum = 0;
            for (String datum : data) {
                String[] datumArr = datum.split(" ");
                String date = datumArr[DATE_FROM_DATA];
                String worker = datumArr[NAME_FROM_DATA];
                int hours = Integer.parseInt(datumArr[HOURS_FROM_DATA]);
                int rate = Integer.parseInt(datumArr[RATE_FROM_DATA]);
                if (name.equals(worker) && isCorrectDate(dateFrom, dateTo, date)) {
                    sum += hours * rate;
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(sum);
        }
        return report.toString();
    }

    public boolean isCorrectDate(String startInput, String stopInput, String workDate) {
        LocalDate start = LocalDate.parse(startInput, DATE_FORMAT).minusDays(1);
        LocalDate stop = LocalDate.parse(stopInput, DATE_FORMAT).plusDays(1);
        LocalDate work = LocalDate.parse(workDate, DATE_FORMAT);
        return start.isBefore(work) && work.isBefore(stop);
    }
}
