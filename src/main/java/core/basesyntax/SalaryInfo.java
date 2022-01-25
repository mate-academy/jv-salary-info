package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final String SEPARATOR = " ";
    public static final int DATA = 0;
    public static final int NAME = 1;
    public static final int HOUR = 2;
    public static final int P_HOUR = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        LocalDate dateF = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateT = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        int salary;
        for (String name: names) {
            salary = 0;
            for (String info: data) {
                String[] elements = info.split(SEPARATOR);
                if (name.equals(elements[NAME])) {
                    LocalDate hoursPerDay = LocalDate.parse(elements[DATA], FORMATTER);
                    if (dateF.isBefore(hoursPerDay) && dateT.isAfter(hoursPerDay)
                            || dateF.isEqual(hoursPerDay) || dateT.equals(hoursPerDay)) {
                        salary += Integer.parseInt(elements[HOUR])
                                * Integer.parseInt(elements[P_HOUR]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }
}
