package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int RATE_PER_HOURS_INDEX = 3;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int ACTUAL_DATE = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for(String name : names) {
            int salary = 0;
            for(String line : data) {
                String[] lineWords = line.split(" ");
                if(lineWords[EMPLOYEE_NAME_INDEX].equals(name) && isValidDate(dateFrom, dateTo, lineWords[ACTUAL_DATE])) {
                    int hours = Integer.parseInt(lineWords[WORKING_HOURS_INDEX]);
                    int salaryPerHour = Integer.parseInt(lineWords[RATE_PER_HOURS_INDEX]);
                    salary += hours * salaryPerHour;
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return stringBuilder.toString();
    }

    public boolean isValidDate(String dateFrom, String dateTo, String actualDate) {
        LocalDate actualLocalDate = LocalDate.parse(actualDate, DATE_FORMAT);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMAT);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMAT);
        return (actualLocalDate.isEqual(localDateTo)
                || actualLocalDate.isEqual(localDateFrom))
                || (actualLocalDate.isBefore(localDateTo)
                && actualLocalDate.isAfter(localDateFrom));
    }
}
