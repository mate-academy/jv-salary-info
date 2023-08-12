package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int earnedMoney = 0;
            for (String part : data) {
                String[] parts = part.split(" ");
                LocalDate entryDate = LocalDate.parse(parts[DATE_INDEX], dateFormatter);
                String employeeName = parts[NAME_INDEX];
                int workingHours = Integer.parseInt(parts[WORKING_HOURS_INDEX]);
                int incomePerHour = Integer.parseInt(parts[INCOME_INDEX]);

                if (employeeName.equals(name) && !entryDate.isBefore(fromDate)
                        && !entryDate.isAfter(toDate)) {
                    earnedMoney += workingHours * incomePerHour;
                }
            }
            builder.append(name)
                    .append(" - ")
                    .append(earnedMoney)
                    .append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
