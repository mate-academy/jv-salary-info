package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private StringBuilder stringBuilder;
    private DateTimeFormatter formatter;
    private UtilsData utilsData;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        stringBuilder = new StringBuilder();
        formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        utilsData = new UtilsData();

        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);

        int[] salaryEmployees = new int[names.length];

        String name;
        LocalDate localDateData;

        for (int i = 0; i < names.length; i++) {
            name = names[i];
            for (String datum : data) {
                if (!name.equals(utilsData.getNameFromDate(datum))) {
                    continue;
                }
                localDateData = utilsData.getDateFromDate(datum, formatter);
                if (localDateData.isAfter(localDateFrom.minusDays(1))
                        && localDateData.isBefore(localDateTo.plusDays(1))) {
                    salaryEmployees[i] +=
                        utilsData.getWorkingHourFromDate(datum) * utilsData
                            .getIncomePerHourFromDate(datum);
                }
            }
        }

        stringBuilder.append("Report for period ")
            .append(localDateFrom.format(formatter))
            .append(" - ").append(localDateTo.format(formatter)).append("\n");

        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i])
                .append(" - ")
                .append(salaryEmployees[i]).append("\n");
        }

        return stringBuilder.toString().trim();
    }
}
