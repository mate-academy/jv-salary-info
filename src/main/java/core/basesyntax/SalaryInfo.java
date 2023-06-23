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
        LocalDate from = parseDate(dateFrom);
        LocalDate to = parseDate(dateTo);
        int[] countedSalaryForEachEmployee = countSalary(names, from, to, data);
        StringBuilder reportHeader = appendReportHeader(dateFrom, dateTo);
        return createCompletedReport(names, reportHeader, countedSalaryForEachEmployee);
    }

    private StringBuilder appendReportHeader(String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Report for period %s - %s", dateFrom, dateTo))
                .append(System.lineSeparator());
        return stringBuilder;
    }

    private int[] countSalary(String[] names, LocalDate from, LocalDate to, String[] data) {
        List<String> dataList = new ArrayList<>(new ArrayList<>(Arrays.asList(data)));
        int[] countedSalary = new int[names.length];
        LocalDate dateToCompare;
        String[] dataEntry;
        for (String temp : dataList) {
            dataEntry = temp.split("\\s");
            dateToCompare = parseDate(dataEntry[DATE_INDEX]);
            if (isDateInRange(from, to, dateToCompare)) {
                countSalaryDueToName(names, countedSalary, dataEntry);
            }
        }
        return countedSalary;
    }

    private void countSalaryDueToName(String[] names, int[] countedSalary, String[] dataEntry) {
        for (int j = 0; j < names.length; j++) {
            if (names[j].contains(dataEntry[NAME_INDEX])) {
                countedSalary[j] += multiplyHoursOnRate(dataEntry);
            }
        }
    }

    private LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Can not parse entry date", e);
        }
    }

    private int multiplyHoursOnRate(String[] strings) {
        int hours = Integer.parseInt(strings[WORKED_HOURS_INDEX]);
        int rate = Integer.parseInt(strings[RATE_INDEX]);
        return hours * rate;
    }

    private boolean isDateInRange(LocalDate from, LocalDate to, LocalDate dateToCompare) {
        return dateToCompare.compareTo(from) >= 0 && dateToCompare.compareTo(to) <= 0;
    }

    private String createCompletedReport(String[] names, StringBuilder stringBuilder,
                                         int[] countedSalary) {
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i]).append(" - ")
                    .append(countedSalary[i]).append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}

