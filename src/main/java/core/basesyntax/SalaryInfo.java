package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int RATE_INDEX = 2;
    private static final int TIME_INDEX = 3;
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builderResult = new StringBuilder().append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String iterationName : names) {
            builderResult.append(System.lineSeparator());
            builderResult.append(iterationName).append(" - ");
            LocalDate localDateFrom = LocalDate.parse(dateFrom, timeFormatter);
            LocalDate localDateTo = LocalDate.parse(dateTo, timeFormatter);
            int sumSalary = 0;
            for (String iterationDate : data) {
                String[] whiteSpace = iterationDate.split(" ");
                LocalDate workDay = LocalDate.parse(whiteSpace[DATE_INDEX], timeFormatter);
                String name = whiteSpace[NAME_INDEX];
                if (name.equals(iterationName)) {
                    if (!workDay.isAfter(localDateTo) && !workDay.isBefore(localDateFrom)) {
                        sumSalary += Integer.parseInt(whiteSpace[RATE_INDEX])
                                * Integer.parseInt(whiteSpace[TIME_INDEX]);
                    }
                }
            }
            builderResult.append(sumSalary);
        }
        return builderResult.toString();
    }
}
