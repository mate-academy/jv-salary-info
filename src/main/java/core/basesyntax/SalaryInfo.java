package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int AMOUNT_INDEX = 2;
    public static final int PAY_FOR_HOUR_INDEX = 3;
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder(
                String.format("Report for period %1$s - %2$s", dateFrom, dateTo));
        LocalDate localDateFrom = stringToLocalDate(dateFrom);
        LocalDate localDateTo = stringToLocalDate(dateTo);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String [] recordSplitter = data[j].split(SEPARATOR);
                if (recordSplitter[NAME_INDEX].equals(names[i])) {
                    if (isDateBetweenTwoDays(localDateFrom, localDateTo,
                            stringToLocalDate(recordSplitter[DATE_INDEX]))) {
                        salary = salary + (Integer.parseInt(recordSplitter[AMOUNT_INDEX])
                                * Integer.parseInt(recordSplitter[PAY_FOR_HOUR_INDEX]));
                    }
                }
            }
            reportBuilder
                    .append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary);
        }
        return reportBuilder.toString();
    }

    private boolean isDateBetweenTwoDays(LocalDate startDate, LocalDate endDate,
                                         LocalDate targetDate) {
        return !targetDate.isBefore(startDate) && !targetDate.isAfter(endDate);
    }

    private LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}
