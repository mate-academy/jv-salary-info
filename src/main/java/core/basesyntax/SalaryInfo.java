package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final int INIT_SALARY_AMOUNT = 0;
    private static final String DATA_SEPARATOR = " ";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private Report report;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Map<String, Integer> nameSalaryMap = fillMapWithKeys(names);
        LocalDate dateFromBorder = stringDateIntoLocalDate(dateFrom).minusDays(1);
        LocalDate dateToBorder = stringDateIntoLocalDate(dateTo).plusDays(1);
        for (String line : data) {
            String[] info = line.split(DATA_SEPARATOR);
            LocalDate workDate = stringDateIntoLocalDate(info[0]);
            if (workDate.isAfter(dateFromBorder) && workDate.isBefore(dateToBorder)) {
                String workerName = info[1];
                if (nameSalaryMap.containsKey(workerName)) {
                    int workingHours = Integer.parseInt(info[2]);
                    int payForHour = Integer.parseInt(info[3]);
                    int currentSalary = nameSalaryMap.get(workerName);
                    nameSalaryMap.put(workerName,currentSalary + workingHours * payForHour);
                }
            }
        }
        report = new Report(dateFrom, dateTo, nameSalaryMap);
        return report.toString();
    }

    private Map<String, Integer> fillMapWithKeys(String[] names) {
        Map<String, Integer> nameSalaryMap = new HashMap<String, Integer>();
        for (String name : names) {
            nameSalaryMap.put(name, INIT_SALARY_AMOUNT);
        }
        return nameSalaryMap;
    }

    private LocalDate stringDateIntoLocalDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}

