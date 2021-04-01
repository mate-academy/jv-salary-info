package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        int[] salary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(name)) {
                    String[] dateAndDay = data[j].split(" ");
                    LocalDate dateTime = LocalDate.parse(dateAndDay[0], FORMATTER);

                    if (localDateFrom.compareTo(dateTime) <= 0
                            && localDateTo.compareTo(dateTime) >= 0) {
                        salary[i] += Integer.parseInt(dateAndDay[2])
                                * Integer.parseInt(dateAndDay[3]);
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder("Report for period " + dateFrom
                                + " - " + dateTo + "\n");

        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ").append(salary[i]).append("\n");
        }
        return report.toString().strip();
    }
}
