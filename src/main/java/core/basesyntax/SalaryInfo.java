package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        int [] salary = new int[names.length];
        for (String line : data) {
            String [] temporaryData = line.split(" ");
            for (int i = 0; i < names.length; i++) {
                if (isIn(LocalDate.parse(temporaryData[0], formatter),fromDate,toDate)
                        && temporaryData[1].equals(names[i])) {
                    salary[i] += Integer.parseInt(temporaryData[2])
                            * Integer.parseInt(temporaryData[3]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < salary.length; i++) {
            if (i == salary.length - 1) {
                sb.append(names[i]).append(" - ").append(salary[i]);
            } else {
                sb.append(names[i]).append(" - ").append(salary[i]).append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }

    private boolean isIn(LocalDate date, LocalDate from, LocalDate to) {
        return ((date.isAfter(from) || date.isEqual(from))
                & (date.isBefore(to) || date.isEqual(to)));
    }
}
