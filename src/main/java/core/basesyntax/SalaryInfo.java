package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
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
                LocalDate workDate = LocalDate.parse(records[0], DATE_FORMAT);
                if (name.equals(records[1])
                        && (workDate.isAfter(fromDate) || workDate.equals(fromDate))
                        && (workDate.isBefore(toDate) || workDate.equals(toDate))) {
                    salary += Integer.parseInt(records[2])
                            * Integer.parseInt(records[3]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name)
                    .append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}

