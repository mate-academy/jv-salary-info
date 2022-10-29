package core.basesyntax;

import java.text.ParseException;

public class SalaryInfo {
    private static final int WORK_DAY = 0;
    private static final int NAME = 1;
    private static final int WORK_HOURS = 2;
    private static final int PAY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Dates dates = new Dates();
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salaryForPeriod = 0;
            for (String dataEmployees : data) {
                String [] personalData = dataEmployees.split(" ");
                try {
                    if (name.equals(personalData[NAME]) && dates.isWorked(dateFrom,
                            dateTo, personalData[WORK_DAY])) {
                        salaryForPeriod += dates
                                .daySalary(Integer.parseInt(personalData[WORK_HOURS]),
                                        Integer.parseInt(personalData[PAY_PER_HOUR]));
                    }
                } catch (ParseException e) {
                    System.out.println("Incorrect data.");
                }
            }
            reportBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salaryForPeriod);
        }
        return reportBuilder.toString();
    }
}
