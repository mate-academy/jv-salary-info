package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private DateClass dateClass = new DateClass();
    private StringBuilder stringBuilder = new StringBuilder();

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
                    try {
                        salaryInfo[i] += Integer.parseInt(splitData[2].trim())
                                * Integer.parseInt(splitData[3].trim());
                    } catch (NumberFormatException e) {
                        throw new RuntimeException("Wrong number: " + splitData[2] + " or " + splitData[3], e);
                    }

                }
            }
        }

        stringBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(salaryInfo[i]);
        }
        return stringBuilder.toString();
    }
}
