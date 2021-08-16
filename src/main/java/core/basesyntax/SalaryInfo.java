package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder().append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int sum = 0;

            for (String datum : data) {
                String date = datum.split(" ")[0];
                String worker = datum.split(" ")[1];
                int hours = Integer.parseInt(datum.split(" ")[2]);
                int rate = Integer.parseInt(datum.split(" ")[3]);

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
