package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalaryInfo {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static String WHITESPACE_DELIMITER = " ";
    private Map<String, Integer> workerInfo = new LinkedHashMap<>();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        setWorkersName(workerInfo,names);
        for (String record : data) {
            String[] parts = record.split(WHITESPACE_DELIMITER);
            String employeeName = parts[1];
            LocalDate parseDate = LocalDate.parse(parts[0], formatter);
            int parsedHour = Integer.parseInt(parts[2]);
            int parsedMoney = Integer.parseInt(parts[3]);
            if (parseDate.isBefore(localDateTo.plusDays(1L)) && parseDate.isAfter(localDateFrom)) {
                int earnAday = parsedHour * parsedMoney;
                workerInfo.put(employeeName, workerInfo.get(employeeName) + earnAday);
            }
        }
        return createReport(workerInfo, dateFrom, dateTo);
    }

    private Map<String, Integer> setWorkersName(Map<String, Integer> workerInfo,String[] names) {
        for (String name: names) {
            workerInfo.put(name,0);
        }
        return workerInfo;
    }

    private String createReport(Map<String,Integer> workerInfo,String dateFrom,String dateTo) {
        StringBuilder resultString = new StringBuilder(
                String.format("Report for period %s - %s",dateFrom,dateTo))
                .append(System.lineSeparator());
        for (Map.Entry<String, Integer> info : workerInfo.entrySet()) {
            resultString.append(info.getKey() + " - " + info.getValue())
                    .append(System.lineSeparator());
        }
        resultString.delete(resultString.length() - System.lineSeparator().length(),
                resultString.length());
        return resultString.toString();
    }
}
