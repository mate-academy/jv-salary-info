package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public static final DateTimeFormatter
            TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int NAME = 1;
    public static final int PERIOD = 0;
    public static final int HOURS = 2;
    public static final int SALARY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom;
        LocalDate localDateTo;
        LocalDate localDateData;

        try {
            localDateFrom = LocalDate.parse(dateFrom, TIME_FORMATTER);
            localDateTo = LocalDate.parse(dateTo, TIME_FORMATTER);
        } catch (DateTimeParseException exc) {
            throw new RuntimeException("Period format for scanning is not valid", exc);
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                        .append(dateFrom)
                        .append(" - ")
                        .append(dateTo);
        long[] countSalary = new long[names.length];
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] lineOfDate = data[j].split(" ");
                try {
                    localDateData = LocalDate.parse(lineOfDate[PERIOD], TIME_FORMATTER);
                } catch (DateTimeParseException exc) {
                    throw new RuntimeException("Date format of USER is not valid", exc);
                }
                if ((localDateData.isEqual(localDateFrom) || localDateData.isAfter(localDateFrom))
                        && (localDateData.isBefore(localDateTo)
                        || localDateData.isEqual(localDateTo))
                        && names[i].equals(lineOfDate[NAME])) {
                    countSalary[i] += Long.parseLong(lineOfDate[HOURS])
                            * Long.parseLong(lineOfDate[SALARY]);
                }
            }
            builder.append(System.lineSeparator())
                   .append(names[i])
                   .append(" - ")
                    .append(countSalary[i]);
        }
        return builder.toString();
    }
}
