package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] names1 = new String[names.length];
        for (int i = 0; i < names1.length; i++) {
            names1[i] = new StringBuilder().append(names[i]).append(" - ").append("0").toString();
        }

        for (int i = 0; i < data.length; i++) {
            LocalDate dateData = LocalDate
                    .parse(data[i].substring(0, data[i].indexOf(' ')), FORMATTER);
            if (dateData.isAfter(LocalDate.parse(dateFrom, FORMATTER).minusDays(1))
                    && dateData.isBefore(LocalDate.parse(dateTo, FORMATTER).plusDays(1))) {
                for (int j = 0; j < names1.length; j++) {
                    String[] generalInfo = data[i].split(" ");
                    if (names1[j].substring(0, names1[j].indexOf(' ')).equals(generalInfo[1])) {
                        names1[j] = new StringBuilder(names1[j])
                        .replace(names1[j].split(" ")[0].length() + 3, names1[j].length(),
                        Integer.toString(Integer.parseInt(generalInfo[2])
                        * Integer.parseInt(generalInfo[3])
                        + Integer.parseInt(names1[j].split(" ")[2]))).toString();
                    }
                }
            }
        }
        StringBuilder finalInfo = new StringBuilder();
        finalInfo.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        for (String totalInfo:names1) {
            finalInfo.append(totalInfo).append(System.lineSeparator());
        }
        return finalInfo.toString().trim();
    }
}

