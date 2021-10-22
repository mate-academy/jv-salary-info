package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate beginOfPeriod = LocalDate.parse(dateFrom, DATE_FORMAT).minusDays(1);
        LocalDate endOfPeriod = LocalDate.parse(dateTo, DATE_FORMAT).plusDays(1);
        StringBuilder stringBuilder = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo);

        for (String name : names) {
            int totalAmount = 0;
            for (String dataLine : data) {
                String particularDay = dataLine.substring(0, dataLine.indexOf(" "));
                LocalDate particularDate = LocalDate.parse(particularDay, DATE_FORMAT);
                String payments = dataLine.substring(dataLine.indexOf(name) + name.length()).trim();
                if (dataLine.contains(name) && particularDate.isAfter(beginOfPeriod)
                        && particularDate.isBefore(endOfPeriod)) {
                    int workingHours = Integer.parseInt(payments
                            .substring(0, payments.indexOf(" ")));
                    int paymentPerHour = Integer.parseInt(payments
                            .substring(payments.indexOf(" ")).trim());
                    totalAmount += (workingHours * paymentPerHour);
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalAmount);
        }
        return stringBuilder.toString().trim();
    }
}
