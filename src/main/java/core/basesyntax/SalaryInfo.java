package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        int[] arrayResultInt = new int[names.length];
        List<String> list = new ArrayList<>(List.of(names));
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        for (String s : data) {
            String[] split = s.split(" ");
            LocalDate localDate = LocalDate.parse(split[0], formatter);
            if ((localDate.isAfter(startDate) || localDate.isEqual(startDate))
                    && (localDate.isBefore(endDate) || localDate.isEqual(endDate))) {
                int salarySum = Integer.parseInt(split[2]) * Integer.parseInt(split[3]);
                arrayResultInt[list.indexOf(split[1])] += salarySum;
            }
        }
        result.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < list.size(); i++) {
            result.append(list.get(i)).append(" - ")
                    .append(arrayResultInt[i]).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
