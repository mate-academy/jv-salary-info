package core.basesyntax;

public class Report {

    private static final String NAME_SALARY_SEPARATOR = " - ";
    private final String dataFrom;
    private final String dataTo;
    private final String[] names;
    private final int[] salaries;

    public Report(String dataFrom, String dataTo, String[] names, int[] salaries) {
        this.dataFrom = dataFrom;
        this.dataTo = dataTo;
        this.names = names;
        this.salaries = salaries;
    }

    @Override
    public String toString() {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dataFrom).append(" - ")
                .append(dataTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            reportBuilder.append(names[i]).append(NAME_SALARY_SEPARATOR).append(salaries[i])
                    .append(System.lineSeparator());
        }
        return reportBuilder.toString().trim();
    }
}
