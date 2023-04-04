package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String PATTERN_FORMAT = "dd.MM.yyyy";

    private static final int CONSTANT_NUM = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT);
        LocalDate payPeriodStart = LocalDate.parse(dateFrom, formatter);
        LocalDate payPeriodEnd = LocalDate.parse(dateTo, formatter);

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            int totalSalary = 0;
            for (String dataOfPerson : data) {
                String[] splitArrayOfData = dataOfPerson.split(" ");
                LocalDate particularDay = LocalDate.parse(splitArrayOfData[0], formatter);
                if (names[i].equals(splitArrayOfData[1])
                        && particularDay.isAfter(payPeriodStart.minusDays(CONSTANT_NUM))
                        && particularDay.isBefore(payPeriodEnd.plusDays(CONSTANT_NUM))) {
                    totalSalary += (Integer.parseInt(splitArrayOfData[2])
                            * Integer.parseInt(splitArrayOfData[3]));
                }
            }
            if (i == names.length - 1) {
                builder.append(names[i]).append(" - ").append(totalSalary);
            } else {
                builder.append(names[i]).append(" - ").append(totalSalary)
                        .append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}

