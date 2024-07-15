package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final String SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period ");
        report.append(getFormattedData(dateFrom, "-", dateTo));
        for (String name : names) {
            int sumSalary = 0;
            for (String value : data) {
                String[] dataSplitted = value.split(SEPARATOR);
                if (isWithinPeriod(dataSplitted[DATE_INDEX], dateFrom, dateTo)
                        && name.equals(dataSplitted[NAME_INDEX])) {
                    sumSalary += Integer.parseInt(dataSplitted[HOUR_INDEX])
                        * Integer.parseInt(dataSplitted[INCOME_INDEX]);
                }
            }
            report.append(System.lineSeparator())
                    .append(getFormattedData(name, "-", Integer.toString(sumSalary)));
        }
        return report.toString();
    }

    private String getFormattedData(String...data) {
        return String.join(SEPARATOR, data);
    }

    private boolean isWithinPeriod(String data, String dataFrom, String dataTo) {
        return !encodeData(data).isBefore(encodeData(dataFrom))
            && !encodeData(data).isAfter(encodeData(dataTo));
    }

    private LocalDate encodeData(String data) {
        return LocalDate.parse(data, DATE_FORMATTER);
    }
}
