package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String PARSING_ERROR = "Wrong input data";
    public static final String REPORT_BEGINNING = "Report for period ";
    public static final String DASH = " - ";
    public static final String SEPARATOR = " ";
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOURS_INDEX = 2;
    public static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(REPORT_BEGINNING).append(dateFrom).append(DASH).append(dateTo);

        LocalDate localDateFrom = getDateFrom(dateFrom);
        LocalDate localDateTo = getDateFrom(dateTo);

        for (String name : names) {
            int salarySum = 0;
            for (String dayData : data) {
                String[] dayDataArray = dayData.split(SEPARATOR);
                LocalDate localDate = getDateFrom(dayDataArray[DATE_INDEX]);
                if (localDateFrom.minusDays(1).isBefore(localDate)
                        && localDateTo.plusDays(1).isAfter(localDate)
                        && name.equals(dayDataArray[NAME_INDEX])) {
                    try {
                        salarySum += Integer.parseInt(dayDataArray[HOURS_INDEX])
                                * Integer.parseInt(dayDataArray[SALARY_INDEX]);
                    } catch (NumberFormatException e) {
                        System.out.println(PARSING_ERROR + SEPARATOR + e);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(DASH)
                    .append(salarySum);
        }
        return stringBuilder.toString();
    }

    private LocalDate getDateFrom(String input) {
        LocalDate date = null;
        try {
            date = LocalDate.parse(input, FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println(PARSING_ERROR + SEPARATOR + e);
        }
        return date;
    }
}
