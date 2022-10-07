package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SEPARATOR_DATA = " ";
    private static final String REPORT_INFO = "Report for period ";
    private static final DateTimeFormatter DATA_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

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

        StringBuilder result = new StringBuilder(REPORT_INFO + dateFrom + " - " + dateTo);
        for (String name : names) {
            int summaName = 0;
            for (String line : data) {
                if (line.length() > 0) {
                    String[] lineArray = line.split(SEPARATOR_DATA, 0);
                    if (lineArray.length == 4) {
                        LocalDate wordData = LocalDate.parse(lineArray[0], DATA_FORMATTER);
                        String wordName = lineArray[1];
                        if ((name.equals(wordName)) && (wordData.compareTo(dateFromFormat) >= 0)
                                && (wordData.compareTo(dateToFormat) <= 0)) {
                            int wordTime = Integer.parseInt(lineArray[2]);
                            int wordRate = Integer.parseInt(lineArray[3]);
                            summaName = summaName + wordTime * wordRate;
                        }
                    }
                }
            }
            result.append("\n").append(name).append(" - ").append(summaName);
        }
        return result.toString();
    }
}
