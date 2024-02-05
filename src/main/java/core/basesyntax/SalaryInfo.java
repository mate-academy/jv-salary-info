package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.uuuu");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parseDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate parseDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int moneyEarned = 0;
            for (String stringOfData : data) {
                String[] splitData = stringOfData.split(" ");
                LocalDate splitDataDate = LocalDate.parse(splitData[0], DATE_FORMATTER);
                if ((splitDataDate.isEqual(parseDateFrom)
                        || splitDataDate.isAfter(parseDateFrom))
                        && (splitDataDate.isEqual(parseDateTo)
                        || splitDataDate.isBefore(parseDateTo))
                        && splitData[1].equals(name)) {
                    moneyEarned += Integer.parseInt(splitData[2])
                            * Integer.parseInt(splitData[3]);
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(moneyEarned);
        }
        return builder.toString();
    }
}

