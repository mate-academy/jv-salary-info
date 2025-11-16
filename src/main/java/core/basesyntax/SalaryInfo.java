package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_IND = 0;
    private static final int NAME_IND = 1;
    private static final int HOURS_IND = 2;
    private static final int HOUR_RATE_IND = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMAT);

        StringBuilder resultReportData = new StringBuilder();

        for (String name : names) {
            resultReportData.append(System.lineSeparator());
            resultReportData.append(getPersonInfo(name, data, localDateFrom, localDateTo));
        }

        return "Report for period " + dateFrom + " - " + dateTo + resultReportData;
    }

    public StringBuilder getPersonInfo(String name, String[] data,
                                       LocalDate localDateFrom, LocalDate localDateTo) {
        int moneyEarned = 0;

        for (String personInfo : data) {
            String[] splitPersonInfo = personInfo.split(" ");
            LocalDate dateForPerson = LocalDate.parse(splitPersonInfo[DATE_IND], DATE_FORMAT);

            if (!name.equals(splitPersonInfo[NAME_IND])) {
                continue;
            }

            if (dateForPerson.isAfter(localDateFrom) && dateForPerson.isBefore(localDateTo)
                    || dateForPerson.isEqual(localDateFrom) || dateForPerson.isEqual(localDateTo)) {
                moneyEarned += Integer.parseInt(splitPersonInfo[HOURS_IND])
                        * Integer.parseInt(splitPersonInfo[HOUR_RATE_IND]);
            }
        }

        return new StringBuilder(name).append(" - ").append(moneyEarned);
    }
}
