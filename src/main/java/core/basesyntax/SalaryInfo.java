package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter SIMPLE_DATE_FORMAT = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int WORKING_DATE_INDEX = 0;
    private static final int CURRENT_NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final String SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder resultData = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom,SIMPLE_DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo,SIMPLE_DATE_FORMAT);
        for (String name : names) {
            int totalSalary = 0;
            for (String entry : data) {
                String[] parts = entry.split(SEPARATOR);
                String workingDate = parts[WORKING_DATE_INDEX];
                String currentName = parts[CURRENT_NAME_INDEX];
                int workingHours = Integer.parseInt(parts[WORKING_HOURS_INDEX]);
                int incomePerHour = Integer.parseInt(parts[INCOME_PER_HOUR_INDEX]);
                LocalDate date = LocalDate.parse(workingDate,SIMPLE_DATE_FORMAT);
                if (name.equals(currentName) && (date.isAfter(fromDate) && date.isBefore(toDate)
                        || date.equals(fromDate) || date.equals(toDate))) {
                    totalSalary += workingHours * incomePerHour;
                }
            }
            resultData.append(System.lineSeparator())
                    .append(name).append(" - ").append(totalSalary);
        }
        return resultData.toString();
    }
}
