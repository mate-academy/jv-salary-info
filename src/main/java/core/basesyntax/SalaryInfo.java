package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int SALARY = 3;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String REGEX = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);
        for (String name : names) {
            int salary = 0;
            for (String account : data) {
                String[] records = account.split(REGEX);
                LocalDate workDate = LocalDate.parse(records[DATE], DATE_FORMAT);
                if (name.equals(records[NAME])
                        && (workDate.isAfter(fromDate) || workDate.equals(fromDate))
                        && (workDate.isBefore(toDate) || workDate.equals(toDate))) {
                    salary += Integer.parseInt(records[HOURS])
                            * Integer.parseInt(records[SALARY]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name)
                    .append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}

