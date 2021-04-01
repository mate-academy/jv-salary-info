package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate lastDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append("\n");
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            String name = names[i];
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(name)) {
                    String getDate = "";
                    getDate += data[j];
                    String[] splitDate = getDate.split(" ");
                    LocalDate localDate = LocalDate.parse(splitDate[0],DATE_FORMATTER);
                    if (localDate.isAfter(firstDate)
                            && localDate.isBefore(lastDate.plusDays(1))) {
                        salary += Integer.parseInt(splitDate[2])
                                * Integer.parseInt(splitDate[3]);
                    }
                }
            }
            report.append(name).append(" - ").append(salary).append("\n");
        }
        return report.toString().trim();
    }
}
