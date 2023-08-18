package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_FORMATTER = "dd.MM.yyyy";
    private static final String DATA_LINE_SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        System.out.println();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
        String[] returnStrings = new String[names.length];
        StringBuilder returnString = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            int sum = 0;
            for (String datum : data) {
                String[] lineFromData = datum.split(DATA_LINE_SEPARATOR);
                LocalDate currentDate = LocalDate.parse(lineFromData[0], dateTimeFormatter);
                if (names[i].equals(lineFromData[1]) && !currentDate.isAfter(localDateTo)
                        && !currentDate.isBefore(localDateFrom)) {
                    sum += Integer.parseInt(lineFromData[2]) * Integer.parseInt(lineFromData[3]);
                }
            }
            returnString.append(System.lineSeparator()).append(names[i]).append(" - ").append(sum);
        }
        return returnString.toString();
    }
}
