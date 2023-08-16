package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final int START_INDEX = 0;
    private static final String SEPARATOR = " ";
    private static final String DELIMITER = " - ";
    private static final String HEADER = "Report for period ";
    private static final int NUMBER_ONE = 1;
    private static final int INDEX_TWO = 2;
    private static final int INDEX_THREE = 3;


    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder info = new StringBuilder(HEADER);
        info.append(dateFrom).append(DELIMITER).append(dateTo);

        LocalDate dateFromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate dateToDate = LocalDate.parse(dateTo, DATE_FORMAT);

        for (String name : names) {
            int tempSalaryInMonth = 0;
            for (int j = START_INDEX; j < data.length; j++) {
                String[] tempSpaceSeparatorData = data[j].split(SEPARATOR);

                LocalDate temporaryDate = LocalDate
                        .parse(tempSpaceSeparatorData[START_INDEX], DATE_FORMAT);

                if ((temporaryDate.isAfter(dateFromDate)
                        || temporaryDate.equals(dateFromDate))
                        && (temporaryDate.isBefore(dateToDate)
                        || temporaryDate.equals(dateToDate))) {
                    if (data[j].contains(name)) {
                        int tempHourInThatDay = Integer.parseInt(tempSpaceSeparatorData[INDEX_TWO]);
                        int tempSalaryPerHourInThatDay = Integer
                                .parseInt(tempSpaceSeparatorData[INDEX_THREE]);

                        int tempSalarySumInThatDay =
                                tempHourInThatDay * tempSalaryPerHourInThatDay;
                        tempSalaryInMonth += tempSalarySumInThatDay;
                    }
                }
                if (j == data.length - NUMBER_ONE) {
                    info.append(System.lineSeparator()).append(name)
                            .append(DELIMITER).append(tempSalaryInMonth);
                }
            }
        }
        return info.toString();
    }
}
