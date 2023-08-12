package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final int START_INDEX = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder info = new StringBuilder("Report for period ");
        info.append(dateFrom).append(" - ").append(dateTo);

        LocalDate dateFromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate dateToDate = LocalDate.parse(dateTo, DATE_FORMAT);

        for (String name : names) {
            int tempSalaryInMonth = 0;
            for (int j = START_INDEX; j < data.length; j++) {
                String[] tempSpaceSeparatorData = data[j].split(" ");

                LocalDate temporaryDate = LocalDate
                        .parse(tempSpaceSeparatorData[START_INDEX], DATE_FORMAT);

                if ((temporaryDate.isAfter(dateFromDate)
                        || temporaryDate.equals(dateFromDate))
                        && (temporaryDate.isBefore(dateToDate)
                        || temporaryDate.equals(dateToDate))) {
                    if (data[j].contains(name)) {
                        int tempHourInThatDay = Integer.parseInt(tempSpaceSeparatorData[2]);
                        int tempSalaryPerHourInThatDay = Integer
                                .parseInt(tempSpaceSeparatorData[3]);

                        int tempSalarySumInThatDay =
                                tempHourInThatDay * tempSalaryPerHourInThatDay;
                        tempSalaryInMonth += tempSalarySumInThatDay;
                    }
                }
                if (j == data.length - 1) {
                    info.append(System.lineSeparator()).append(name)
                            .append(" - ").append(tempSalaryInMonth);
                }
            }
        }
        return info.toString();
    }
}
