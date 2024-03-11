package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int dataCounter = 0;

        LocalDate localDateFrom = LocalDate.of(
                Integer.parseInt(dateFrom.substring(6, 10)),
                Integer.parseInt(dateFrom.substring(3, 5)),
                Integer.parseInt(dateFrom.substring(0, 2))
        );
        LocalDate localDateTo = LocalDate.of(
                Integer.parseInt(dateTo.substring(6, 10)),
                Integer.parseInt(dateTo.substring(3, 5)),
                Integer.parseInt(dateTo.substring(0, 2))
        );
        for (String oneData : data) {
            LocalDate localOneData = LocalDate.of(
                    Integer.parseInt(oneData.substring(6, 10)),
                    Integer.parseInt(oneData.substring(3, 5)),
                    Integer.parseInt(oneData.substring(0, 2))
            );
            if (localOneData.isAfter(localDateFrom) && localOneData.isBefore(localDateTo)) {
                dataCounter++;
            }
        }

        int counter = 0;
        String[] dataWithCorrectDates = new String[dataCounter];
        for (String oneData : data) {
            LocalDate localOneData = LocalDate.of(
                    Integer.parseInt(oneData.substring(6, 10)),
                    Integer.parseInt(oneData.substring(3, 5)),
                    Integer.parseInt(oneData.substring(0, 2))
            );
            if (localOneData.isAfter(localDateFrom) && localOneData.isBefore(localDateTo)) {
                dataWithCorrectDates[counter++] = oneData;
            }
        }

        StringBuilder stringBuilder = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo
        );
        for (String oneName : names) {
            int salary = 0;
            for (String oneData : dataWithCorrectDates) {
                String NameInData = oneData.substring(11, 11 + oneName.length());
                if (NameInData.equals(oneName)) {
                    salary += Integer.parseInt(oneData.substring(11 + oneName.length()));
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(oneName).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
