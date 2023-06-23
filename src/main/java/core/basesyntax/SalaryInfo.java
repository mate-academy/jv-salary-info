package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKED_HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from;
        LocalDate to;
        try {
            from = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
            to = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Can not parse entry date", e);
        }
        List<String> dataList = new ArrayList<>(new ArrayList<>(Arrays.asList(data)));
        int[] countedSalary = countSalary(names, from, to, dataList);
        StringBuilder stringBuilder = new StringBuilder();
        appendReportHeader(dateFrom, dateTo, stringBuilder);
        return appendSalaryInfo(names, stringBuilder, countedSalary);
    }

    private void appendReportHeader(String dateFrom, String dateTo, StringBuilder stringBuilder) {
        stringBuilder.append(String.format("Report for period %s - %s", dateFrom, dateTo))
                .append(System.lineSeparator());
    }

    private int[] countSalary(String[] names, LocalDate from, LocalDate to, List<String> list) {
        int[] countedSalary = new int[names.length];
        LocalDate dateToCompare;
        String[] dataEntry;
        for (String temp : list) {
            dataEntry = temp.split("\\s");
            try {
                dateToCompare = LocalDate.parse(dataEntry[DATE_INDEX], DATE_TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                throw new RuntimeException("Can not parse entry date", e);
            }
            if (isDateInRange(from, to, dateToCompare)) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].contains(dataEntry[NAME_INDEX])) {
                        countedSalary[j] += multiplyHoursOnRate(dataEntry);
                    }
                }
            }
        }
        return countedSalary;
    }

    private int multiplyHoursOnRate(String[] strings) {
        int hours = Integer.parseInt(strings[WORKED_HOURS_INDEX]);
        int rate = Integer.parseInt(strings[RATE_INDEX]);
        return hours * rate;
    }

    private boolean isDateInRange(LocalDate from, LocalDate to, LocalDate dateToCompare) {
        return dateToCompare.compareTo(from) >= 0 && dateToCompare.compareTo(to) <= 0;
    }

    private String appendSalaryInfo(String[] names, StringBuilder stringBuilder,
                                    int[] countedSalary) {
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i]).append(" - ")
                    .append(countedSalary[i]).append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}

