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
        for (String name : names) {
            int salary = 0;
            for (String dataInfo : data) {
                String[] splitedInfo = dataInfo.split(" ");
                if (name.equals(dataInfo.split(" ")[1])
                        && !LocalDate.parse(splitedInfo[0],
                        DATE_TIME_FORMATTER).isBefore(beginDate)
                        && !LocalDate.parse(splitedInfo[0],
                        DATE_TIME_FORMATTER).isAfter(endDate)) {
                    int workingHours;
                    int paymentPerHour;
                    workingHours = Integer.parseInt(splitedInfo[2]);
                    paymentPerHour = Integer.parseInt(splitedInfo[3]);
                    salary += workingHours * paymentPerHour;
                }
            }
            reportString.append(name).append(" - ").append(salary).append("\n");
        }
        return reportString.toString().trim();
    }
}
