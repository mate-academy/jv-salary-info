package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String CHARACTER = "-";

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        int[] eachSalary = new int[names.length];
        LocalDate firstDate = LocalDate.parse(dateFrom.substring(6, 10) + CHARACTER
                + dateFrom.substring(3, 5) + CHARACTER + dateFrom.substring(0, 2));
        LocalDate secondDate = LocalDate.parse(dateTo.substring(6, 10) + CHARACTER
                + dateTo.substring(3, 5) + CHARACTER + dateTo.substring(0, 2));

        for (String datum : data) {
            builder.append(datum);
            LocalDate date = LocalDate.parse(builder.substring(6, 10) + CHARACTER
                    + builder.substring(3, 5) + CHARACTER + builder.substring(0, 2));

            if ((date.isAfter(firstDate) || date.isEqual(firstDate)) && (date.isBefore(secondDate)
                    || date.isEqual(secondDate))) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(datum.split(" ")[1])) {
                        eachSalary[j] += Integer.parseInt(datum.split(" ")[2])
                                * Integer.parseInt(datum.split(" ")[3]);
                    }
                }
            }
            builder.delete(0, builder.length());
        }

        builder.append("Report for period ").append(FORMATTER.format(firstDate)).append(" - ")
                .append(FORMATTER.format(secondDate));

        for (int i = 0; i < names.length; i++) {
            builder.append("\n").append(names[i]).append(" - ").append(eachSalary[i]);
        }

        return builder.toString();
    }
}
