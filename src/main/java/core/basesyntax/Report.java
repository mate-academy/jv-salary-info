package core.basesyntax;

import java.util.Map;

public class Report {

    private static final String NAME_SALARY_SEPARATOR = " - ";
    private final String dataFrom;
    private final String dataTo;
    private final Map<String, Integer> workerNameSalaryMap;

    public Report(String dataFrom, String dataTo, Map<String, Integer> workerNameSalaryMap) {
        this.dataFrom = dataFrom;
        this.dataTo = dataTo;
        this.workerNameSalaryMap = workerNameSalaryMap;
    }

    @Override
    public String toString() {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dataFrom).append(" - ")
                .append(dataTo).append('\n');
        int reportHeaderLength = reportBuilder.length();
        for (String name : workerNameSalaryMap.keySet()) {
            StringBuilder workerSalaryBuilder = new StringBuilder();
            workerSalaryBuilder.append(name).append(NAME_SALARY_SEPARATOR)
                    .append(workerNameSalaryMap.get(name))
                    .append('\n');
            reportBuilder.insert(reportHeaderLength, workerSalaryBuilder);
        }
        return reportBuilder.toString().trim();
    }
}
