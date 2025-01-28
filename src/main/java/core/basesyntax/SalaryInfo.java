package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder infoBuilder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            infoBuilder.append(System.lineSeparator()).append(name);
            int sumSalary = 0;
            for (String dataString : data) {
                String[] dataSplit = dataString.split(" ");
                if (dataSplit[1].equals(name)) {
                    LocalDate localDate = LocalDate.parse(dataSplit[0], format);
                    LocalDate fromDate = LocalDate.parse(dateFrom, format);
                    LocalDate toDate = LocalDate.parse(dateTo, format);
                    if (localDate.isEqual(fromDate)
                            || localDate.isAfter(fromDate) && localDate.isBefore(toDate)
                            || localDate.isEqual(toDate)) {
                        sumSalary += Integer.parseInt(dataSplit[2])
                                * Integer.parseInt(dataSplit[3]);
                    }
                }
            }
            infoBuilder.append(" - ").append(sumSalary);
        }
        return infoBuilder.toString();
    }
}
