package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int INDEX_HOURS_WORKED = 2;
    private static final int INDEX_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                        .append(dateFrom)
                        .append(" - ")
                        .append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                String[] splitRecord = record.split(" ");
                LocalDate date = LocalDate.parse(splitRecord[DATE_INDEX], FORMATTER);
                if (name.equals(splitRecord[NAME_INDEX])) {
                    if ((date.isAfter(fromDate) || date.isEqual(fromDate))
                            && (date.isBefore(toDate) || (date.isEqual(toDate)))) {
                        salary += Integer.parseInt(splitRecord[INDEX_HOURS_WORKED])
                                * Integer.parseInt(splitRecord[INDEX_PER_HOUR]);
                    }
                }
            }
            report.append(System.lineSeparator())
                .append(name)
                .append(" - ")
                    .append(salary);
        }
        return report.toString();
    }
}
