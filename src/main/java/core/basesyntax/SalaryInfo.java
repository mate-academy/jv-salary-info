package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PAY_RATE_INDEX = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        StringBuilder report = new StringBuilder();

        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            report.append(System.lineSeparator()).append(name).append(" - ");
            int count = 0;
            for (String row : data) {
                String[] values = row.split(" ");
                if (name.equals(values[NAME_INDEX])) {
                    LocalDate date = LocalDate.parse(values[DATE_INDEX], formatter);
                    if (isInPeriod(date, fromDate, toDate)) {
                        count += Integer.valueOf(values[HOURS_INDEX]) 
                                * Integer.valueOf(values[PAY_RATE_INDEX]);
                    }
                }
            }
            report.append(count);
        }
        return report.toString();
    }

    private boolean isInPeriod(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return (dateFrom.isEqual(date) || dateFrom.isBefore(date))
                && (dateTo.isEqual(date) || dateTo.isAfter(date));
    }
}
