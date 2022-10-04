package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String DIVIDER = " - ";
    public static final String INTRODUCTORY_PHRASE = "Report for period ";
    public static final String SPLITTER = " ";

    private LocalDate searchFrom;
    private LocalDate searchTo;
    private LocalDate currentDate;
    private int sum;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder dataBuilder = new StringBuilder(INTRODUCTORY_PHRASE);
        dataBuilder.append(dateFrom).append(DIVIDER).append(dateTo);
        searchFrom = LocalDate.parse(dateFrom, dateFormatter);
        searchTo = LocalDate.parse(dateTo, dateFormatter);
        for (String name : names) {
            dataBuilder.append(System.lineSeparator()).append(name)
                    .append(DIVIDER);
            for (String inf : data) {
                String[] str = inf.split(SPLITTER);
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
