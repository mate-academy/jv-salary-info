package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Map<String, Integer> mapOfEmployees = new LinkedHashMap<>();
        for (String name : names) {
            mapOfEmployees.put(name, 0);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        for (String datum : data) {
            String[] seperatedLine = datum.split(" ");
            String checkedData = seperatedLine[0];
            String nameOfEmployee = seperatedLine[1];
            int totalSalary = Integer.parseInt(seperatedLine[2])
                    * Integer.parseInt(seperatedLine[3]);
            if ((LocalDate.parse(checkedData, formatter)
                    .isAfter(LocalDate.parse(dateFrom, formatter))
                    || LocalDate.parse(checkedData, formatter)
                    .isEqual(LocalDate.parse(dateFrom, formatter)))
                    && (LocalDate.parse(checkedData, formatter)
                    .isBefore(LocalDate.parse(dateTo, formatter))
                    || LocalDate.parse(checkedData, formatter)
                    .equals(LocalDate.parse(dateTo, formatter)))) {
                mapOfEmployees.merge(nameOfEmployee, totalSalary, Integer::sum);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> stringIntegerEntry : mapOfEmployees.entrySet()) {
            stringBuilder.append(stringIntegerEntry.getKey()).append(" - ")
                    .append(stringIntegerEntry.getValue()).append("\r\n");
        }
        return "Report for period " + dateFrom + " - " + dateTo + "\r\n"
                + stringBuilder.toString().trim();
    }
}
