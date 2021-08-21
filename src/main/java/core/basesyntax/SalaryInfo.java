package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String pattern = "dd.MM.yyyy";
    private static final DateTimeFormatter SIMPLE_DATE_FORMAT =
            DateTimeFormatter.ofPattern(pattern);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int moneyEarned;
        String[] dataOfIndex;
        LocalDate dateFrom2 = LocalDate.parse(dateFrom, SIMPLE_DATE_FORMAT).minusDays(1);
        LocalDate dateTo2 = LocalDate.parse(dateTo, SIMPLE_DATE_FORMAT).plusDays(1);
        LocalDate dateOfWork;
        StringBuilder reportForPeriod = new StringBuilder()
                .append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            moneyEarned = 0;
            for (String datum : data) {
                dataOfIndex = datum.split(" ");
                dateOfWork = LocalDate.parse(dataOfIndex[0], SIMPLE_DATE_FORMAT);
                if (name.equals(dataOfIndex[1]) && dateOfWork.isAfter(dateFrom2)
                        && dateOfWork.isBefore(dateTo2)) {
                    moneyEarned +=
                            Integer.parseInt(dataOfIndex[2]) * Integer.parseInt(dataOfIndex[3]);
                }
            }
            reportForPeriod.append(System.lineSeparator())
                    .append(name).append(" - ").append(moneyEarned);
        }
        return reportForPeriod.toString();
    }
}


