package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateTimeFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String WHITE_SPACE = " ";
    private static final String REPORT = "Report for period ";
    private static final String DASH = " - ";
    private static final int DATE_IN_DATA = 0;
    private static final int NAME_IN_DATA = 1;
    private static final int HOURS_IN_DATA = 2;
    private static final int INCOME_IN_DATA = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate lastDate = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder builder = new StringBuilder(REPORT);
        builder.append(dateFrom).append(DASH).append(dateTo);
        for (String name : names) {
            builder.append(System.lineSeparator()).append(name).append(DASH);
            int salaryForEmployee = 0;
            for (String dataValue : data) {
                String[] splitedData = dataValue.split(WHITE_SPACE);
                LocalDate exactDate = LocalDate.parse(splitedData[DATE_IN_DATA], dateTimeFormatter);
                if (exactDate.isAfter(firstDate.minusDays(1))
                        && exactDate.isBefore(lastDate.plusDays(1))
                        && name.equals(splitedData[NAME_IN_DATA])) {
                    salaryForEmployee += (Integer.parseInt(splitedData[HOURS_IN_DATA])
                            * Integer.parseInt(splitedData[INCOME_IN_DATA]));
                }
            }
            builder.append(salaryForEmployee);
        }
        return builder.toString();
    }
}
