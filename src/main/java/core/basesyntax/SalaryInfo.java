package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append("\n");

        LocalDate startDate = getDateFromString(dateFrom);
        LocalDate endDate = getDateFromString(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                if (line.contains(name)) {
                    String[] lineContent = line.split(" ");
                    if (getDateFromString(lineContent[0]).isAfter(startDate)
                            && getDateFromString(lineContent[0]).isBefore(endDate.plusDays(1))) {
                        salary += Integer.parseInt(lineContent[2])
                                * Integer.parseInt(lineContent[3]);
                    }

                }
            }
            builder.append(name).append(" - ").append(salary).append("\n");
        }
        return builder.toString().trim();
    }

    public LocalDate getDateFromString(String date) {
        return LocalDate.parse(date, FORMAT);
    }
}
