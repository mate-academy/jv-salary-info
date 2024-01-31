package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalaryInfo {
   private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Map<String, Integer> mapOfEmployees = new LinkedHashMap<>();
        for (String name : names) {
            mapOfEmployees.put(name, 0);
        }

        for (String datum : data) {
            String[] seperatedLine = datum.split(" ");
            String checkedData = seperatedLine[0];
            String nameOfEmployee = seperatedLine[1];
            int totalSalary = Integer.parseInt(seperatedLine[2])
                    * Integer.parseInt(seperatedLine[3]);
            if ((LocalDate.parse(checkedData, FORMATTER)
                    .isAfter(LocalDate.parse(dateFrom, FORMATTER))
                    || LocalDate.parse(checkedData, FORMATTER)
                    .isEqual(LocalDate.parse(dateFrom, FORMATTER)))
                    && (LocalDate.parse(checkedData, FORMATTER)
                    .isBefore(LocalDate.parse(dateTo, FORMATTER))
                    || LocalDate.parse(checkedData, FORMATTER)
                    .equals(LocalDate.parse(dateTo, FORMATTER)))) {
                mapOfEmployees.merge(nameOfEmployee, totalSalary, Integer::sum);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> stringIntegerEntry : mapOfEmployees.entrySet()) {
            stringBuilder.append(stringIntegerEntry.getKey()).append(" - ")
                    .append(stringIntegerEntry.getValue()).append("\n");
        }
        return "Report for period " + dateFrom + " - " + dateTo + "\n"
                + stringBuilder.toString().trim();
    }
}
