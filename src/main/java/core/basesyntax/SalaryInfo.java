package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int TIME_INDEX = 2;
    private static final int PROFIT_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);

        for (String name : names) {
            int salary = 0;
            for (String content : data) {
                String[] dataContent = content.split(" ");
                LocalDate workDay = LocalDate.parse(dataContent[DATE_INDEX], FORMATTER);
                boolean validDate = ((workDay.isAfter(startDate) || workDay.isEqual(startDate))
                        && (workDay.isBefore(endDate) || workDay.isEqual(endDate)));
                if (dataContent[NAME_INDEX].equals(name) && validDate) {
                    salary += Integer.parseInt(dataContent[TIME_INDEX])
                            * Integer.parseInt(dataContent[PROFIT_INDEX]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return builder.toString();
    }
}
