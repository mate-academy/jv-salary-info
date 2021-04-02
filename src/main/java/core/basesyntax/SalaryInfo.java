package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDay = LocalDate.parse(dateFrom, FORMAT_DATE);
        LocalDate lastDay = LocalDate.parse(dateTo, FORMAT_DATE);
        StringBuilder totalResult = new StringBuilder();
        totalResult.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo).append("\n");
        for (int i = 0, namesLength = names.length; i < namesLength; i++) {
            String name = names[i];
            int allSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String info = data[j];
                if (info.contains(name)) {
                    String[] splitDataArray = info.split(" ");
                    LocalDate currentDay = LocalDate.parse(splitDataArray[0], FORMAT_DATE);
                    if (currentDay.isAfter(firstDay) && currentDay.isBefore(lastDay.plusDays(1))) {
                        allSalary += Integer.parseInt(splitDataArray[2])
                                * Integer.parseInt(splitDataArray[3]);
                    }
                }
            }
            totalResult.append(name).append(" - ").append(allSalary).append("\n");
        }
        return totalResult.toString().trim();
    }
}
