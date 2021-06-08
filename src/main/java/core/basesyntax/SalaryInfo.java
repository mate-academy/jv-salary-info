package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SPACE = " ";
    private static final String DASH = " - ";
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate sdateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate sdateTo = LocalDate.parse(dateTo, formatter);
        int[] totalSalaryForPerson = new int[names.length];
        for (String inputData : data) {
            String[] info = inputData.split(SPACE);
            LocalDate inputDateString = LocalDate.parse(info[0], formatter);
            if ((inputDateString.isBefore(sdateTo) && inputDateString.isAfter(sdateFrom))
                    || inputDateString.isEqual(sdateTo) || inputDateString.isEqual(sdateFrom)) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(info[1])) {
                        totalSalaryForPerson[j] +=
                                Integer.parseInt(info[2]) * Integer.parseInt(info[3]);
                        break;
                    }
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append("\n");
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i]).append(" - ")
                    .append(totalSalaryForPerson[i]).append("\n");
        }
        return stringBuilder.toString().trim();
    }
}
