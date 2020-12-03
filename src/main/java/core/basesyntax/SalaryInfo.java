package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATA_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder finalReport = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        LocalDate startDate = LocalDate.parse(dateFrom, DATA_FORMAT);
        LocalDate endDate = LocalDate.parse(dateTo, DATA_FORMAT);
        for (int i = 0; i < names.length; i++) {
            int userSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] userData = data[j].split(" ");
                LocalDate localDate = LocalDate.parse(userData[0], DATA_FORMAT);
                if ((localDate.equals(startDate) || localDate.equals(endDate)
                        || localDate.isAfter(startDate) && localDate.isBefore(endDate))
                        && data[j].contains(names[i])) {
                    userSalary += Integer.parseInt(userData[2])
                            * Integer.parseInt(userData[3]);
                }
            }
            finalReport.append("\n").append(names[i]).append(" - ").append(userSalary);
        }
        return finalReport.toString();
    }
}
