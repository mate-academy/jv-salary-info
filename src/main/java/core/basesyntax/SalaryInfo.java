package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter EUROPEAN_FORMAT = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int CURRENT_DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] compareData;
        int[] countSalaries = new int[names.length];
        LocalDate currentDate;
        LocalDate fromDate = LocalDate.parse(dateFrom, EUROPEAN_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, EUROPEAN_FORMAT);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());
        for (String splitedDate : data) {
            compareData = splitedDate.split(" ");
            currentDate = LocalDate.parse(compareData[CURRENT_DATE], EUROPEAN_FORMAT);
            for (int j = 0; j < names.length; j++) {
                if (fromDate.compareTo(currentDate) <= 0
                        && toDate.compareTo(currentDate) >= 0
                        && names[j].equals(compareData[NAME])) {
                    countSalaries[j] += Integer.parseInt(compareData[HOURS])
                            * Integer.parseInt(compareData[INCOME_PER_HOUR]);
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i]).append(" - ").append(countSalaries[i])
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
