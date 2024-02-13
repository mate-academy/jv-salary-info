package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String DASH = " - ";
    private static final int INDEX_ONE = 1;
    private static final int INDEX_THREE = 3;
    private static final int INDEX_TWO = 2;
    private static final String REPORT_TITLE = "Report for period ";
    private static final String SPLIT_REGEXP = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMATE);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMATE);
        StringBuilder resultStrBuilder = new StringBuilder();
        resultStrBuilder.append(REPORT_TITLE)
                .append(dateFrom)
                .append(DASH)
                .append(dateTo)
                .append(System.lineSeparator());

        generateData(names, data, from, to, resultStrBuilder);

        return resultStrBuilder.toString().trim();
    }

    private void generateData(String[] names,
                              String[] data,
                              LocalDate from,
                              LocalDate to,
                              StringBuilder resultStrBuilder) {
        for (String name : names) {
            int salary = 0;

            for (String entryData : data) {
                String[] splitData = entryData.split(SPLIT_REGEXP);

                if (isDateInRange(LocalDate.parse(splitData[0], DATE_FORMATE),
                        from,
                        to)
                        && name.equals(splitData[INDEX_ONE])) {
                    salary += Integer.parseInt(splitData[INDEX_TWO])
                            * Integer.parseInt(splitData[INDEX_THREE]);
                }
            }

            resultStrBuilder.append(name)
                    .append(DASH)
                    .append(salary)
                    .append(System.lineSeparator());
        }
    }

    private boolean isDateInRange(LocalDate entryDate, LocalDate dateFrom, LocalDate dateTo) {
        return (!entryDate.isBefore(dateFrom) && !entryDate.isAfter(dateTo));
    }
}
