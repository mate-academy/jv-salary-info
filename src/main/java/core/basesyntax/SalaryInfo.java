package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private DateClass dateClass = new DateClass();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaryInfo = new int[names.length];

        LocalDate localDateFrom = dateClass.getLocalDate(dateFrom);
        LocalDate localDateTo = dateClass.getLocalDate(dateTo);

        for (String dataLine : data) {
            String[] splitData = dataLine.split(" ");
            LocalDate date = dateClass.getLocalDate(splitData[0]);

            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(splitData[1]) && (date.isAfter(localDateFrom.minusDays(1))
                        && date.isBefore(localDateTo.plusDays(1)))) {
                    salaryInfo[i] += Integer.parseInt(splitData[2].trim())
                            * Integer.parseInt(splitData[3].trim());
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        int namesLength = names.length;
        for (int i = 0; i < namesLength; i++) {
            stringBuilder.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(salaryInfo[i]);
        }

        return stringBuilder.toString();
    }
}
