package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private String[] splittedDataEntry;
    private LocalDate workingDay;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private int salaryPerWorkingDay;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null || dateFrom == null || dateTo == null || names.length == 0
                || data.length == 0) {
            return "Wrong input";
        }

        this.dateFrom = LocalDate.parse(dateFrom, FORMATTER);
        this.dateTo = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder result = new StringBuilder(
                "Report for period " + this.dateFrom.format(FORMATTER) + " - " + this.dateTo.format(
                FORMATTER) + System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            for (String dataEntry : data) {
                splittedDataEntry = dataEntry.split(" ");
                if (splittedDataEntry.length != 4) {
                    continue;
                }
                workingDay = LocalDate.parse(splittedDataEntry[0], FORMATTER);
                if (workingDay.isBefore(this.dateTo.plusDays(1)) && workingDay.isAfter(
                        this.dateFrom.minusDays(1))
                        && names[i].equals(splittedDataEntry[1])) {
                    salaryPerWorkingDay +=
                        Integer.parseInt(splittedDataEntry[2]) * Integer.parseInt(
                            splittedDataEntry[3]);
                }
            }
            result.append(names[i]).append(" - ").append(salaryPerWorkingDay);
            salaryPerWorkingDay = 0;
            if (i != names.length - 1) {
                result.append(System.lineSeparator());
            }
        }
        return result.toString();
    }
}
