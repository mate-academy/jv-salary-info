package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder finalReport = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        LocalDate startDate = LocalDate.of(Integer.valueOf(dateFrom.substring(6,10)),
                Integer.valueOf(dateFrom.substring(3, 5)),
                Integer.valueOf(dateFrom.substring(0, 2)));
        LocalDate endDate = LocalDate.of(Integer.valueOf(dateTo.substring(6,10)),
                Integer.valueOf(dateTo.substring(3, 5)),
                Integer.valueOf(dateTo.substring(0, 2)));
        for (int i = 0; i < names.length; i++) {
            int userSalart = 0;
            for (int j = 0; j < data.length; j++) {
                LocalDate localDate = LocalDate.of(Integer.valueOf(data[j].substring(6,10)),
                        Integer.valueOf(data[j].substring(3, 5)),
                        Integer.valueOf(data[j].substring(0, 2)));
                if ((localDate.equals(startDate) || localDate.equals(endDate)
                        || localDate.isAfter(startDate) && localDate.isBefore(endDate))
                        && data[j].contains(names[i])) {
                    String[] userData = data[j].split(" ");
                    userSalart += Integer.parseInt(userData[2])
                            * Integer.parseInt(userData[3]);
                }
            }
            finalReport.append("\n").append(names[i]).append(" - ").append(userSalart);
        }

        return finalReport.toString();
    }
}
