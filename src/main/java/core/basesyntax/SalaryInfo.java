package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String EUROPEAN_FORMAT = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final DateTimeFormatter eurFormat = DateTimeFormatter
                .ofPattern(EUROPEAN_FORMAT);
        String[] compareData;
        int[] countSalaries = new int[names.length];
        LocalDate currentDate;
        LocalDate fromDate = LocalDate.parse(dateFrom, eurFormat);
        LocalDate toDate = LocalDate.parse(dateTo, eurFormat);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());
        for (String splitedDate : data) {
            compareData = splitedDate.split(" ");
            currentDate = LocalDate.parse(compareData[0], eurFormat);
            for (int j = 0; j < names.length; j++) {
                if (fromDate.compareTo(currentDate) <= 0
                        && toDate.compareTo(currentDate) >= 0
                        && names[j].equals(compareData[1])) {
                    countSalaries[j] += Integer.parseInt(compareData[2])
                            * Integer.parseInt(compareData[3]);
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i]).append(" - ").append(countSalaries[i])
                    .append(System.lineSeparator());
        }
        int indexDel = stringBuilder.lastIndexOf(System.lineSeparator());
        stringBuilder.delete(indexDel, stringBuilder.length());
        return stringBuilder.toString();
    }
}
