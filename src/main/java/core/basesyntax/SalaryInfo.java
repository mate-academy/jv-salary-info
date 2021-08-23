package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String FORMATTER = "dd.MM.yyyy";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final String DATA_SEPARATOR = " ";
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(FORMATTER);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, dateFormatter);
        int earnedMoney = 0;
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            for (String datum : data) {
                String[] splitArray = datum.split(DATA_SEPARATOR);
                LocalDate arrayDate = LocalDate.parse(splitArray[DATE_INDEX], dateFormatter);
                if (splitArray[NAME_INDEX].equals(name)
                        && (arrayDate.isAfter(dateStart) && !dateEnd.isBefore(arrayDate))) {
                    earnedMoney += Integer.parseInt(splitArray[HOURS_INDEX])
                            * Integer.parseInt(splitArray[RATE_INDEX]);
                }
            }
            reportBuilder.append(name)
                    .append(" - ")
                    .append(earnedMoney)
                    .append(System.lineSeparator());
            earnedMoney = 0;
        }
        return reportBuilder.toString().trim();
    }
}
