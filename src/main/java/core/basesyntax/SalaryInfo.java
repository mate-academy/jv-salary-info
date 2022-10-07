package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int[] INDEX = {0, 1, 2, 3};

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom);
        builder.append(" - ");
        builder.append(dateTo);
        int salary = 0;
        LocalDate dateF = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateT = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            for (String strData : data) {
                String[] array = strData.split(" ");
                LocalDate date = LocalDate.parse(array[INDEX[0]], FORMATTER);
                if (name.equals(array[INDEX[1]])
                        && !dateF.isEqual(dateT)
                        && (date.isAfter(dateF)
                        || date.isEqual(dateF))
                        && (date.isBefore(dateT)
                        || date.isEqual(dateT))) {
                    salary += Integer.parseInt(array[INDEX[2]]) * Integer.parseInt(array[INDEX[3]]);
                }
            }
            builder.append(System.lineSeparator());
            builder.append(name);
            builder.append(" - ");
            builder.append(salary);
            salary = 0;
        }
        return builder.toString();
    }
}
