package core.basesyntax;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        Map<String, Integer> salary = new HashMap<>();
        for (String name : names) {
            salary.put(name, 0);
        }
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        for (String s : data) {
            String[] split = s.split(" ");
            LocalDate localDate = LocalDate.parse(split[0], formatter);
            if ((localDate.isAfter(startDate) || localDate.isEqual(startDate))
                    && (localDate.isBefore(endDate) || localDate.isEqual(endDate))) {
                Integer salarySum = Integer.parseInt(split[2]) * Integer.parseInt(split[3]);
                salary.compute(split[1], (k, v) -> v + salarySum);

            }
        }
        result.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            result.append(name).append(" - ").append(salary.get(name)).append(System.lineSeparator());
        }
        return result.toString().trim();
    }

}
