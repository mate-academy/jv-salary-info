package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder rezult = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
        rezult.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate StartDate = LocalDate.parse(dateFrom.replaceAll(" ", ""), formatter);
        LocalDate EndDate = LocalDate.parse(dateTo.replaceAll(" ", ""), formatter);
        int[] salary = new int[names.length];
        Arrays.fill(salary, 0);
        for (String el: data) {
            LocalDate thisDate = LocalDate.parse(el.substring(0, 10), formatter);
            if (thisDate.compareTo(StartDate) >= 0 && thisDate.compareTo(EndDate) <= 0) {
                String temp = el.substring(11);
                String tname = temp.substring(0, temp.indexOf(' '));
                for (int i = 0; i < names.length; i++) {
                    if (tname.equals(names[i])) {
                        temp = temp.substring(tname.length() + 1);
                        salary[i] += Integer.parseInt(temp.substring(0,temp.indexOf(' ')))
                                * Integer.parseInt(temp.substring(temp.indexOf(' ') + 1));
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
