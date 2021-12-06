package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SalaryParser salaryParser = new SalaryParser();
        WorkerSalary[] workerSalary = salaryParser.parseSalaryData(data);
        LocalDate startDate = salaryParser
                .convertToLocalDate(dateFrom);
        LocalDate endDate = salaryParser
                .convertToLocalDate(dateTo);
        System.out.println(salaryParser
                .convertToLocalDate(dateTo));
        System.out.println(endDate);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salaryAccumulator = 0;
            for (WorkerSalary worker : workerSalary) {
                if ((worker.getDate().isAfter(startDate)
                        || worker.getDate().equals(startDate))
                        && (worker.getDate().isBefore(endDate)
                        || worker.getDate().equals(endDate))) {
                    if (worker.getName().equals(name)) {
                        salaryAccumulator += worker.getSalary();
                    }
                }
            }
            stringBuilder
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ").append(salaryAccumulator);

        }
        return stringBuilder.toString();
    }
}

