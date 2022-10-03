package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public static final String PATTERN = "dd.MM.yyyy";
    public static final String PARSING_ERROR = "Wrong input data";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        LocalDate localDateFrom = getDateFrom(dateFrom);
        LocalDate localDateTo = getDateFrom(dateTo);

        for (String name : names) {
            int salarySum = 0;
            for (String dayData : data) {
                String[] dayDataArray = dayData.split(" ");
                LocalDate localDate = getDateFrom(dayDataArray[0]);
                if (localDateFrom.minusDays(1).isBefore(localDate)
                        && localDateTo.plusDays(1).isAfter(localDate)
                        && name.equals(dayDataArray[1])) {
                    try {
                        salarySum += Integer.parseInt(dayDataArray[2])
                                * Integer.parseInt(dayDataArray[3]);
                    } catch (NumberFormatException e) {
                        System.out.println(PARSING_ERROR + " " + e);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salarySum);
        }
        return stringBuilder.toString();
    }

    private LocalDate getDateFrom(String input) {
        LocalDate date = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
            date = LocalDate.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println(PARSING_ERROR + " " + e);
        }
        return date;
    }
}
