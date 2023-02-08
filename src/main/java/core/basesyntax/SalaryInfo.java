package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder reportForPeriod = new StringBuilder();
        reportForPeriod.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        int[] salary = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            for (String dataLoop : data) {
                String dataLoopSplit = dataLoop.split(" ")[0];
                LocalDate localDateParse = LocalDate.parse(dataLoopSplit, FORMATTER);
                if ((localDateParse.isAfter(dateFromFormatted)
                        || localDateParse.isEqual(dateFromFormatted))
                        && (localDateParse.isBefore(dateToFormatted)
                        || localDateParse.isEqual(dateToFormatted))
                        && names[i].equals(dataLoop.split(" ")[1])) {
                    salary[i] += Integer.parseInt(dataLoop.split(" ")[2])
                            * Integer.parseInt(dataLoop.split(" ")[3]);
                }
            }
            reportForPeriod.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salary[i]);
        }
        return reportForPeriod.toString();
    }
}
