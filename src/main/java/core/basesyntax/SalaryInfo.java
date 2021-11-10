package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_PAYMENT = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder buildResult = new StringBuilder("Report for period ");
        buildResult.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            buildResult.append(name)
                    .append(" - ")
                    .append(getSalaryFromPerson(name, data, dateFrom, dateTo))
                    .append(System.lineSeparator());
        }
        return buildResult.toString().trim();
    }

    private int getSalaryFromPerson(String name, String[] data, String dateFrom, String dateTo) {
        int totalScoreOfPerson = 0;

        for (String info : data) {
            String[] infoArr = info.split(" ");
            if (name.equals(infoArr[INDEX_OF_NAME])) {
                LocalDate currentDate = getDate(infoArr[INDEX_OF_DATE]);
                LocalDate workFrom = getDate(dateFrom);
                LocalDate workTo = getDate(dateTo);

                if ((currentDate.isBefore(workTo) || currentDate.isEqual(workTo))
                        && (currentDate.isAfter(workFrom)) || currentDate.isEqual(workFrom)) {
                    totalScoreOfPerson += (Integer.parseInt(infoArr[INDEX_OF_HOURS])
                            * Integer.parseInt(infoArr[INDEX_OF_PAYMENT]));
                }
            }
        }
        return totalScoreOfPerson;
    }

    public LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }
}
