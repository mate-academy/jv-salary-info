package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER_DATE);
        LocalDate dateEnd = LocalDate.parse(dateTo, FORMATTER_DATE);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int sumSalary = 0;
            for (String date : data) {
                String[] parameter = date.split(" ");
                LocalDate parameterDate = LocalDate.parse(parameter[0], FORMATTER_DATE);
                if (name.equals(parameter[1])
                        && (parameterDate.isAfter(dateStart) || parameterDate.isEqual(dateStart))
                        && (parameterDate.isBefore(dateEnd) || parameterDate.isEqual(dateEnd))) {
                    sumSalary += Integer.parseInt(parameter[2]) * Integer.parseInt(parameter[3]);
                }
            }
            result.append("\n").append(name).append(" - ").append(sumSalary);
        }
        return result.toString();
    }
}
