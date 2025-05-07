package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATA_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period ");
        report.append(getFormattedData(dateFrom, "-", dateTo));

        for (String name : names) {
            int totalSalary = 0;
            for (String values : data) {
                String[] dataInfo = values.split(" ");
                if (isInRangeOfPeriod(dataInfo[DATA_INDEX], dateFrom, dateTo)
                        && name.equals(dataInfo[NAME_INDEX])) {
                    totalSalary += Integer.parseInt(dataInfo[HOURS_INDEX])
                            * Integer.parseInt(dataInfo[SALARY_INDEX]);
                }
            }
            report.append(System.lineSeparator())
                    .append(getFormattedData(name, "-", Integer.toString(totalSalary)));
        }
        return report.toString();
    }

    private boolean isInRangeOfPeriod(String data, String dataFrom, String dataTo) {
        return !encodeData(data).isBefore(encodeData(dataFrom))
                && !encodeData(data).isAfter(encodeData(dataTo));
    }

    private LocalDate encodeData(String data) {
        return LocalDate.parse(data, DATE_FORMATTER);
    }

    private String getFormattedData(String...data) {
        return String.join(" ", data);
    }
}
