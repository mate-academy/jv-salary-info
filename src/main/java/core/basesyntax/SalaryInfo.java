package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final String DATA_SEPARATOR = " ";
    private static final String MINUS_SEPARATOR = " - ";
    private static final int DATE_INDEX = 0;
    private static final int PERSON_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int PAY_FOR_HOUR_INDEX = 3;
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] sum = new int[names.length];
        int i = 0;
        StringBuilder report = new StringBuilder("Report for period ");
        report.append(dateFrom).append(MINUS_SEPARATOR).append(dateTo);
        LocalDate startDate;
        LocalDate endDate;
        LocalDate currentDate;
        try {
            startDate = LocalDate.parse(dateFrom, formatter);
            endDate = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Date is not parsable.", e);
        }
        for (String name : names) {
            for (String dataValue : data) {
                String[] splittedData = dataValue.split(DATA_SEPARATOR);
                try {
                    currentDate = LocalDate.parse(splittedData[DATE_INDEX], formatter);
                } catch (DateTimeParseException e) {
                    throw new RuntimeException("Date is not parsable.", e);
                }
                if (name.equals(splittedData[PERSON_INDEX])
                        && currentDate.isAfter(startDate.minusDays(1))
                        && currentDate.isBefore(endDate.plusDays(1))) {
                    int hour = Integer.parseInt(splittedData[HOUR_INDEX]);
                    int payPerHour = Integer.parseInt(splittedData[PAY_FOR_HOUR_INDEX]);
                    sum[i] += hour * payPerHour;
                }
            }
            report.append(System.lineSeparator()).append(name)
                    .append(MINUS_SEPARATOR).append(sum[i]);
            i++;
        }
        return report.toString();
    }
}
