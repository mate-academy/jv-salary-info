package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SalaryInfo {
    private static final DateTimeFormatter FORMMATER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        int result = 0;
        LocalDate startDate = LocalDate.parse(dateFrom, FORMMATER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMMATER);
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            for (String dataString : data) {
                String [] infoFromData = dataString.split(" ");
                LocalDate currentDate = LocalDate.parse(infoFromData[0], FORMMATER);
                if (name.equals(infoFromData[1])) {
                    if (ChronoUnit.DAYS.between(currentDate, endDate) >= 0
                            && ChronoUnit.DAYS.between(startDate, currentDate) >= 0) {
                        result += Integer.parseInt(infoFromData[2])
                                * Integer.parseInt(infoFromData[3]);
                    }
                }
            }
            builder.append("\r\n").append(name).append(" - ").append(result);
            result = 0;
        }
        return builder.toString();
    }
}
