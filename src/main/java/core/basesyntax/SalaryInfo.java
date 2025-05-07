package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String HYPHEN = " - ";
    private static final String WHITE_SPACE = " ";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int PARSE_DATA_INDEX_0 = 0;
    private static final int PARSE_DATA_INDEX_1 = 1;
    private static final int PARSE_DATA_INDEX_2 = 2;
    private static final int PARSE_DATA_INDEX_3 = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        return concatTopPart(dateFrom, dateTo)
                + personsSalaryInfo(names, data, dateFrom, dateTo).trim();
    }

    public boolean compareData(LocalDate dateFrom, LocalDate dateTo, LocalDate comparedDate) {
        return (dateFrom.isBefore(comparedDate) || dateFrom.equals(comparedDate))
                && (dateTo.isAfter(comparedDate) || dateTo.equals(comparedDate));
    }

    public String concatTopPart(String first, String second) {
        return "Report for period " + first + HYPHEN + second + LINE_SEPARATOR;
    }

    public String concatBottomPart(String name, int count) {
        return name + HYPHEN + count + LINE_SEPARATOR;
    }

    public String personsSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        for (String name : names) {
            int countMoney = 0;
            for (String info : data) {
                String[] parsedInfo = info.split(WHITE_SPACE);
                LocalDate infoDate = LocalDate.parse(parsedInfo[PARSE_DATA_INDEX_0],
                        DATE_FORMATTER);
                if (parsedInfo[PARSE_DATA_INDEX_1].equals(name)
                        && compareData(parsedDateFrom, parsedDateTo, infoDate)) {
                    countMoney += Integer.parseInt(parsedInfo[PARSE_DATA_INDEX_2])
                            * Integer.parseInt(parsedInfo[PARSE_DATA_INDEX_3]);
                }
            }
            stringBuilder.append(concatBottomPart(name, countMoney));
        }
        return stringBuilder.toString();
    }
}
