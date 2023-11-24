package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String PATTERN_FORMAT = "dd.MM.yyyy";
    private static final int FIRST_ELEMENT = 1;
    private static final int SECOND_ELEMENT = 2;
    private static final int THIRD_ELEMENT = 3;
    private static final int ZERO_INDEX = 0;

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
                LocalDate particularDay = LocalDate.parse(splitArrayOfData[ZERO_INDEX], formatter);
                if (names[i].equals(splitArrayOfData[FIRST_ELEMENT])
                        && particularDay.isAfter(payPeriodStart.minusDays(FIRST_ELEMENT))
                        && particularDay.isBefore(payPeriodEnd.plusDays(FIRST_ELEMENT))) {
                    totalSalary += (Integer.parseInt(splitArrayOfData[SECOND_ELEMENT])
                            * Integer.parseInt(splitArrayOfData[THIRD_ELEMENT]));
                }
            }
            if (i == names.length - FIRST_ELEMENT) {
                builder.append(names[i]).append(" - ").append(totalSalary);
            } else {
                builder.append(names[i]).append(" - ").append(totalSalary)
                        .append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}

