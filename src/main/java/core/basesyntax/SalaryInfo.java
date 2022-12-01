package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PAYRATE_INDEX = 3;
    private static final String PATTERN = "dd.MM.yyyy";

    private LocalDate dateFrom;
    private LocalDate dateTo;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        var formatter = DateTimeFormatter.ofPattern(PATTERN);
        this.dateFrom = LocalDate.parse(dateFrom, formatter);
        this.dateTo = LocalDate.parse(dateTo, formatter);
        var report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (var name : names) {
            report.append(System.lineSeparator()).append(name).append(" - ");
            int count = 0;
            for (var row : data) {
                String[] values = row.split(" ");
                if (name.equals(values[NAME_INDEX])) {
                    var date = LocalDate.parse(values[DATE_INDEX], formatter);
                    if (isInPeriod(date)) {
                        count += Integer.valueOf(values[HOURS_INDEX]) 
                                * Integer.valueOf(values[PAYRATE_INDEX]);
                    }
                }

            }
            report.append(count);
        }
        return report.toString();
    }

    private boolean isInPeriod(LocalDate date) {
        return (dateFrom.isEqual(date) || dateFrom.isBefore(date)) 
                && (dateTo.isEqual(date) || dateTo.isAfter(date));
    }
}
