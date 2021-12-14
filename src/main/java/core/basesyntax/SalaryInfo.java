package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder sbReport =
                new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        LocalDate localDateIsAfter = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate localDateIsBefore = LocalDate.parse(dateTo, dateTimeFormatter);

        for (String name: names) {
            int salaryFromPeriod = 0;
            for (int i = 0; i < data.length; i++) {
                String[] splitDataArray = data[i].split(" ");
                LocalDate localDate =
                        LocalDate.parse(splitDataArray[0], dateTimeFormatter);
                if (localDate.isAfter(localDateIsAfter)
                        && (localDate.isBefore(localDateIsBefore)
                        || localDate.equals(localDateIsBefore))
                        && splitDataArray[1].equals(name)) {
                    salaryFromPeriod +=
                            Integer.parseInt(splitDataArray[2])
                                    * Integer.parseInt(splitDataArray[3]);
                }
            }
            sbReport.append(System.lineSeparator())
                    .append(name).append(" - ").append(salaryFromPeriod);
        }
        return sbReport.toString();
    }
}
