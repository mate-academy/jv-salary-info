package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATA_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append("\n");
        LocalDate dateFromTime = LocalDate.parse(dateFrom, DATA_TIME_FORMATTER);
        LocalDate dateToTime = LocalDate.parse(dateTo, DATA_TIME_FORMATTER);
        String[] employee;
        LocalDate nowData;
        int getMoney;
        for (int i = 0; i < names.length; i++) {
            getMoney = 0;
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i])) {
                    employee = data[j].split(" ");
                    nowData = LocalDate.parse(employee[0], DATA_TIME_FORMATTER);
                    if (nowData.isAfter(dateFromTime) && nowData.isBefore(dateToTime)
                            || nowData.equals(dateToTime)) {
                        getMoney += Integer.parseInt(employee[2])
                                * Integer.parseInt(employee[3]);

                    }
                }
            }

            builder.append(names[i]).append(" - ").append(getMoney).append("\n");
        }
        return String.valueOf(builder).trim();
    }
}
