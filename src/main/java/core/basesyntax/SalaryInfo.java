package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder rezult = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
        rezult.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate startDate = LocalDate.parse(dateFrom.replaceAll(" ", ""), formatter);
        LocalDate endDate = LocalDate.parse(dateTo.replaceAll(" ", ""), formatter);
        int[] salary = new int[names.length];
        for (String line : data) {
            LocalDate currentDate = LocalDate.parse(line.substring(0, 10), formatter);
            if (currentDate.compareTo(startDate) >= 0 && currentDate.compareTo(endDate) <= 0) {
                String[] info = line.split(" ");
                for (int i = 0; i < names.length; i++) {
                    if (info[1].equals(names[i])) {
                        salary[i] += Integer.parseInt(info[2])
                                * Integer.parseInt(info[3]);
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            rezult.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary[i]);
        }
        return rezult.toString();
    }
}
