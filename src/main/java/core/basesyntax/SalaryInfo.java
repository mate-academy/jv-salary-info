package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from;
        LocalDate to;
        from = parseDate(dateFrom);
        to = parseDate(dateTo);
        int[] salary = new int[names.length];
        for (String record : data) {
            String[] token = record.trim().split(" ");
            if (token.length != 4) {
                throw new IllegalArgumentException("Illegal argument 'data'. Couldn't"
                        + "parse '" + record + "'");
            }
            LocalDate localDate;
            String name;
            int workHour;
            int income;
            localDate = parseDate(token[0]);
            if ((localDate.compareTo(from) < 0) || (localDate.compareTo(to) > 0)) {
                continue;
            }
            name = token[1];
            int namesIndex = -1;
            for (int i = 0; i < names.length; i++) {
                if (name.equals(names[i])) {
                    namesIndex = i;
                    break;
                }
            }
            if (namesIndex < 0) {
                continue;
            }
            workHour = Integer.parseInt(token[2]);
            if (workHour < 0) {
                throw new IllegalArgumentException("Illegal argument 'data'. "
                        + "Working hours couldn't by negative:"
                        + System.lineSeparator()
                        + "'" + record + "'");
            }
            income = Integer.parseInt(token[3]);
            if (income < 0) {
                throw new IllegalArgumentException("Illegal argument 'data'. "
                        + "Income per hour couldn't by negative:"
                        + System.lineSeparator()
                        + "'" + record + "'");
            }
            salary[namesIndex] += income * workHour;
        }
        return salaryInfoHelper(names, salary, dateFrom, dateTo);
    }

    private String salaryInfoHelper(String[] names, int[] salary, String dateFrom, String dateTo) {
        StringBuilder retVal = new StringBuilder("Report for period " + dateFrom.trim() + " - "
                + dateTo.trim());
        for (int i = 0; i < names.length; i++) {
            retVal.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary[i]);
        }
        return retVal.toString();
    }

    private LocalDate parseDate(String text) {
        return LocalDate.parse(text.trim(), formatter);
    }
}
