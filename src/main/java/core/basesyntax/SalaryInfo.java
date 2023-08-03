package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String COMA = ",";
    private static final String HYPHEN = " - ";
    private static final String GAP = " ";
    private static final int ZERO_INDEX = 0;
    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;
    private static final int THIRD_INDEX = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder periodData = new StringBuilder();
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        for (String day : data) {
            String[] string = day.split(GAP);
            LocalDate date = LocalDate.parse(string[ZERO_INDEX], formatter);
            if ((date.isAfter(fromDate) || fromDate.isEqual(date))
                    && (date.isBefore(toDate) || date.isEqual(toDate))) {
                periodData.append(day).append(COMA);
            }
        }
        String[] periodDataArr = periodData.toString().split(COMA);
        int[] salsry = new int[names.length];
        StringBuilder builder = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(HYPHEN)
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            if (periodDataArr.length > 1) {
                for (String personDataStr : periodDataArr) {
                    String[] dataPerson = personDataStr.split(GAP);
                    if (dataPerson[FIRST_INDEX].equals(names[i])) {
                        salsry[i] += Integer.parseInt(dataPerson[SECOND_INDEX])
                                * Integer.parseInt(dataPerson[THIRD_INDEX]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(names[i])
                    .append(HYPHEN).append(salsry[i]);
        }
        return builder.toString();
    }
}
