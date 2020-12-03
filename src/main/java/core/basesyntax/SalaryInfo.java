package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder finalReport = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        LocalDate startDate = LocalDate.of(Integer.parseInt(dateFrom.substring(6,10)),
                Integer.parseInt(dateFrom.substring(3, 5)),
                Integer.parseInt(dateFrom.substring(0, 2)));
        LocalDate endDate = LocalDate.of(Integer.parseInt(dateTo.substring(6,10)),
                Integer.parseInt(dateTo.substring(3, 5)),
                Integer.parseInt(dateTo.substring(0, 2)));
        for (int i = 0; i < names.length; i++) {
            int userSalary = 0;
            for (int j = 0; j < data.length; j++) {

                LocalDate localDate = LocalDate.of(Integer.parseInt(data[j].substring(6,10)),
                        Integer.parseInt(data[j].substring(3, 5)),
                        Integer.parseInt(data[j].substring(0, 2)));
                if ((localDate.equals(startDate) || localDate.equals(endDate)
                        || localDate.isAfter(startDate) && localDate.isBefore(endDate))
                        && data[j].contains(names[i])) {
                    String[] userData = data[j].split(" ");
                    userSalary += Integer.parseInt(userData[2])
                            * Integer.parseInt(userData[3]);
                }
            }
            finalReport.append("\n").append(names[i]).append(" - ").append(userSalary);
        }

        return finalReport.toString();
    }
}
