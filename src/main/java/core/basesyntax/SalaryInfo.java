package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        List<String> list = new ArrayList<>(new ArrayList<>(Arrays.asList(data)));
        int[] countedSalary = countSalary(names, from, to, list);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Report for period %s - %s", dateFrom, dateTo))
                .append(System.lineSeparator());
        return formReport(names, stringBuilder, countedSalary);

    }

    private int[] countSalary(String[] names, LocalDate from, LocalDate to, List<String> list) {
        int[] countedSalary = new int[names.length];
        LocalDate dateToCompare;
        String[] split;
        for (String s : list) {
            split = s.split("\\s");
            dateToCompare = LocalDate.parse(split[0], DATE_TIME_FORMATTER);
            if (isMatchedDate(from, to, dateToCompare)) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].contains(split[1])) {
                        countedSalary[j] += multiplyHoursOnRate(split);
                    }
                }
            }
        }
        return countedSalary;
    }

    private int multiplyHoursOnRate(String[] strings) {
        return Integer.parseInt(strings[2]) * Integer.parseInt(strings[3]);
    }

    private boolean isMatchedDate(LocalDate from, LocalDate to, LocalDate dateToCompare) {
        return dateToCompare.compareTo(from) >= 0 && dateToCompare.compareTo(to) <= 0;
    }

    private String formReport(String[] names, StringBuilder stringBuilder, int[] countedSalary) {
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i]).append(" - ")
                    .append(countedSalary[i]).append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}

