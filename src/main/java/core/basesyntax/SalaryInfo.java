package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String pattern = "dd.MM.yyyy";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    private static final String SEPARATOR = " ";
    private static final int DATE_INDEX = 0;
    private static final int HOUR_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate beginDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        LocalDate currentDate;

        for (String name : names) {
            int sum = 0;
            for (String dateLine : data) {
                if (dateLine.contains(name)) {
                    String[] record = dateLine.split(SEPARATOR);
                    currentDate = LocalDate.parse(record[DATE_INDEX], formatter);

                    if (currentDate.isEqual(beginDate) || currentDate.isEqual(endDate)
                            || (currentDate.isAfter(beginDate) && currentDate.isBefore(endDate))) {
                        sum += Integer.parseInt(record[HOUR_INDEX])
                                * Integer.parseInt(record[INCOME_PER_HOUR_INDEX]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(sum);
        }
        return stringBuilder.toString();
    }
}
