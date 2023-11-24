package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int AMOUNT_OF_DATE_INDEX = 0;
    private static final int AMOUNT_OF_HOUR_INDEX = 2;
    private static final int AMOUNT_OF_MONEY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                                            + dateFrom + " - " + dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        for (int i = 0, fullSalary = 0; i < names.length; i++) {
            for (String pieceOfData : data) {
                if (pieceOfData.contains(names[i])) {
                    String[] allInfo = pieceOfData.split(" ");
                    if (isDateIncludes(localDateFrom, localDateTo, allInfo[AMOUNT_OF_DATE_INDEX])) {
                        fullSalary += Integer.parseInt(allInfo[AMOUNT_OF_HOUR_INDEX])
                                * Integer.parseInt(allInfo[AMOUNT_OF_MONEY_INDEX]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(names[i])
                            .append(" - ").append(fullSalary);
            fullSalary = 0;
        }
        return stringBuilder.toString();
    }

    private boolean isDateIncludes(LocalDate dateFrom, LocalDate dateTo, String currentDateString) {
        LocalDate currentDate = LocalDate.parse(currentDateString, FORMATTER);
        return (currentDate.isAfter(dateFrom) && currentDate.isBefore(dateTo))
                || (currentDate.isEqual(dateFrom) || currentDate.isEqual(dateTo));
    }
}
