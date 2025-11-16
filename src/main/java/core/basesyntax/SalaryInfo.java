package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, dateFormatter);

        String[] reportData = new String[names.length];

        for (int i = 0; i < names.length; i++) {
            reportData[i] = getPersonInfo(names[i], data, localDateFrom, localDateTo);
        }

        String resultReportData = convertAndFormatReportData(reportData);

        return "Report for period " + dateFrom + " - " + dateTo + resultReportData;
    }

    public String getPersonInfo(String name, String[] data,
                                LocalDate localDateFrom, LocalDate localDateTo) {
        int moneyEarned = 0;

        for (String personInfo : data) {
            String[] splitPersonInfo = personInfo.split(" ");
            LocalDate dateForPerson = LocalDate.parse(splitPersonInfo[0], dateFormatter);

            if (!name.equals(splitPersonInfo[1])) {
                continue;
            }

            if (dateForPerson.isAfter(localDateFrom) && dateForPerson.isBefore(localDateTo)
                    || dateForPerson.isEqual(localDateFrom) || dateForPerson.isEqual(localDateTo)) {
                moneyEarned += Integer.parseInt(splitPersonInfo[2])
                        * Integer.parseInt(splitPersonInfo[3]);
            }
        }

        return name + " - " + moneyEarned;
    }

    public String convertAndFormatReportData(String[] reportData) {
        StringBuilder result = new StringBuilder();

        for (String personInReport: reportData) {
            result.append(System.lineSeparator());
            result.append(personInReport);
        }

        return result.toString();
    }
}
