package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String FORMAT_DATE = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        LocalDate firstDate = LocalDate.parse(dateFrom, formatter);
        LocalDate lastDate = LocalDate.parse(dateTo, formatter);

        int salaryNameOne = 0;
        int salaryNameTwo = 0;
        int salaryNameTree = 0;

        for (String day : data) {
            String[] a = day.split(" ");
            LocalDate currentDate = LocalDate.parse(a[0], formatter);
            if ((currentDate.compareTo(firstDate) >= 0) && (currentDate.compareTo(lastDate) <= 0)) {
                if (a[1].equals(names[0])) {
                    salaryNameOne = salaryNameOne + (Integer.parseInt(a[2])
                            * Integer.parseInt(a[3]));
                } else if (a[1].equals(names[1])) {
                    salaryNameTwo = salaryNameTwo + (Integer.parseInt(a[2])
                            * Integer.parseInt(a[3]));
                } else {
                    salaryNameTree = salaryNameTree + (Integer.parseInt(a[2])
                            * Integer.parseInt(a[3]));
                }
            }
        }

        StringBuilder sb = new StringBuilder("Report for period ");

        sb.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator())
                .append(names[0]).append(" - ").append(salaryNameOne).append(System.lineSeparator())
                .append(names[1]).append(" - ").append(salaryNameTwo).append(System.lineSeparator())
                .append(names[2]).append(" - ").append(salaryNameTree);
        return sb.toString();
    }
}
