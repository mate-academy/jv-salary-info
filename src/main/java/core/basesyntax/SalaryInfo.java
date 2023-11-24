package core.basesyntax;

import core.basesyntax.utils.DataUtils;

public class SalaryInfo {
    static final String FORMAT = "dd.MM.yyyy";
    private StringBuilder report = new StringBuilder();
    private DataUtils dataUtils = new DataUtils();
    private int salaryOfEmployee;
    private String reportedDate;
    private int reportedHours;
    private int hourlyWage;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String [] splitData = new String[4];

        for (String name : names) {
            for (int i = 0; i < data.length; i++) {
                if (data[i].contains(name)) {
                    splitData = data[i].split(" ");
                    reportedDate = splitData[0];
                    reportedHours = Integer.valueOf(splitData[2]);
                    hourlyWage = Integer.valueOf(splitData[3]);
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
        return "Report for period " + dateFrom + " - " + dateTo + System.lineSeparator()
            + report.toString();
    }
}
