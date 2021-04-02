package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate beginDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder reportString = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + "\n");
        int salary = 0;
        int workingHours;
        int paymentPerHour;
        for (String name : names) {
            for (String dataInfo : data) {
                if (name.equals(dataInfo.split(" ")[1])
                        && !LocalDate.parse(dataInfo.split(" ")[0],
                        DATE_TIME_FORMATTER).isBefore(beginDate)
                        && !LocalDate.parse(dataInfo.split(" ")[0],
                        DATE_TIME_FORMATTER).isAfter(endDate)) {
                    workingHours = Integer.parseInt(dataInfo.split(" ")[2]);
                    paymentPerHour = Integer.parseInt(dataInfo.split(" ")[3]);
                    salary += workingHours * paymentPerHour;
                }
            }
            reportString.append(name).append(" - ").append(salary).append("\n");
            salary = 0;
        }
        return reportString.toString().trim();
    }
}
