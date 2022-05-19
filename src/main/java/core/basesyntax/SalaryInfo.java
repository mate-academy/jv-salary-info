package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate dateDateTo = LocalDate.parse(dateTo, formatter);
        int[] salaries = new int[names.length];
        StringBuilder output = new StringBuilder("Report for period ");
        output.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            for (String d : data) {
                boolean dateCheck =
                        (dateDateFrom.isBefore(LocalDate.parse(d.substring(0, 10), formatter))
                        && dateDateTo.isAfter(LocalDate.parse(d.substring(0, 10), formatter))
                        || dateDateFrom.isEqual(LocalDate.parse(d.substring(0, 10), formatter))
                        || dateDateTo.isEqual(LocalDate.parse(d.substring(0, 10), formatter)));
                if (d.substring(11).contains(names[i]) && dateCheck) {
                    System.out.println(Integer.parseInt(d.substring(12 + names[i].length(),
                            d.indexOf(" ", 12 + names[i].length())))
                            * Integer.parseInt(d.substring(d.indexOf(" ", 12
                            + names[i].length()) + 1)));
                    salaries[i] += Integer.parseInt(d.substring(12 + names[i].length(),
                            d.indexOf(" ", 12 + names[i].length())))
                            * Integer.parseInt(d.substring(d.indexOf(" ", 12
                            + names[i].length()) + 1));
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            output.append(names[i]).append(" - ").append(salaries[i]);
            if (i < names.length - 1) {
                output.append(System.lineSeparator());
            }
        }
        return output.toString();
    }
}
