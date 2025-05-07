package core.basesyntax;

import core.basesyntax.utils.DataUtils;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int HOURS_INDEX = 2;
    private static final int WAGE_INDEX = 3;
    private final DataUtils dataUtils = new DataUtils();
    private int salaryOfEmployee;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        String [] splitData;
        for (String name : names) {
            for (int i = 0; i < data.length; i++) {
                if (data[i].contains(name)) {
                    splitData = data[i].split(" ");
                    String reportedDate = splitData[DATE_INDEX];
                    int reportedHours = Integer.parseInt(splitData[HOURS_INDEX]);
                    int hourlyWage = Integer.parseInt(splitData[WAGE_INDEX]);
                    if (dataUtils.isDateInRange(dateFrom, dateTo, reportedDate)) {
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
