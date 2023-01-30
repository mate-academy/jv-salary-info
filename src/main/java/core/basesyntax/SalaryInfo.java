package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, formatter);

        StringBuilder reportForPeriod = new StringBuilder();
        reportForPeriod.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        int[] salary = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            for (String dataLoop : data) {
                if ((LocalDate.parse(dataLoop.split(" ")[0], formatter)
                        .isAfter(dateFromFormatted)
                        || LocalDate.parse(dataLoop.split(" ")[0], formatter)
                        .isEqual(dateFromFormatted))
                        && (LocalDate.parse(dataLoop.split(" ")[0], formatter)
                        .isBefore(dateToFormatted)
                        || LocalDate.parse(dataLoop.split(" ")[0], formatter)
                        .isEqual(dateToFormatted))
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
