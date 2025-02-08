package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salary = new int[names.length];
        final String dateFormat = "dd.MM.yyyy";
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern(dateFormat));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern(dateFormat));
        StringBuilder builder = new StringBuilder("Report for period ");

        for (int i = 0; i < names.length; i++) {
            for (String datum : data) {
                String[] separateData = datum.split(" ");
                LocalDate currentDay = LocalDate.parse(separateData[0],
                        DateTimeFormatter.ofPattern(dateFormat));

                if (names[i].equals(separateData[1])
                        && (!currentDay.isBefore(from) && !currentDay.isAfter(to))) {
                    salary[i] += Integer.parseInt(separateData[2])
                            * Integer.parseInt(separateData[3]);
                }
            }
        }

        builder.append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            builder.append("\n").append(names[i]).append(" - ").append(salary[i]);
        }
        return builder.toString();
    }
}
