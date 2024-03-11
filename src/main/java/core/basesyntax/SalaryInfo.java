package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int dateCounter = 0;
        int counter = 0;

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
        for (String OneData : data) {
            LocalDate localOneData = LocalDate.of(
                    Integer.parseInt(OneData.substring(6, 10)),
                    Integer.parseInt(OneData.substring(3, 5)),
                    Integer.parseInt(OneData.substring(0, 2))
            );
            if (localOneData.isAfter(localDateFrom) && localOneData.isBefore(localDateTo)) {
                dateCounter++;
            }
        }

        String[] dataWithCorrectDates = new String[dateCounter];
        for (String OneData : data) {
            LocalDate localOneData = LocalDate.of(
                    Integer.parseInt(OneData.substring(6, 10)),
                    Integer.parseInt(OneData.substring(3, 5)),
                    Integer.parseInt(OneData.substring(0, 2))
            );
            if (localOneData.isAfter(localDateFrom) && localOneData.isBefore(localDateTo)) {
                dataWithCorrectDates[counter++] = OneData;
            }
        }

        StringBuilder stringBuilder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String OneName : names) {
            int salary = 0;
            for (String OneData : dataWithCorrectDates) {
                if (OneData.substring(11, 11 + OneName.length()).trim().equals(OneName)) {
                    salary += Integer.parseInt(OneData.substring(11 + OneName.length() + 1));
                }
            }
            stringBuilder.append("\n").append(OneName).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
