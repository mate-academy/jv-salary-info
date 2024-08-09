package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period ");
        report.append(getFormattedData(dateFrom, "-", dateTo));

        for (String name : names) {
            int totalSalary = 0;
            for (String values : data) {
                String[] dataInfo = values.split(" ");
                if (isInRangeOfPeriod(dataInfo[0], dateFrom, dateTo)
                        && name.equals(dataInfo[1])) {
                    totalSalary += Integer.parseInt(dataInfo[2]) * Integer.parseInt(dataInfo[3]);
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
