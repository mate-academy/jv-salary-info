package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final int DATA_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOUR_INDEX = 2;
    public static final int PAY_INDEX = 3;
    public static final int EXTRA_DAY = 1;
    public static final String DASH = "-";
    public static final String DASH_WITH_SPAСES = " - ";
    public static final String DOT = ".";
    public static final String SEPARATOR = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateF = LocalDate.parse(dateFrom.replace(DOT, DASH), FORMATTER);
        LocalDate dateT = LocalDate.parse(dateTo.replace(DOT, DASH), FORMATTER);
        int totalSalary;
        String[] array;
        String result = "";
        for (String name : names) {
            totalSalary = 0;
            for (String datum : data) {
                array = datum.split(" ");
                if (name.equals(array[NAME_INDEX])
                        && (LocalDate.parse(array[DATA_INDEX].replace(DOT, DASH), FORMATTER)
                        .isAfter(dateF)
                        && LocalDate.parse(array[DATA_INDEX].replace(DOT, DASH), FORMATTER)
                        .isBefore(dateT.plusDays(EXTRA_DAY)))) {
                    totalSalary += Integer.parseInt(array[HOUR_INDEX])
                            * Integer.parseInt(array[PAY_INDEX]);
                }
            }
            StringBuilder builder = new StringBuilder();
            builder
                    .append(name)
                    .append(DASH_WITH_SPAСES)
                    .append(totalSalary);
            result += builder.toString()
                    + SEPARATOR;
        }
        return "Report for period " + dateFrom + DASH_WITH_SPAСES + dateTo
                + SEPARATOR
                + result.trim();
    }
}
