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
            for (int j = 0; j < data.length; j++) {
                String[] accounts = data[j].split(REGEX);
                LocalDate workDate = LocalDate.parse(accounts[0], DATE_FORMAT);
                if (name.equals(accounts[1])
                        && (workDate.isAfter(fromDate) || workDate.equals(fromDate))
                        && (workDate.isBefore(toDate) || workDate.equals(toDate))) {
                    salary += Integer.parseInt(accounts[2])
                            * Integer.parseInt(accounts[3]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name)
                    .append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}

