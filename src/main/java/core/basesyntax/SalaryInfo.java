package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);

        for (String name : names) {
            int totalEarned = 0;
            for (String dataName : data) {
                String[] parts = dataName.split(" ");

                if (!name.equals(parts[1])) {
                    continue;
                }
                LocalDate recordDate = LocalDate.parse(parts[0], formatter);

                if (!recordDate.isBefore(fromDate) && !recordDate.isAfter(toDate)) {
                    int hours = Integer.parseInt(parts[2]);
                    int rate = Integer.parseInt(parts[3]);
                    totalEarned += hours * rate;
                }
            }
            salaryInfo.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalEarned);
        }
        return salaryInfo.toString();
    }
}
