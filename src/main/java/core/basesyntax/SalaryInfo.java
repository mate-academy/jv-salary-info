package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private LocalDate searchFrom;
    private LocalDate searchTo;
    private LocalDate currentDate;
    private int sum;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder dataBuilder = new StringBuilder("Report for period ");
        dataBuilder.append(dateFrom).append(" - ").append(dateTo);
        searchFrom = LocalDate.parse(dateFrom, dateFormatter);
        searchTo = LocalDate.parse(dateTo, dateFormatter);
        for (String name : names) {
            dataBuilder.append(System.lineSeparator()).append(name)
                    .append(" - ");
            for (String inf : data) {
                String[] str = inf.split(" ");
                currentDate = LocalDate.parse(str[0], dateFormatter);
                if (str[1].equals(name) && currentDate.compareTo(searchFrom)
                        * currentDate.compareTo(searchTo) <= 0) {
                    sum += Integer.parseInt(str[2]) * Integer.parseInt(str[3]);
                }
            }
            dataBuilder.append(sum);
            sum = 0;
        }
        return dataBuilder.toString();
    }
}
