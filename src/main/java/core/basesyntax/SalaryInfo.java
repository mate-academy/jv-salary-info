package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX_FOR_DATE = 0;
    private static final int DATE_INDEX_FOR_NAME = 1;
    private static final int DATE_INDEX_FOR_WORKED_HOURS = 2;
    private static final int DATE_INDEX_FOR_PAYMENT_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null || dateFrom.isEmpty() || dateTo.isEmpty()) {
            throw new IllegalArgumentException("Dates or data cannot be null or empty");
        }

        LocalDate startDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        StringBuilder sb = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + System.lineSeparator());

        for (String name : names) {
            int salary = 0;

            for (String info : data) {
                String[] parts = info.split(" ");
                String dateFromData = parts[DATE_INDEX_FOR_DATE];
                LocalDate date = LocalDate.parse(dateFromData, DATE_TIME_FORMATTER);
                String nameFromData = parts[DATE_INDEX_FOR_NAME];
                String hoursWorked = parts[DATE_INDEX_FOR_WORKED_HOURS];
                String paymentPerHour = parts[DATE_INDEX_FOR_PAYMENT_PER_HOUR];

                if (nameFromData.equals(name)
                        && (date.isEqual(startDate) || date.isAfter(startDate))
                        && (date.isEqual(endDate) || date.isBefore(endDate))) {
                    int hoursWorkedToInt = Integer.parseInt(hoursWorked);
                    int paymentPerHourToInt = Integer.parseInt(paymentPerHour);

                    salary += hoursWorkedToInt * paymentPerHourToInt;
                }
            }

            sb.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
