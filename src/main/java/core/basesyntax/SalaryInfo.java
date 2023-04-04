package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String PATTERN_FORMAT = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT);
        LocalDate payPeriodStart = LocalDate.parse(dateFrom, formatter);
        LocalDate payPeriodEnd = LocalDate.parse(dateTo, formatter);

        int totalSalary = 0;
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(payPeriodStart).append(" - ")
                .append(payPeriodEnd).append(System.lineSeparator());

        for (String name : names) {
            for (int i = 0; i < data.length; i++) {
                String[] splitArrayOfData = data[i].split(" ");
                LocalDate particularDay = LocalDate.parse(splitArrayOfData[0], formatter);
                if (name.equals(splitArrayOfData[1]) &&
                        particularDay.isAfter(payPeriodStart.minusDays(1))
                        && particularDay.isBefore(payPeriodEnd.plusDays(1))) {
                    totalSalary += (Integer.parseInt(splitArrayOfData[2]) * Integer.parseInt(splitArrayOfData[3]));
                }
            }
            builder.append(name).append(" - ").append(totalSalary).append(System.lineSeparator());
        }
        return builder.toString();
    }
}

