package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate fromDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate toDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] dataInfo = data[i].split(" ");
                LocalDate dataInfoData = LocalDate.parse(dataInfo[0], formatter);
                if (dataInfoData.isAfter(fromDateFrom)
                        && dataInfoData.isBefore(toDateTo.plusDays(1))
                        && dataInfo[1].equals(name)) {
                    salary += Integer.parseInt(dataInfo[2]) * Integer.parseInt(dataInfo[3]);
                }
            }
            result.append(System.lineSeparator());
            result.append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
