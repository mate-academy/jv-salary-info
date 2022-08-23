package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATA = 0;
    public static final int NAME = 1;
    public static final int HOUR = 2;
    public static final int  SALARY_PER_HOUR = 3;
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);


        for(String name: names) {
            int salary = 0;
            for(String lineOfData: data) {
                String[] line = lineOfData.split(" ");
                if (name.equals(line[NAME])) {
                    LocalDate date = LocalDate.parse(line[DATA], FORMATTER);
                    if (date.compareTo(from) >= 0 && date.compareTo(to) <=0) {
                        salary += (Integer.parseInt(line[HOUR])* Integer.parseInt(line[SALARY_PER_HOUR]));
                        builder.append(System.lineSeparator())
                                .append(name)
                                .append(" - ")
                                .append(salary);
                    }
                }

            }
        }
        return builder.toString();
    }
}
