package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_FROM_DATA = 0;
    private static final int NAME_FROM_DATA = 1;
    private static final int WORKING_HOURS_FROM_DATA = 2;
    private static final int PER_HOUR_FROM_DATA = 3;
    private static final String SEPARATOR = " ";
    private static final DateTimeFormatter dateTimeFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, dateTimeFormatter);

        for (String name: names) {
            int salarySum = 0;
            for (String infoFromData: data) {
                String[] dataElements = infoFromData.split(SEPARATOR);
                LocalDate workDate
                        = LocalDate.parse(dataElements[DATE_FROM_DATA], dateTimeFormatter);
                if (dataElements[NAME_FROM_DATA].equals(name)
                        && (workDate.isAfter(localDateFrom)
                        || workDate.isEqual(localDateFrom))
                        && (workDate.isBefore(localDateTo)
                        || workDate.isEqual(localDateTo))) {
                    salarySum += Integer.parseInt(dataElements[WORKING_HOURS_FROM_DATA])
                            * Integer.parseInt(dataElements[PER_HOUR_FROM_DATA]);
                }
            }
            report.append(System.lineSeparator())
                    .append(name).append(" - ")
                    .append(salarySum);
        }
        return report.toString();
    }
}
