package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int INDEX_TWO = 2;
    private static final int INDEX_THREE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        for (String name : names) {
            int totalSalary = INDEX_ZERO;
            for (String record : data) {
                String[] recordParts = record.split(" ");
                LocalDate date = LocalDate.parse(recordParts[INDEX_ZERO], DATE_TIME_FORMATTER);
                if ((date.isEqual(toDate) || date.isEqual(fromDate))
                        || (date.isAfter(fromDate) && date.isBefore(toDate))) {
                    String nameAtRecord = recordParts[INDEX_ONE];

                    if (name.equals(nameAtRecord)) {
                        int hoursAtRecord = Integer.parseInt(recordParts[INDEX_TWO]);
                        int moneyPerHour = Integer.parseInt(recordParts[INDEX_THREE]);
                        int salary = moneyPerHour * hoursAtRecord;
                        totalSalary += salary;
                    }
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }

        return result.toString();
    }
}
