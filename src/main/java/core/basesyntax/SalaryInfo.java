package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, formatter);

        StringBuilder reportForPeriod = new StringBuilder();

        int[] salary = new int[names.length];
        String[][] dataSplit = new String[data.length][4];

        for (int i = 0; i < data.length; i++) {
            dataSplit[i] = data[i].split(" ");
            for (int j = 0; j < names.length; j++) {
                if ((LocalDate.parse(dataSplit[i][0], formatter).isAfter(dateFromFormatted)
                        || LocalDate.parse(dataSplit[i][0], formatter).isEqual(dateFromFormatted))
                        && (LocalDate.parse(dataSplit[i][0], formatter).isBefore(dateToFormatted)
                        || LocalDate.parse(dataSplit[i][0], formatter).isEqual(dateToFormatted))
                        && names[j].equals(dataSplit[i][1])) {
                    salary[j] += Integer.parseInt(dataSplit[i][2])
                            * Integer.parseInt(dataSplit[i][3]);
                }
            }
        }

        return reportForPeriod.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator())
                .append(names[0]).append(" - ").append(salary[0])
                .append(System.lineSeparator()).append(names[1]).append(" - ").append(salary[1])
                .append(System.lineSeparator()).append(names[2]).append(" - ").append(salary[2])
                .toString();
    }
}
