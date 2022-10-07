package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SEPARATOR_DATA = " ";
    private static final String SEPARATOR_STRING = " - ";
    private static final String REPORT_INFO = "Report for period ";
    private static final String SEPARATOR_LINE = System.lineSeparator();
    private static final DateTimeFormatter DATA_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_MAX = 4;
    private static final int INDEX_DATA = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_TIME = 2;
    private static final int INDEX_RATE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if ((names.length == 0) || (data.length == 0) || (dateFrom.length() == 0)
                || (dateTo.length() == 0)) {
            return "";
        }
        LocalDate dateFromFormat = LocalDate.parse(dateFrom, DATA_FORMATTER);
        LocalDate dateToFormat = LocalDate.parse(dateTo, DATA_FORMATTER);
        if (dateFromFormat.compareTo(dateToFormat) > 0) {
            return "";
        }

        StringBuilder result = new StringBuilder(REPORT_INFO + dateFrom
                + SEPARATOR_STRING + dateTo);
        for (String name : names) {
            int summaName = 0;
            for (String line : data) {
                if (line.length() > 0) {
                    String[] lineArray = line.split(SEPARATOR_DATA, 0);
                    if (lineArray.length == INDEX_MAX) {
                        LocalDate wordData = LocalDate.parse(lineArray[INDEX_DATA], DATA_FORMATTER);
                        String wordName = lineArray[INDEX_NAME];
                        if ((name.equals(wordName)) && (wordData.compareTo(dateFromFormat) >= 0)
                                && (wordData.compareTo(dateToFormat) <= 0)) {
                            int wordTime = Integer.parseInt(lineArray[INDEX_TIME]);
                            int wordRate = Integer.parseInt(lineArray[INDEX_RATE]);
                            summaName = summaName + wordTime * wordRate;
                        }
                    }
                }
            }
            result.append(SEPARATOR_LINE).append(name).append(" - ").append(summaName);
        }
        return result.toString();
    }
}
