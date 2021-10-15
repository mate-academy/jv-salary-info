package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder stringBuilder = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo);
        stringBuilder.append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            int periodSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] datas = data[j].split(" ");
                LocalDate dateFromDatas = LocalDate.parse(datas[0],formatter);
                if (dateFromDatas.isBefore(localDateTo.plusDays(1))
                        && (dateFromDatas.isAfter(localDateFrom))
                        && datas[1].equals(names[i])) {
                    int personSalary = Integer.parseInt(datas[2]) * Integer.parseInt(datas[3]);
                    periodSalary += personSalary;
                }
            }
            stringBuilder.append(names[i]).append(" - ").append(periodSalary)
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
