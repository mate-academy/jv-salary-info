package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int NAME_EMPLOYER = 1;
    private static final int HOURS_DAY = 2;
    private static final int SALARY_HOUR = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        StringBuilder information = new StringBuilder();
        information.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] info = data[i].split(" ");
                LocalDate dateDay = LocalDate.parse(info[0], formatter);
                if (name.equals(info[NAME_EMPLOYER])
                        && !dateDay.isBefore(from)
                        && !dateDay.isAfter(to)) {
                    salary += Integer.parseInt(info[SALARY_HOUR])
                            * Integer.parseInt(info[HOURS_DAY]);
                }
            }
            information.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return information.toString();
    }
}
