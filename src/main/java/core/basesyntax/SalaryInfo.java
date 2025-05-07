package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final String DELIMITER = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();
        setFirstReportLine(dateFrom, dateTo, stringBuilder);
        parsingData(names, data, fromDate, toDate, stringBuilder);
        return stringBuilder.toString();
    }

    private void parsingData(String[] names,
                             String[] data,
                             LocalDate fromDate,
                             LocalDate toDate,
                             StringBuilder stringBuilder) {
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] parts = line.split(DELIMITER);
                String dateString = parts[DATE_INDEX];
                String employeeName = parts[NAME_INDEX];
                if (employeeName.equals(name)) {
                    if (isDateInRange(LocalDate.parse(dateString, FORMATTER), fromDate, toDate)) {
                        int hours = Integer.parseInt(parts[HOURS_INDEX]);
                        int rate = Integer.parseInt(parts[RATE_INDEX]);
                        salary += hours * rate;
                    }
                }
            }
            addReportLine(name, salary, stringBuilder);
        }
    }

    private void setFirstReportLine(String dateFrom, String dateTo, StringBuilder stringBuilder) {
        stringBuilder.append(String.format("Report for period %s - %s", dateFrom, dateTo));

    }

    private void addReportLine(String name, int salary, StringBuilder stringBuilder) {
        stringBuilder.append(System.lineSeparator()).append(String.format("%s - %d", name, salary));
    }

    private boolean isDateInRange(LocalDate date, LocalDate fromDate, LocalDate toDate) {
        return (date.isAfter(fromDate)
                && date.isBefore(toDate))
                || date.isEqual(fromDate)
                || date.isEqual(toDate);
    }
}
