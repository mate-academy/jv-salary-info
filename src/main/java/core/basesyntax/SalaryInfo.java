package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final DateTimeFormatter D_F = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromDateType = LocalDate.parse(dateFrom, D_F);
        LocalDate dateToDateType = LocalDate.parse(dateTo, D_F);
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom
                + " - "
                + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String dataItem : data) {
                String[] dataItemInfo = dataItem.split(" ");
                if (name.equals(dataItemInfo[1])
                        && LocalDate.parse(dataItemInfo[0], D_F)
                        .isAfter(dateFromDateType)
                        && LocalDate.parse(dataItemInfo[0], D_F)
                        .isBefore(dateToDateType.plusDays(1))) {
                    salary = salary
                            + Integer.parseInt(dataItemInfo[2])
                            * Integer.parseInt(dataItemInfo[3]);
                }
            }
            salaryInfo.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return salaryInfo.toString();
    }
}
