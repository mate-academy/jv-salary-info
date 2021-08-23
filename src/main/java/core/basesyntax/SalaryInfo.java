package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parsedMinDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate parsedMaxDate = LocalDate.parse(dateTo, DATE_FORMAT);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int totalIncome = 0;
            for (String value : data) {
                String[] values = value.split(" ");
                LocalDate parsedDateOfValue = LocalDate.parse(values[0], DATE_FORMAT);
                if ((parsedDateOfValue.isAfter(parsedMinDate)
                        || parsedDateOfValue.isEqual(parsedMinDate))
                        && (parsedDateOfValue.isBefore(parsedMaxDate)
                        || parsedDateOfValue.isEqual(parsedMaxDate))
                        && name.equals(values[1])) {
                    totalIncome += Integer.parseInt(values[2]) * Integer.parseInt(values[3]);
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(totalIncome);
        }
        return builder.toString();
    }
}
