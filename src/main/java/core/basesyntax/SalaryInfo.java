package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATA_PATTERN = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int employeeEarning = 0;
        String[] parseRecord;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATA_PATTERN);
        LocalDate formattedDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate formattedDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            builder.append(System.lineSeparator());
            for (String recordDateNameHourEarnings : data) {
                parseRecord = recordDateNameHourEarnings.split(" ");
                LocalDate recordDate = LocalDate.parse(parseRecord[0], formatter);
                if ((recordDate.isAfter(formattedDateFrom)
                        | recordDate.isEqual(formattedDateFrom))
                        & (recordDate.isBefore(formattedDateTo)
                        | recordDate.isEqual(formattedDateTo))
                        && name.equals(parseRecord[1])) {
                    employeeEarning = employeeEarning
                            + (Integer.parseInt(parseRecord[2])
                            * Integer.parseInt(parseRecord[3]));
                }
            }
            builder.append(name)
                    .append(" - ")
                    .append(employeeEarning);
            employeeEarning = 0;
        }
        return builder.toString();
    }
}
