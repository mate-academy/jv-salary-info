package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate parsedMinDate = LocalDate.parse(dateFrom, format);
        LocalDate parsedMaxDate = LocalDate.parse(dateTo, format);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int totalIncome = 0;
            for (String value : data) {
                String[] valueArr = value.split(" ");
                DateTimeFormatter formatDateOfValue = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate parsedDateOfValue = LocalDate.parse(valueArr[0], formatDateOfValue);
                if (parsedDateOfValue.compareTo(parsedMinDate) >= 0
                        && parsedDateOfValue.compareTo(parsedMaxDate) <= 0
                        && name.equals(valueArr[1])) {
                    totalIncome += Integer.parseInt(valueArr[2]) * Integer.parseInt(valueArr[3]);
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(totalIncome);
        }
        return builder.toString();
    }
}
