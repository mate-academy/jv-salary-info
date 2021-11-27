package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter).minusDays(1);
        LocalDate finishDate = LocalDate.parse(dateTo, formatter).plusDays(1);
        int[]salary = new int[names.length];
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            for (String datum : data) {
                int days = Integer.parseInt(datum.split(" ")[2]);
                int price = Integer.parseInt(datum.split(" ")[3]);
                String nameTemp = datum.substring(11, 11 + names[i].length());
                LocalDate dataTemp = LocalDate.parse(datum.split(" ")[0], formatter);
                if (dataTemp.isAfter(startDate) && dataTemp.isBefore(finishDate)
                                                && names[i].equals(nameTemp)) {
                    salary[i] += days * price;
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator());
            builder.append(names[i]);
            builder.append(" - ");
            builder.append(salary[i]);
        }
        return builder.toString();
    }
}
