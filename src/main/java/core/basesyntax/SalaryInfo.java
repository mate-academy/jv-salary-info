package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, formater);
        LocalDate toDate = LocalDate.parse(dateTo, formater);
        String result = "Report for period " + dateFrom + " - " + dateTo + "\r\n";
        StringBuilder stringBuilder = new StringBuilder();
        int[] salaryArr = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i])) {
                    String [] lineOfData = data[j].split(" ");
                    LocalDate day = LocalDate.parse(lineOfData[0],formater);
                    if (!day.isBefore(fromDate) && !day.isAfter(toDate)) {
                        String[] dataLineArr = data[j].split(" ");
                        int hours = Integer.parseInt(dataLineArr[2]);
                        int money = Integer.parseInt(dataLineArr[3]);
                        salaryArr[i] = salaryArr[i] + hours * money;
                    }
                }
            }
            stringBuilder.append(names[i]).append(" - ").append(salaryArr[i]).append("\r\n");
        }
        result = result + stringBuilder.toString();
        return result.substring(0, result.length() - 2);
    }
}
