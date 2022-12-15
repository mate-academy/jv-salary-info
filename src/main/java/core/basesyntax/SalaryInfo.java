package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int WORKING_DATE = 0;
    private static final int EMPLOYEE_NAME = 1;
    private static final int WORKING_HOURS = 2;
    private static final int INCOME_PER_HOUR = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLD = LocalDate.parse(dateFrom,formatter).minusDays(1);
        LocalDate dateToLD = LocalDate.parse(dateTo,formatter).plusDays(1);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int income = 0;
            for (String dataForSplit : data) {
                String[] dataArray = dataForSplit.split(" ");
                LocalDate date = LocalDate.parse(dataArray[WORKING_DATE],formatter);
                if (name.equals(dataArray[EMPLOYEE_NAME]) && dateFromLD.isBefore(date)
                        && dateToLD.isAfter(date)) {
                    income = income + Integer.parseInt(dataArray[INCOME_PER_HOUR])
                            * Integer.parseInt(dataArray[WORKING_HOURS]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(income);
        }
        return builder.toString();
    }
}
