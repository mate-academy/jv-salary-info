package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int AMOUNT_PER_HOUR = 3;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private LocalDate currentDate;
    private int salary;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        String[] personsInfo;
        this.dateFrom = LocalDate.parse(dateFrom, formatter);
        this.dateTo = LocalDate.parse(dateTo, formatter);
        builder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            salary = 0;
            for (int j = 0; j < data.length; j++) {
                personsInfo = data[j].split(" ");
                currentDate = LocalDate.parse(personsInfo[DATE_INDEX], formatter);
                if (!currentDate.isBefore(this.dateFrom) && !currentDate.isAfter(this.dateTo)
                        && personsInfo[NAME_INDEX].equals(names[i])) {
                    salary = salary + (Integer.parseInt(personsInfo[HOURS_INDEX])
                            * Integer.parseInt(personsInfo[AMOUNT_PER_HOUR]));
                }
            }
            builder.append(names[i]).append(" - ").append(salary).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
