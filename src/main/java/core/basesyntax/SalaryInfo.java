package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_LENGTH = 10;
    private static final int DATE_WITH_SPACES = 12;

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        StringBuilder output = new StringBuilder();
        output.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        LocalDate compareDate;
        String withoutName;
        int hours;
        int money;
        int wage = 0;
        for (String name : names) {
            for (String shift : data) {
                compareDate = LocalDate.parse(shift.substring(0, DATE_LENGTH), DATE_TIME_FORMATTER);
                if ((fromDate.isBefore(compareDate) || fromDate.isEqual(compareDate))
                        && (toDate.isAfter(compareDate) || toDate.isEqual(compareDate))) {
                    if (shift.contains(name)) {
                        withoutName = shift.substring(name.length() + DATE_WITH_SPACES).trim();
                        hours = Integer.parseInt(withoutName.substring(0, withoutName.indexOf(" ")));
                        money = Integer.parseInt(withoutName
                                .substring(withoutName.indexOf(" ")).trim());
                        wage += hours * money;
                    }
                }
            }
            output.append(System.lineSeparator()).append(name).append(" - ").append(wage);
            wage = 0;
        }
        return output.toString();
    }
}
