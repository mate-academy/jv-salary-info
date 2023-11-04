package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        String[] dataInfo;
        LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, formatter);
        int totalSalary = 0;
        for (String name : names) {
            for (String info : data) {
                dataInfo = info.split(" ");
                if (name.equals(dataInfo[1])) {
                    LocalDate dateNow = LocalDate.parse(dataInfo[0], formatter);
                    if (dateNow.compareTo(dateStart) >= 0 && dateNow.compareTo(dateEnd) <= 0) {
                        totalSalary += Integer.parseInt(dataInfo[2])
                                * Integer.parseInt(dataInfo[3]);
                    }
                }
            }
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
            totalSalary = 0;
        }
        return result.toString();
    }

}
