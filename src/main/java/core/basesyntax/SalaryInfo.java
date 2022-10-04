package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String HYPHEN = " - ";
    private static final String WHITE_SPACE = " ";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int THERO_INDEX = 0;
    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;
    private static final int THIRD_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(concatTopPart(dateFrom, dateTo));
        for (String name : names) {
            int countMoney = 0;
            for (String info : data) {
                String[] parsedInfo = info.split(WHITE_SPACE);
                LocalDate infoDate = LocalDate.parse(parsedInfo[THERO_INDEX], FORMATTER);
                if (parsedInfo[FIRST_INDEX].equals(name)
                        && compareData(parsedDateFrom, parsedDateTo, infoDate)) {
                    countMoney += Integer.parseInt(parsedInfo[SECOND_INDEX])
                            * Integer.parseInt(parsedInfo[THIRD_INDEX]);
                }
            }
            stringBuilder.append(concatBottomPart(name, countMoney));
        }
        return stringBuilder.toString().trim();
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
}
