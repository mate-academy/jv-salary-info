package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String REPORT_HEADER = "Report for period ";
    private static final String HYPHEN = " - ";
    private static final int POSITION_OF_DATE = 0;
    private static final int POSITION_OF_NAME = 1;
    private static final int POSITION_OF_HOURS = 2;
    private static final int POSITION_OF_MONEY_PER_HOUR = 3;
    private static final String SEPARATOR = " ";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int moneyForPeriod;
        StringBuilder builder = new StringBuilder(REPORT_HEADER)
                .append(dateFrom).append(HYPHEN).append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        for (String name : names) {
            moneyForPeriod = 0;
            for (String record : data) {
                String[] infoFromRecord = record.split(SEPARATOR);
                if (name.equals(infoFromRecord[POSITION_OF_NAME])) {
                    LocalDate date = LocalDate.parse(infoFromRecord[POSITION_OF_DATE], FORMATTER);
                    if (date.isEqual(localDateFrom) || date.isEqual(localDateTo)
                            || (date.isAfter(localDateFrom) && date.isBefore(localDateTo))) {
                        moneyForPeriod += Integer.parseInt(infoFromRecord[POSITION_OF_HOURS])
                                * Integer.parseInt(infoFromRecord[POSITION_OF_MONEY_PER_HOUR]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name).append(HYPHEN)
                    .append(moneyForPeriod);
        }
        return builder.toString();
    }
}
