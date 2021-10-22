package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = 
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate finishDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder reportBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String name : names) {
            int amontOfMoney = 0;
            for (String datum : data) {
                String[] split = datum.split(" ");
                LocalDate separateDate = LocalDate.parse(split[0], DATE_TIME_FORMATTER);
                if (name.equals(split[1])) {
                    if (separateDate.isAfter(startDate) && separateDate
                            .isBefore(finishDate.plusDays(1))) {
                        amontOfMoney += Integer.parseInt(split[2]) * Integer.parseInt(split[3]);
                    }
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name)
                    .append(" - ").append(amontOfMoney);
        }
        return reportBuilder.toString();
    }
}
