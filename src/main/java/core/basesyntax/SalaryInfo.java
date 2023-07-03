package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String DASH_BETWEEN_STATEMENTS = " - ";
    private static final String SPACE_SEPARATOR = " ";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int NUMBER_OF_PAYMENTS_INDEX = 2;
    private static final int AMOUNT_OF_PAYMENT_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder info = new StringBuilder();
        LocalDate ldFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate ldTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        info.append("Report for period ").append(dateFrom)
                .append(DASH_BETWEEN_STATEMENTS).append(dateTo);
        int[] salaries = countSalaries(ldFrom, ldTo, data, names);
        for (int i = 0; i < names.length; i++) {
            info.append(LINE_SEPARATOR).append(names[i])
                    .append(DASH_BETWEEN_STATEMENTS).append(salaries[i]);
        }

        return info.toString();

    }

    private int[] countSalaries(LocalDate ldFrom, LocalDate ldTo, String[] data, String[] names) {
        int[] salaries = new int[names.length];
        for (String dataLine : data) {
            String dateFromData = dataLine.split(SPACE_SEPARATOR)[DATE_INDEX];
            LocalDate ldData = LocalDate.parse(dateFromData, DATE_FORMATTER);
            if (!ldData.isAfter(ldTo) && !ldData.isBefore(ldFrom)) {
                for (int j = 0; j < names.length; j++) {
                    String nameFromData = dataLine.split(SPACE_SEPARATOR)[NAME_INDEX];
                    if (nameFromData.equals(names[j])) {
                        int numberOfPayments = Integer.parseInt(
                                dataLine.split(SPACE_SEPARATOR)[NUMBER_OF_PAYMENTS_INDEX]);
                        int amountOfPayment = Integer.parseInt(
                                dataLine.split(SPACE_SEPARATOR)[AMOUNT_OF_PAYMENT_INDEX]);
                        salaries[j] += numberOfPayments * amountOfPayment;
                    }
                }
            }
        }
        return salaries;
    }
}
