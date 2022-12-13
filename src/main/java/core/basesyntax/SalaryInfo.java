package core.basesyntax;

import java.time.LocalDate;
import java.util.Arrays;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.of(Integer.parseInt(dateFrom.split("[.]")[2]),
                Integer.parseInt(dateFrom.split("[.]")[1]),Integer.parseInt(dateFrom
                        .split("[.]")[0]));
        LocalDate endDate = LocalDate.of(Integer.parseInt(dateTo.split("[.]")[2]),
                Integer.parseInt(dateTo.split("[.]")[1]),Integer.parseInt(dateTo
                        .split("[.]")[0]) + 1);
        int[] summarySalaryInfo = new int[names.length];
        for (int i = 0; i < data.length; i++) {
            String[] timeless = data[i].split(" ");
            int indexOf = Arrays.asList(names).indexOf(timeless[1]);
            LocalDate dateNow = LocalDate.of(Integer.parseInt(timeless[0].split("[.]")[2]),
                    Integer.parseInt(timeless[0].split("[.]")[1]),Integer.parseInt(timeless[0]
                            .split("[.]")[0]));
            if (dateNow.isAfter(startDate) && dateNow.isBefore(endDate)) {
                summarySalaryInfo[indexOf] = summarySalaryInfo[indexOf]
                        + (Integer.parseInt(timeless[2]) * Integer.parseInt(timeless[3]));
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(summarySalaryInfo[i]);
        }
        return builder.toString();
    }
}
