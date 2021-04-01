package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final String LINE_SEPARATOR = "\n";
    private static final String HEADER_OF_RESPONSE = "Report for period ";
    private static final String DASH = " - ";
    private static final String HOURS_PATTERN = "[\\d\\.]+[\\D]+ ([\\d]+)[\\s\\d]+$";
    private static final String RATE_PATTERN = "[\\d\\.]+[\\D]+ [\\d]+\\s([\\d]+)$";
    private static final String PATTERN_FOR_CHECK_DATE = "([\\d\\.]+)[\\w\\d\\s]+";
    private static final String EXCEPT_LETTERS_PATTERN = "[^A-Za-z]+";

    protected String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null || dateFrom == null || dateTo == null) {
            throw new NullPointerException("Can't read data. Null in the input data");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(HEADER_OF_RESPONSE).append(dateFrom)
                .append(DASH).append(dateTo).append(LINE_SEPARATOR);
        LocalDate localDateFrom = parseStringToLocalDate(dateFrom);
        LocalDate localDateTo = parseStringToLocalDate(dateTo);
        int salarySummary;
        for (int i = 0; i < names.length; i++) {
            salarySummary = 0;
            for (int j = 0; j < data.length; j++) {
                if (checkName(names[i], data[j])
                        && checkDate(localDateFrom, localDateTo.plusDays(1), data[j])) {
                    salarySummary += calculateSalary(data[j]);
                }
            }
            sb.append(names[i]).append(DASH).append(salarySummary).append(LINE_SEPARATOR);
        }
        return sb.toString().trim();
    }

    private LocalDate parseStringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    private boolean checkName(String name, String data) {
        if (name != null && data != null) {
            data = data.replaceAll(EXCEPT_LETTERS_PATTERN, "");
            if (data.equals(name)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDate(LocalDate dateFrom, LocalDate dateTo, String data) {
        if (data != null) {
            data = data.replaceAll(PATTERN_FOR_CHECK_DATE, "$1");
            LocalDate dateFromData = parseStringToLocalDate(data);
            if (dateFromData.isAfter(dateFrom) && dateFromData.isBefore(dateTo)) {
                return true;
            }
        }
        return false;
    }

    private int calculateSalary(String data) {
        int hours = parseDataToIntByPattern(data, HOURS_PATTERN);
        int rate = parseDataToIntByPattern(data, RATE_PATTERN);
        return hours * rate;
    }

    private int parseDataToIntByPattern(String data, String pattern) {
        return Integer.parseInt(data.replaceAll(pattern, "$1"));
    }
}
