package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateEnd = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String dataArray : data) {
                String[] dataForDay = dataArray.split(" ");
                if (name.equals(dataForDay[1])) {
                    LocalDate dateForCalculating = LocalDate.parse(dataForDay[0], FORMATTER);
                    if ((dateForCalculating.isAfter(dateStart)
                            || dateForCalculating.isEqual(dateStart))
                            && (dateForCalculating.isBefore(dateEnd)
                            || dateForCalculating.isEqual(dateEnd))) {
                        salary += Integer.parseInt(dataForDay[2]) * Integer.parseInt(dataForDay[3]);
                    }
                }
            }
            result.append("\n").append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
