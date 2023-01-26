package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter
            DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int NAME = 1;
    public static final int PERIOD = 0;
    public static final int HOURS = 2;
    public static final int SALARY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate formattedDateFrom;
        LocalDate formattedDateTo;
        LocalDate formattedDateData;

        formattedDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        formattedDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder builderReport = new StringBuilder();
        builderReport.append("Report for period ")
                        .append(dateFrom)
                        .append(" - ")
                        .append(dateTo);
        long[] salaries = new long[names.length];
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] lineOfDate = data[j].split(" ");
                formattedDateData = LocalDate.parse(lineOfDate[PERIOD], DATE_FORMATTER);
                if ((formattedDateData.isEqual(formattedDateFrom)
                        || formattedDateData.isAfter(formattedDateFrom))
                        && (formattedDateData.isBefore(formattedDateTo)
                        || formattedDateData.isEqual(formattedDateTo))
                        && names[i].equals(lineOfDate[NAME])) {
                    salaries[i] += Long.parseLong(lineOfDate[HOURS])
                            * Long.parseLong(lineOfDate[SALARY]);
                }
            }
            builderReport.append(System.lineSeparator())
                   .append(names[i])
                   .append(" - ")
                    .append(salaries[i]);
        }
        return builderReport.toString();
    }
}
