package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int INCOME_PER_HOUR = 3;
    private static final String SEPARATOR = " - ";
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("dd[.]MM[.]yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        String report = "Report for period " + dateFrom + " - " + dateTo;
        StringBuilder reportComplete = new StringBuilder(report);
        for (String name : names) {
            reportComplete.append(System.lineSeparator()).append(name).append(SEPARATOR);
            int salary = 0;
            for (String record : data) {
                String[] splitRecord = record.split(" ");
                LocalDate recordDate = LocalDate.parse(splitRecord[DATE], FORMATTER);
                if (splitRecord[NAME].equals(name)
                        && (recordDate.isEqual(from) || recordDate.isEqual(to)
                        || (recordDate.isAfter(from) && recordDate.isBefore(to)))) {
                    salary = salary
                            + (Integer.parseInt(splitRecord[HOURS])
                            * Integer.parseInt(splitRecord[INCOME_PER_HOUR]));
                }
            }
            reportComplete.append(salary);
        }
        return reportComplete.toString();
    }
}
