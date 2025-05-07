package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String DIVIDER = " - ";
    public static final String SPLITTER = " ";
    public static final int DATE_POSITION = 0;
    public static final int NAME_POSITION = 1;
    public static final int HOURS_POSITION = 2;
    public static final int SALARY_POSITION = 3;

    private LocalDate searchFrom;
    private LocalDate searchTo;
    private LocalDate currentDate;
    private int sum;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder dataBuilder = new StringBuilder("Report for period ");
        dataBuilder.append(dateFrom).append(DIVIDER).append(dateTo);
        searchFrom = LocalDate.parse(dateFrom, dateFormatter);
        searchTo = LocalDate.parse(dateTo, dateFormatter);
        for (String name : names) {
            dataBuilder.append(System.lineSeparator()).append(name)
                    .append(DIVIDER);
            for (String inf : data) {
                String[] str = inf.split(SPLITTER);
                currentDate = LocalDate.parse(str[DATE_POSITION], dateFormatter);
                if (str[NAME_POSITION].equals(name) && currentDate.compareTo(searchFrom)
                        * currentDate.compareTo(searchTo) <= 0) {
                    sum += Integer.parseInt(str[HOURS_POSITION])
                            * Integer.parseInt(str[SALARY_POSITION]);
                }
            }
            dataBuilder.append(sum);
            sum = 0;
        }
        return dataBuilder.toString();
    }
}
