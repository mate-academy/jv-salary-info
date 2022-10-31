package core.basesyntax;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int WORK_DAY = 0;
    private static final int NAME = 1;
    private static final int WORK_HOURS = 2;
    private static final int PAY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salaryForPeriod = 0;
            for (String dataEmployees : data) {
                String [] personalData = dataEmployees.split(" ");
                try {
                    if (name.equals(personalData[NAME]) && isWorked(dateFrom,
                            dateTo, personalData[WORK_DAY])) {
                        salaryForPeriod += daySalary(Integer.parseInt(personalData[WORK_HOURS]),
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

    public boolean isWorked(String dateFrom, String dateTo, String workDay)
            throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

        LocalDate dateFromWork = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToWork = LocalDate.parse(dateTo, formatter);
        LocalDate jobDay = LocalDate.parse(workDay, formatter);
        return (jobDay.compareTo(dateFromWork) >= 0 && jobDay.compareTo(dateToWork) <= 0);
    }

    public int daySalary(int hours, int pay) {
        if (hours == 0 || pay == 0) {
            return 0;
        }
        return hours * pay;
    }
}
