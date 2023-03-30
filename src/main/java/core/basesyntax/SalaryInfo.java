package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] yearnMoney = new int[names.length];
        String[] information;
        LocalDate compareDate;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDateFrom = LocalDate.parse(dateFrom, format);
        LocalDate localDateTo = LocalDate.parse(dateTo, format);

        for (String dataString : data) {
            information = dataString.split(" ");
            compareDate = LocalDate.parse(information[0],format);
            if (compareDates(compareDate, localDateFrom, localDateTo)) {
                for (int i = 0; i < names.length; i++) {
                    if (information[1].equals(names[i])) {
                        yearnMoney[i] += Integer.parseInt(information[2])
                                * Integer.parseInt(information[3]);
                    }
                }
            }
        }
        return getReport(names, yearnMoney, dateFrom, dateTo);
    }

    private boolean compareDates(LocalDate firstDate, LocalDate dateFrom, LocalDate dateTo) {
        if (firstDate == null || dateFrom == null || dateTo == null) {
            return false;
        }
        if (firstDate.equals(dateFrom) || firstDate.isAfter(dateFrom)) {
            if (firstDate.equals(dateTo) || firstDate.isBefore(dateTo)) {
                return true;
            }
        }
        return false;
    }

    private String getReport(String[] names, int[] yearnMoney, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(yearnMoney[i]);
        }
        return builder.toString();
    }
}
