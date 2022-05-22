package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        StringBuilder builder = new StringBuilder();
        int[] empolyerSalaryPeriod = new int[names.length];
        String[] dataUser;
        LocalDate firstDate = LocalDate.parse(dateFrom, formatter);
        LocalDate lastDate = LocalDate.parse(dateTo, formatter);
        LocalDate currentDate;
        for (String dt : data) {
            dataUser = dt.split(" ");
            currentDate = LocalDate.parse(dataUser[0], formatter);
            if (currentDate.isAfter(firstDate) && currentDate.isBefore(lastDate.plusDays(1))) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(dataUser[1])) {
                        empolyerSalaryPeriod[j] += Integer.parseInt(dataUser[2])
                                * Integer.parseInt(dataUser[3]);
                    }
                }
            }
        }
        builder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            builder.append(names[i]).append(" - ")
                    .append(empolyerSalaryPeriod[i]).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
