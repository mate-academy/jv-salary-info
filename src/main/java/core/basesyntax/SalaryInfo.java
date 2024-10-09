package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_TIME_INDEX = 2;
    private static final int WAGE_RATE_INDEX = 3;
    private static final String SEPARATOR = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);
        String[] workerSalaries = new String[names.length];

        for (int i = 0; i < workerSalaries.length; i++) {
            workerSalaries[i] = names[i] + SEPARATOR + 0;
        }

        for (String date : data) {
            String[] worker = date.split(" ");
            LocalDate workDate = LocalDate.parse(worker[DATE_INDEX], DATE_FORMAT);

            if ((workDate.isAfter(fromDate) && workDate.isBefore(toDate))
                    || workDate.equals(fromDate) || workDate.equals(toDate)) {
                int salary = Integer.parseInt(worker[WORK_TIME_INDEX])
                        * Integer.parseInt(worker[WAGE_RATE_INDEX]);
                for (int i = 0; i < workerSalaries.length; i++) {
                    String name = workerSalaries[i].substring(0, workerSalaries[i].indexOf(" "));
                    if (name.equals(worker[NAME_INDEX])) {
                        int oldSalary = Integer.parseInt(workerSalaries[i]
                                .substring(workerSalaries[i].indexOf(SEPARATOR) + 3));
                        workerSalaries[i] = name + SEPARATOR + (salary + oldSalary);
                    }
                }
            }
        }

        return generateReport(fromDate, toDate, workerSalaries);
    }

    private String generateReport(LocalDate fromDate, LocalDate toDate, String[] workerSalaries) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(fromDate.format(DATE_FORMAT))
                .append(SEPARATOR).append(toDate.format(DATE_FORMAT));
        for (String workerSalary : workerSalaries) {
            report.append(System.lineSeparator()).append(workerSalary);
        }

        return report.toString();
    }
}
