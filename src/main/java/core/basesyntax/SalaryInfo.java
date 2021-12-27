package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null) {
            throw new NullPointerException("names is null");
        }
        if (data == null) {
            throw new NullPointerException("data is null");
        }
        if (dateFrom == null) {
            throw new NullPointerException("dateFrom is null");
        }
        if (dateTo == null) {
            throw new NullPointerException("dateTo is null");
        }
        LocalDate from;
        LocalDate to;
        try {
            from = parseDate(dateFrom);
            to = parseDate(dateTo);
        } catch (DateTimeParseException e) {
            throw e;
        }
        int[] salary = new int[names.length];
        for (String record : data) {
            String[] token = record.trim().split(" ");
            if (token.length != 4) {
                continue;
            }
            /*
                sample: 05.04.2019 Andrew 3 200
                token[0] - date;
                token[1] - name;
                token[2] - working hour;
                token[3] - income per hour.
            */
            LocalDate localDate;
            String name;
            int workHour;
            int income;
            try {
                localDate = parseDate(token[0]);
            } catch (DateTimeParseException e) {
                continue;
            }
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
            try {
                workHour = Integer.parseInt(token[2]);
            } catch (NumberFormatException e) {
                continue;
            }
            if (workHour < 0) {
                continue;
            }
            try {
                income = Integer.parseInt(token[3]);
            } catch (NumberFormatException e) {
                continue;
            }
            if (income < 0) {
                continue;
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
