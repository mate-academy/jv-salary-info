package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final String HYPHEN_WITH_SPACES = " - ";
    private static final String REPORT_START = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter).minusDays(1);
        LocalDate toDate = LocalDate.parse(dateTo, formatter).plusDays(1);
        LocalDate dateFromData;
        int[] salaries = new int[names.length];
        String[] splitData;
        StringBuilder stringBuilder = new StringBuilder()
                .append(REPORT_START).append(dateFrom)
                .append(HYPHEN_WITH_SPACES).append(dateTo);

        for (int i = 0; i < names.length; i++) {

            for (String lineOfData : data) {
                splitData = lineOfData.split(" ");
                dateFromData = LocalDate.parse(splitData[0], formatter);

                if (names[i].equals(splitData[1])
                        && dateFromData.isAfter(fromDate)
                        && dateFromData.isBefore(toDate)) {
                    salaries[i] += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(names[i]).append(HYPHEN_WITH_SPACES).append(salaries[i]);
        }
        return stringBuilder.toString();
    }
}
