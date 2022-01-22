package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int SALARY_INDEX = 2;
    private static final int HOURS_INDEX = 3;
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder()
                .append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            int salaryAmount = 0;
            builder.append(System.lineSeparator()).append(names[i]).append(" - ");
            for (int j = 0; j < data.length; j++) {
                if (names[i].equals(data[j].split(" ")[NAME_INDEX])) {
                    if (LocalDate.parse(data[j].split(" ")[DATE_INDEX], FORMAT)
                            .equals(LocalDate.parse(dateFrom, FORMAT))
                            || LocalDate.parse(data[j].split(" ")[DATE_INDEX], FORMAT)
                            .equals(LocalDate.parse(dateTo, FORMAT))
                            || (LocalDate.parse(data[j].split(" ")[DATE_INDEX], FORMAT)
                            .isAfter(LocalDate.parse(dateFrom, FORMAT))
                            && LocalDate.parse(data[j].split(" ")[DATE_INDEX], FORMAT)
                            .isBefore(LocalDate.parse(dateTo, FORMAT)))) {
                        salaryAmount += Integer.parseInt(data[j].split(" ")[SALARY_INDEX])
                                * Integer.parseInt(data[j].split(" ")[HOURS_INDEX]);
                    }
                }
            }
            builder.append(salaryAmount);
        }
        return builder.toString();
    }
}
