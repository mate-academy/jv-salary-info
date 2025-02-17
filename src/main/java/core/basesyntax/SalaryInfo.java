package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOURS_POSITION = 2;
    private static final int INCOME_POSITION = 3;
    private static final DateTimeFormatter Date = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, Date);
        LocalDate to = LocalDate.parse(dateTo, Date);
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String dataInfo : data) {
                String[] info = dataInfo.split(" ");
                if (name.equals(info[NAME_POSITION])) {
                    LocalDate recordDate = LocalDate.parse(info[DATE_POSITION], Date);
                    if (!recordDate.isBefore(from) && !recordDate.isAfter(to)) {
                        salary += Integer.parseInt(info[INCOME_POSITION])
                                * Integer.parseInt(info[HOURS_POSITION]);
                    }
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return builder.toString();
    }
}
