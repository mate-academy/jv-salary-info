package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);

        StringBuilder salary = new StringBuilder("");
        salary.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");

        for (String name : names) {
            salary.append(name).append(" - ");
            
            int salarySum = 0;

            for (String employerData : data) {
                if (employerData.contains(name)) {
                    String[] arrayOfEmployerData
                            = employerData.split(" ");
                    LocalDate employerDate
                            = LocalDate.parse(arrayOfEmployerData[0], DATE_FORMATTER);
                    if (localDateFrom.compareTo(employerDate) <= 0
                            && localDateTo.compareTo(employerDate) >= 0) {
                        salarySum += Integer.parseInt(arrayOfEmployerData[2])
                                * Integer.parseInt(arrayOfEmployerData[3]);
                    }
                }
            }
            salary.append(salarySum).append("\n");
        }
        return salary.toString().trim();
    }
}
