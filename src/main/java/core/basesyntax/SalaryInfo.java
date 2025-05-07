package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int HOURS_INDEX = 2;
    private static final int PAY_FOR_HOUR_INDEX = 3;
    private static final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate parseDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate parseDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String element : data) {
                String[] splitData = element.split(" ");
                LocalDate date = LocalDate.parse(splitData[DATE_INDEX],
                        dateTimeFormatter);
                if (element.contains(name)) {
                    if (date.compareTo(parseDateFrom) >= 0
                            && parseDateTo.compareTo(date) >= 0) {
                        salary += Integer.parseInt(splitData[HOURS_INDEX])
                                * Integer.parseInt(splitData[PAY_FOR_HOUR_INDEX]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }
}


