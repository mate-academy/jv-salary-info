package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final int POSITION_DATE = 0;
    private static final int POSITION_NAME = 1;
    private static final int POSITION_HOUR = 2;
    private static final int POSITION_PAY = 3;
    private StringBuilder stringBuilder;
    private String[] elementFromData;
    private LocalDate localDateData;
    private int salary;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Report for period %s - %s", dateFrom, dateTo));

        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        for (String name : names) {
            salary = 0;
            for (String datum : data) {
                elementFromData = datum.split(" ");
                if (name.equals(elementFromData[POSITION_NAME])) {
                    localDateData = LocalDate.parse(elementFromData[POSITION_DATE], FORMATTER);
                    if (localDateData.isAfter(localDateFrom.minusDays(1))
                            && localDateData.isBefore(localDateTo.plusDays(1))) {
                        salary += Integer.parseInt(elementFromData[POSITION_HOUR])
                            * Integer.parseInt(elementFromData[POSITION_PAY]);
                    }
                }
            }
            stringBuilder.append(String.format("\n%s - %d", name, salary));
        }

        return stringBuilder.toString();
    }
}
