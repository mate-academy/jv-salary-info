package core.basesyntax;

import core.basesyntax.utils.DataUtils;

public class SalaryInfo {
    static final String FORMAT = "dd.MM.yyyy";
    private final StringBuilder report = new StringBuilder();
    private final DataUtils dataUtils = new DataUtils();
    private int salaryOfEmployee;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String [] splitData;
        for (String name : names) {
            for (int i = 0; i < data.length; i++) {
                if (data[i].contains(name)) {
                    splitData = data[i].split(" ");
                    String reportedDate = splitData[0];
                    int reportedHours = Integer.parseInt(splitData[2]);
                    int hourlyWage = Integer.parseInt(splitData[3]);
                    if (dataUtils.isDateInRange(dateFrom, dateTo, reportedDate, FORMAT)) {
                        salaryOfEmployee = salaryOfEmployee + reportedHours * hourlyWage;
                    }
                }
            }
            report.append(name).append(" - ").append(salaryOfEmployee);
            if (!name.equals(names[names.length - 1])) {
                report.append(System.lineSeparator());
            }
            salaryOfEmployee = 0;
        }
        return "Report for period " + dateFrom + " - " + dateTo + System.lineSeparator() + report;
    }
}
