package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final String DATE_PATTERN = "d.M.yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String employee : names) {
            int money = 0;
            for (String record : data) {
                String[] currentRecord = record.split(" ");
                LocalDate recordDate = LocalDate.parse(currentRecord[DATE_INDEX], FORMATTER);

                if (!currentRecord[NAME_INDEX].equals(employee)
                        || recordDate.isAfter(toDate)
                        || recordDate.isBefore(fromDate)) {
                    continue;
                }

                money += Integer.parseInt(currentRecord[HOURS_INDEX])
                        * Integer.parseInt(currentRecord[INCOME_PER_HOUR_INDEX]);
            }
            salaryInfo.append("\n").append(employee).append(" - ").append(money);
        }

        return salaryInfo.toString();
    }
}
