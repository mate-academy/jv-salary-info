package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_TIME_INDEX = 2;
    private static final int WAGE_RATE_INDEX = 3;
    private final Map<String, Integer> workerSalaries = new HashMap<>();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);

        for (String name : names) {
            workerSalaries.put(name, 0);
        }
        calculateSalaries(fromDate, toDate, data);

        return generateReport(fromDate, toDate, names);
    }

    private void calculateSalaries(LocalDate fromDate, LocalDate toDate, String[] data) {
        for (String date : data) {
            String[] worker = date.split(" ");
            LocalDate workDate = LocalDate.parse(worker[DATE_INDEX], DATE_FORMAT);

            if ((workDate.isAfter(fromDate) && workDate.isBefore(toDate))
                    || workDate.equals(fromDate) || workDate.equals(toDate)) {
                Integer salary = Integer.parseInt(worker[WORK_TIME_INDEX])
                        * Integer.parseInt(worker[WAGE_RATE_INDEX]);
                workerSalaries.put(worker[NAME_INDEX],
                        workerSalaries.get(worker[NAME_INDEX]) + salary);
            }
        }
    }

    private String generateReport(LocalDate fromDate, LocalDate toDate, String[] names) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(fromDate.format(DATE_FORMAT))
                .append(" - ").append(toDate.format(DATE_FORMAT));
        for (String name : names) {
            report.append(System.lineSeparator())
                    .append(name).append(" - ").append(workerSalaries.get(name));
        }

        return report.toString();
    }
}
