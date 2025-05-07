package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int DAY_COLUMN = 0;
    public static final int WORKING_HOUR_COLUMN = 2;
    public static final int INCOME_PER_HOUR_COLUMN = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDay = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate lastDay = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i])) {
                    String[] dataEntry = data[j].split(" ");
                    LocalDate workingDay = LocalDate.parse(dataEntry[DAY_COLUMN], FORMATTER);
                    if ((workingDay.isAfter(firstDay) && workingDay.isBefore(lastDay))
                            || workingDay.isEqual(firstDay) || workingDay.isEqual(lastDay)) {
                        salary += Integer.parseInt(dataEntry[WORKING_HOUR_COLUMN])
                                * Integer.parseInt(dataEntry[INCOME_PER_HOUR_COLUMN]);
                    }
                }
            }
            stringBuilder.append(names[i])
                    .append(" - ")
                    .append(salary)
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
