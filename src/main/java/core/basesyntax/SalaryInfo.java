package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private UtilsData utilsData = new UtilsData();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();

        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        int[] salaryEmployees = new int[names.length];

        String name;
        LocalDate localDateData;

        for (int i = 0; i < names.length; i++) {
            name = names[i];
            for (String datum : data) {
                if (!name.equals(utilsData.getNameFromDate(datum))) {
                    continue;
                }
                localDateData = utilsData.getDateFromDate(datum, FORMATTER);
                if (localDateData.isAfter(localDateFrom.minusDays(1))
                        && localDateData.isBefore(localDateTo.plusDays(1))) {
                    salaryEmployees[i] +=
                        utilsData.getWorkingHourFromDate(datum) * utilsData
                            .getIncomePerHourFromDate(datum);
                }
            }
        }

        stringBuilder.append("Report for period ")
            .append(localDateFrom.format(FORMATTER))
            .append(" - ").append(localDateTo.format(FORMATTER)).append("\n");

        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i])
                .append(" - ")
                .append(salaryEmployees[i]).append("\n");
        }

        return stringBuilder.toString().trim();
    }
}
