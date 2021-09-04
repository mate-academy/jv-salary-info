package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromDateType = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToDateType = LocalDate.parse(dateTo, formatter);
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom
                + " - "
                + dateTo);

        // filter data array and calculate employer salary
        for (String namesItem : names) {
            int salary = 0;
            for (String dataItem : data) {
                String[] dataItemInfo = dataItem.split(" ");
                if (namesItem.equals(dataItemInfo[1])
                        && LocalDate.parse(dataItemInfo[0],
                        formatter).isAfter(dateFromDateType)
                        && LocalDate.parse(dataItemInfo[0],
                        formatter).isBefore(dateToDateType.plusDays(1))) {

                    salary = salary
                            + Integer.parseInt(dataItemInfo[2])
                            * Integer.parseInt(dataItemInfo[3]);
                }
            }
            salaryInfo.append(System.lineSeparator())
                    .append(namesItem)
                    .append(" - ")
                    .append(salary);
        }
        return salaryInfo.toString();
    }
}
