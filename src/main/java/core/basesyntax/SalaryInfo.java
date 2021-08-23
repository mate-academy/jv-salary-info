package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int DATE_POSITION = 0;
    public static final int NAME_POSITION = 1;
    public static final int POSITION_OF_HOURS = 2;
    public static final int POSITION_OF_INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parsedMinDate = LocalDate.parse(dateFrom, DATE);
        LocalDate parsedMaxDate = LocalDate.parse(dateTo, DATE);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] dataOfDay = data[j].split(" ");
                LocalDate localDate = LocalDate.parse(dataOfDay[0], DATE);
                if (names[i].equals(dataOfDay[1])
                        && !localDate.isBefore(parsedMinDate)
                        && !localDate.isAfter(parsedMaxDate)) {
                    salary += Integer.parseInt(dataOfDay[3]) * Integer.parseInt(dataOfDay[2]);
                }
            }
            report.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary);
        }
        return report.toString();
    }
}
