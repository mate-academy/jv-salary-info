package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromDateType = LocalDate.parse(dateFrom, SalaryInfo.DATE_FORMAT);
        LocalDate dateToDateType = LocalDate.parse(dateTo, SalaryInfo.DATE_FORMAT);
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom
                + " - "
                + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String dataItem : data) {
                String[] dataItemInfo = dataItem.split(" ");
                if (name.equals(dataItemInfo[1])
                        && LocalDate.parse(dataItemInfo[0], SalaryInfo.DATE_FORMAT)
                        .isAfter(dateFromDateType)
                        && LocalDate.parse(dataItemInfo[0], SalaryInfo.DATE_FORMAT)
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
