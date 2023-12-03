package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromNow = LocalDate.parse(dateFrom, df);
        LocalDate dateToNow = LocalDate.parse(dateTo, df);
        int[] countSalary = new int [names.length];
        for (String dataEntry : data) {
            String[] currentStringInfo = dataEntry.split(" ");
            int hourlyRate = Integer.parseInt(currentStringInfo[3]);
            int hoursWorked = Integer.parseInt(currentStringInfo[2]);
            for (int j = 0; j < names.length; j++) {
                LocalDate today = LocalDate.parse(currentStringInfo[0], df);
                if (names[j].equals(currentStringInfo[1]) && !dateFromNow.isAfter(today)
                        && !(dateToNow.isBefore(today))) {
                    countSalary[j] += hourlyRate * hoursWorked;
                }
            }
        }
        StringBuilder salaryBuilder = new StringBuilder();
        salaryBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int k = 0; k < names.length; k++) {
            salaryBuilder.append(System.lineSeparator())
                    .append(names[k])
                    .append(" - ")
                    .append(countSalary[k]);
        }
        return salaryBuilder.toString();
    }
}
