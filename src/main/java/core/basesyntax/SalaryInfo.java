package core.basesyntax;

import java.time.LocalDate;
import java.util.stream.Stream;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final String DATE_PATTERN = "d.M.y";
    private static final String REPORT_DELIMITER = " - ";
    private static final String REPORT_HEADING = "Report for period ";
    private static final String SYSTEM_LINE_SEPARATOR = System.lineSeparator();
    private static final DateConverter CONVERTER = new DateConverter(DATE_PATTERN);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder(REPORT_HEADING
                + dateFrom + REPORT_DELIMITER + dateTo + SYSTEM_LINE_SEPARATOR);
        for (String name : names) {
            builder.append(getIndividualReport(name, data, dateFrom, dateTo));
        }
        return builder.toString().strip();
    }

    private StringBuilder getIndividualReport(String name, String[] data,
                                              String dateFrom, String dateTo) {
        LocalDate from = CONVERTER.stringDateToLocale(dateFrom);
        LocalDate to = CONVERTER.stringDateToLocale(dateTo);
        StringBuilder builder = new StringBuilder(name + REPORT_DELIMITER);
        int salaryForPeriod = getSalaryForPeriod(name, data, from, to);
        builder.append(salaryForPeriod)
                .append(SYSTEM_LINE_SEPARATOR);
        return builder;
    }

    private int getSalaryForPeriod(String name, String[] data, LocalDate from,
                                   LocalDate to) {
        return Stream.of(data)
                .filter(x -> x.contains(name))
                .mapToInt(x -> {
                    String[] tmp = x.split(" ");
                    LocalDate tempDate = CONVERTER.stringDateToLocale(tmp[DATE_INDEX]);
                    if ((tempDate.isAfter(from) || tempDate.equals(from))
                            && (tempDate.isBefore(to) || tempDate.equals(to))) {
                        return Integer.parseInt(tmp[HOURS_INDEX])
                                * Integer.parseInt(tmp[SALARY_INDEX]);
                    }
                    return 0;
                })
                .sum();
    }
}
