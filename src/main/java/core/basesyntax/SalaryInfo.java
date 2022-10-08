package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int MULTIPLIER = 2;
    private static final int AMOUNT = 3;

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
                LocalDate date = LocalDate.parse(array[DATE], FORMATTER);
                if (name.equals(array[NAME])
                        && !dateF.isEqual(dateT)
                        && (date.isAfter(dateF)
                        || date.isEqual(dateF))
                        && (date.isBefore(dateT)
                        || date.isEqual(dateT))) {
                    salary += Integer.parseInt(array[MULTIPLIER]) * Integer.parseInt(array[AMOUNT]);
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
