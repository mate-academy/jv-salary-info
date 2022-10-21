package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_STRING_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] tmpStrArr;
        int len = names.length;
        int[] tmpIntArr = new int[len];
        StringBuilder stringBuilder =
                new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        LocalDate convertDate;
        LocalDate convertDateFrom;
        LocalDate convertDateTo;
        convertDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        convertDateTo = LocalDate.parse(dateTo, FORMATTER);

        int index = 0;
        for (String name: names) {
            int salary = 0;
            for (String record: data) {
                tmpStrArr = record.split(" ");
                if (tmpStrArr[NAME_INDEX].equals(name)) {
                    convertDate = LocalDate.parse(tmpStrArr[DATE_STRING_INDEX], FORMATTER);
                    if (convertDate.isAfter(convertDateFrom) && convertDate.isBefore(convertDateTo)
                            || convertDate.isEqual(convertDateFrom)
                            || convertDate.isEqual(convertDateTo)) {
                        salary += Integer.parseInt(tmpStrArr[HOURS_INDEX])
                                * Integer.parseInt(tmpStrArr[INCOME_PER_HOUR_INDEX]);
                    }
                }
            }
            stringBuilder
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return stringBuilder.toString();
    }
}
