package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final String DATE_FORMAT = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate startDate = LocalDate.parse(dateFrom, formatter).minusDays(1);
        LocalDate finishDate = LocalDate.parse(dateTo, formatter).plusDays(1);
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salaryTotal = 0;
            for (String datum : data) {
                int salaryPerDay = Integer.parseInt(datum.split(" ")[2])
                                 * Integer.parseInt(datum.split(" ")[3]);
                String nameTemp = datum.split(" ")[1];
                LocalDate dataTemp = LocalDate.parse(datum.split(" ")[0], formatter);
                if (dataTemp.isAfter(startDate) && dataTemp.isBefore(finishDate)
                        && name.equals(nameTemp)) {
                    salaryTotal += salaryPerDay;
                }
            }
            builder.append(System.lineSeparator());
            builder.append(name);
            builder.append(" - ");
            builder.append(salaryTotal);
        }
        return builder.toString();
    }
}
