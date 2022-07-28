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
        String[][] accounts = new String[data.length][4];
        for (int i = 0; i < data.length; i++) {
            accounts[i] = data[i].split(REGEX);
        }
        for (String name : names) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                LocalDate workDate = LocalDate.parse(accounts[j][0], DATE_FORMAT);
                if (name.equals(accounts[j][1])
                        && (workDate.isAfter(fromDate) || workDate.equals(fromDate))
                        && (workDate.isBefore(toDate) || workDate.equals(toDate))) {
                    salary += Integer.parseInt(accounts[j][2])
                            * Integer.parseInt(accounts[j][3]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ")
                    .append(salary);
        }
        return stringBuilder.toString();
    }
}

