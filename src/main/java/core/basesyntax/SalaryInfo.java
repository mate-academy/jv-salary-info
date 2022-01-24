package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int SALARY_INDEX = 2;
    private static final int HOURS_INDEX = 3;
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private int salaryAmount;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder()
                .append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            salaryAmount = 0;
            builder.append(System.lineSeparator())
                    .append(name).append(" - ");
            for (int j = 0; j < data.length; j++) {
                String[] dataLine = data[j].split(" ");
                if (name.equals(dataLine[NAME_INDEX])) {
                    if (LocalDate.parse(dataLine[DATE_INDEX], FORMAT)
                            .equals(LocalDate.parse(dateFrom, FORMAT))
                            || LocalDate.parse(dataLine[DATE_INDEX], FORMAT)
                            .equals(LocalDate.parse(dateTo, FORMAT))
                            || (LocalDate.parse(dataLine[DATE_INDEX], FORMAT)
                            .isAfter(LocalDate.parse(dateFrom, FORMAT))
                            && LocalDate.parse(dataLine[DATE_INDEX], FORMAT)
                            .isBefore(LocalDate.parse(dateTo, FORMAT)))) {
                        salaryAmount += Integer.parseInt(dataLine[SALARY_INDEX])
                                * Integer.parseInt(dataLine[HOURS_INDEX]);
                    }
                }
            }
            builder.append(salaryAmount);
        }
        return builder.toString();
    }
}
