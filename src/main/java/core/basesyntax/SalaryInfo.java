package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_SUBSTRING_START = 0;
    private static final int DATE_SUBSTRING_END = 10;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final String COLUMNS_SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        //I split the getSalaryInfo method into 4 separate methods
        int[] salary = salaryCounter(names, data, dateFrom, dateTo);
        return resultExecutor(names, salary, dateFrom, dateTo);
    }

    public int[] salaryCounter(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salary = new int[names.length];
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i])) {
                    LocalDate localDateCurrent = LocalDate.parse(data[j]
                                    .substring(DATE_SUBSTRING_START,
                                            DATE_SUBSTRING_END),
                            DATE_FORMATTER);
                    if (dateChecker(localDateFrom, localDateTo, localDateCurrent)) {
                        String[] currentData = data[j].split(COLUMNS_SEPARATOR);
                        salary[i] += Integer.parseInt(currentData[HOURS_INDEX])
                                * Integer.parseInt(currentData[RATE_INDEX]);
                    }
                }
            }
        }
        return salary;
    }

    public boolean dateChecker(LocalDate localDateFrom, LocalDate localDateTo,
                               LocalDate localDateCurrent) {
        //I think this way of returning is better
        //than creating two boolean variables and returning them
        return ((localDateFrom.isBefore(localDateCurrent)
                || localDateFrom.isEqual(localDateCurrent))
                && (localDateTo.isAfter(localDateCurrent)
                || localDateTo.isEqual(localDateCurrent)));
    }

    public String resultExecutor(String[] names, int[] salary, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder(String.format("Report for period "
                + dateFrom + " - " + dateTo + System.lineSeparator()));
        for (int i = 0; i < names.length; i++) {
            builder.append(String.format(names[i] + " - " + salary[i]) + System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
