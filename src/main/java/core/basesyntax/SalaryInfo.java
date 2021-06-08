package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final String DELIMITER = " ";
    public static final int DATA_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOURS_INDEX = 2;
    public static final int SALARY_PER_HOUR_INDEX = 3;
    public static final String DATE_FORMATTER = "dd.MM.yyyy";
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_FORMATTER);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromWithFormatter = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate dateToWithFormatter = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder salaryInfo = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        String[] splitLine;
        for (String name : names) {
            int earnedPerName = 0;
            for (String element : data) {
                splitLine = element.split(DELIMITER);
                if (name.equals(splitLine[NAME_INDEX])
                        && isDateInPeriod(splitLine[DATA_INDEX],
                        dateFromWithFormatter, dateToWithFormatter)) {
                    earnedPerName += Integer.parseInt(splitLine[HOURS_INDEX])
                            * Integer.parseInt(splitLine[SALARY_PER_HOUR_INDEX]);
                }
            }
            salaryInfo = salaryInfo.append("\n")
                    .append(name)
                    .append(" - ")
                    .append(earnedPerName);
        }
        return salaryInfo.toString();
    }

    private boolean isDateInPeriod(String date,
                                   LocalDate dateFromWithFormatter,
                                   LocalDate dateToWithFormatter) {
        LocalDate localDate = LocalDate.parse(date, DATE_TIME_FORMATTER);
        return (localDate.isAfter(dateFromWithFormatter)
                || localDate.equals(dateToWithFormatter))
                && localDate.isBefore(dateToWithFormatter)
                || localDate.equals(dateToWithFormatter);
    }

}
