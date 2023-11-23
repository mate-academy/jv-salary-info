package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SEPARATOR = " ";
    private static final String HEADER = "Report for period ";
    private static final String DELIMITER = " - ";
    private static final int DATE_NUMBER = 0;
    private static final int NAME_NUMBER = 1;
    private static final int HOUR_NUMBER = 2;
    private static final int SALARY_BY_HOUR_NUMBER = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);

        Map<String, Integer> nameSalaryMap = new HashMap<>();
        for (String name : names) {
            nameSalaryMap.put(name, 0);
        }

        for (String oneDataString : data) {
            String[] dataSplitFormat = oneDataString.split(SEPARATOR);
            LocalDate dateFromData = LocalDate.parse(dataSplitFormat[DATE_NUMBER], DATE_FORMAT);
            String dataName = dataSplitFormat[NAME_NUMBER];

            if (!dateFromData.isBefore(fromDate) && !dateFromData.isAfter(toDate)) {
                if (nameSalaryMap.containsKey(dataName)) {
                    Integer oldValue = nameSalaryMap.get(dataSplitFormat[NAME_NUMBER]);
                    Integer dataHour = Integer.parseInt(dataSplitFormat[HOUR_NUMBER]);
                    Integer dataSalary = Integer.parseInt(dataSplitFormat[SALARY_BY_HOUR_NUMBER]);

                    nameSalaryMap.put(dataName, oldValue + dataHour * dataSalary);
                }

            }
        }

        //BuildResultToString
        StringBuilder result = new StringBuilder();
        result.append(HEADER).append(dateFrom).append(DELIMITER).append(dateTo);
        for (String name : names) {
            result.append("\n").append(name).append(DELIMITER).append(nameSalaryMap.get(name));
        }
        return result.toString();
    }
}
