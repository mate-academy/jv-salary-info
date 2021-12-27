package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {
        String[] sampleNames = {"John", "Andrew", "Kate"};
        String[] nullNames = null;
        String[] scriptArray = {
                "25.04.2019 John 60 50",
                "25.04.2019 Andrew 3 200",
                "25.04.2019 Kate 10 100",

                "26.04.2019 Andrew 3 200",
                "26.04.2019 Kate 9 100",

                "27.04.2019 John 7 100",
                "27.04.2019 Kate 3 80",
                "27.04.2019 Andrew 8 100"
        };
        SalaryInfo salaryInfo = new SalaryInfo();
        System.out.println(salaryInfo.getSalaryInfo(sampleNames, scriptArray,
                "23.04.2019", "29.04.2019"));
        System.out.println(salaryInfo.getSalaryInfo(nullNames, scriptArray,
                "23.04.2019", "29.04.2019"));
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null) {
            throw new IllegalArgumentException("Illegal argument 'names'",
                    new NullPointerException("'names' couldn't be null"));
        }
        if (data == null) {
            throw new IllegalArgumentException("Illegal argument 'names'",
                    new NullPointerException("'data' couldn't be null"));
        }
        if (dateFrom == null) {
            throw new IllegalArgumentException("Illegal argument 'dateFrom'",
                    new NullPointerException("'dateFrom' couldn't be null"));
        }
        if (dateTo == null) {
            throw new IllegalArgumentException("Illegal argument 'dateTo'",
                    new NullPointerException("'dateTo' couldn't be null"));
        }
        LocalDate from;
        LocalDate to;
        try {
            from = parseDate(dateFrom);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Illegal argument 'dateFrom'", e);
        }
        try {
            to = parseDate(dateTo);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Illegal argument 'dateTo'", e);
        }
        int[] salary = new int[names.length];
        for (String record : data) {
            String[] token = record.trim().split(" ");
            if (token.length != 4) {
                continue;
            }
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
