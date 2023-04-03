package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String pattern = "dd.MM.yyyy";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    private static final String WHITE_SPASE = " ";
    private static final int DATE = 0;
    private static final int HOUR  = 2;
    private static final int INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate beginDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        LocalDate currentDate;

        for (String name: names) {
            int sum = 0;
            for (String dateLine: data) {
                if (dateLine.contains(name)) {
                    String[] record = dateLine.split(WHITE_SPASE);
                    currentDate = LocalDate.parse(record[DATE], formatter);

                    if (currentDate.compareTo(beginDate) == 0 || currentDate.compareTo(endDate) == 0
                            || (currentDate.isAfter(beginDate) && currentDate.isBefore(endDate))) {
                        sum += Integer.parseInt(record[HOUR]) * Integer.parseInt(record[INCOME_PER_HOUR]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(sum);
        }
        return stringBuilder.toString();
    }
}
