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
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMATTER);
        info.append("Report for period ").append(dateFrom)
                .append(DASH_BETWEEN_STATEMENTS).append(dateTo);
        int[] salaries = countSalaries(from, to, data, names);
        for (int i = 0; i < names.length; i++) {
            info.append(LINE_SEPARATOR).append(names[i])
                    .append(DASH_BETWEEN_STATEMENTS).append(salaries[i]);
        }

        return info.toString();

    }

    private int[] countSalaries(LocalDate from, LocalDate to, String[] data, String[] names) {
        int[] salaries = new int[names.length];
        for (String dataLine : data) {
            String[] dataLineSplit = dataLine.split(SPACE_SEPARATOR);
            String dateFromData = dataLineSplit[DATE_INDEX];
            LocalDate dataFromFile = LocalDate.parse(dateFromData, DATE_FORMATTER);
            if (!dataFromFile.isAfter(to) && !dataFromFile.isBefore(from)) {
                for (int j = 0; j < names.length; j++) {
                    String nameFromData = dataLineSplit[NAME_INDEX];
                    if (nameFromData.equals(names[j])) {
                        int numberOfPayments = Integer.parseInt(
                                dataLineSplit[NUMBER_OF_PAYMENTS_INDEX]);
                        int amountOfPayment = Integer.parseInt(
                                dataLineSplit[AMOUNT_OF_PAYMENT_INDEX]);
                        salaries[j] += numberOfPayments * amountOfPayment;
                    }
                }
            }
        }
        return salaries;
    }
}
