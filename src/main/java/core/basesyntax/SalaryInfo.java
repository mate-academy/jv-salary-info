package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String name;
        int[] salary = {0, 0, 0};

        for (int i = 0; i < data.length; i++) {
            StringBuilder builder = new StringBuilder(data[i]);
            String date = builder.substring(0, data[i].indexOf(" "));
            if (LocalDate.parse(date, DATE_FORMAT)
                    .compareTo(LocalDate.parse(dateFrom, DATE_FORMAT)) >= 0
                    && LocalDate.parse(date, DATE_FORMAT)
                    .compareTo(LocalDate.parse(dateTo, DATE_FORMAT)) <= 0) {
                builder.delete(0, builder.indexOf(" ") + 1);
                name = builder.substring(0, builder.indexOf(" "));
                builder.delete(0, builder.indexOf(" ") + 1);
                getSalaryByName(names, name, salary, builder);
            }
        }
        return buildReport(names, salary, dateTo, dateFrom);
    }

    private static void getSalaryByName(String[] names,
                                        String name,
                                        int[] salary,
                                        StringBuilder builder) {
        int workingHours;
        int fee;
        for (int i = 0; i < names.length; i++) {
            if (name.equals(names[i])) {
                workingHours = Integer.parseInt(builder.substring(0, builder.indexOf(" ")));
                builder.delete(0, builder.indexOf(" ") + 1);
                fee = Integer.parseInt(builder.toString());
                salary[i] += fee * workingHours;
            }
        }
    }

    private static String buildReport(String[] names,
                                      int[] salary,
                                      String dateTo,
                                      String dateFrom) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");
        for (int i = 0; i < names.length - 1; i++) {
            builder.append(names[i])
                    .append(" - ")
                    .append(salary[i])
                    .append("\n");
        }
        builder.append(names[names.length - 1])
                .append(" - ")
                .append(salary[salary.length - 1]);
        return builder.toString();
    }
}
