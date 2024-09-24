package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String result = "Report for period " + dateFrom + " - " + dateTo;

        LocalDate localDateFrom = LocalDate.parse(dateFrom,
                                    DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate localDateTo = LocalDate.parse(dateTo,
                                    DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        for (String username: names) {
            int money = 0;
            
            for (String jobData: data) {
                if (jobData.contains(username)) {
                    String[] parts = jobData.split(" ");
                    LocalDate dd = LocalDate.parse(parts[0],
                                        DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    if ((dd.isAfter(localDateFrom) || dd.isEqual(localDateFrom))
                                && (dd.isBefore(localDateTo) || dd.isEqual(localDateTo))) {
                        money += Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
                    }
                }
            }
            result += System.lineSeparator() + username + " - " + money;
        }
        return result;
    }
}
