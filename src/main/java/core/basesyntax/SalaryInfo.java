package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String pattern = "dd.MM.yyyy";
    private static final DateTimeFormatter SIMPLE_DATE_FORMAT =
            DateTimeFormatter.ofPattern(pattern);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, SIMPLE_DATE_FORMAT).minusDays(1);
        LocalDate localDateTo = LocalDate.parse(dateTo, SIMPLE_DATE_FORMAT).plusDays(1);
        StringBuilder reportForPeriod = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int moneyEarned = 0;
            for (String datum : data) {
                String[] record = datum.split(" ");
                LocalDate dateOfWork = LocalDate.parse(record[0], SIMPLE_DATE_FORMAT);
                if (name.equals(record[1]) && dateOfWork.isAfter(localDateFrom)
                        && dateOfWork.isBefore(localDateTo)) {
                    moneyEarned +=
                            Integer.parseInt(record[2]) * Integer.parseInt(record[3]);
                }
            }
            reportForPeriod.append(System.lineSeparator())
                    .append(name).append(" - ").append(moneyEarned);
        }
        return reportForPeriod.toString();
    }
}


