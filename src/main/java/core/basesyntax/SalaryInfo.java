package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(getFormattedData(dateFrom, "-", dateTo));
        for (String name : names) {
            int sumSalary = 0;
            for (String value : data) {
                String[] dataSplitted = value.split(" ");
                if (isWithinPeriod(dataSplitted[0], dateFrom, dateTo)
                        && name.equals(dataSplitted[1])) {
                    sumSalary += Integer.parseInt(dataSplitted[2])
                            * Integer.parseInt(dataSplitted[3]);
                }
            }
            result.append(System.lineSeparator())
                    .append(getFormattedData(name, "-", Integer.toString(sumSalary)));
        }
        return result.toString();
    }

    private String getFormattedData(String...data) {
        return String.join(" ", data);
    }

    private boolean isWithinPeriod(String data, String dataFrom, String dataTo) {
        return !encodeData(data).isBefore(encodeData(dataFrom))
                && !encodeData(data).isAfter(encodeData(dataTo));
    }

    private LocalDate encodeData(String data) {
        return LocalDate.parse(data, dateTimeFormatter);
    }
}
