package core.basesyntax;

import java.text.ParseException;

public class SalaryInfo {
    SalaryCalculator salaryCalculator = new SalaryCalculator();
    Dates dates = new Dates();
    StringBuilder builder = new StringBuilder();
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        builder.append("Report for period " + dateFrom + " - " + dateTo);
            for ( String name : names) {
                int salaryForPeriod = 0;
                for (String dataEmployees : data) {
                    String [] personalData = dataEmployees.split(" ");
                    try {
                        if (name.equals(personalData[1]) && dates.isWorked(dateFrom, dateTo, personalData[0])) {
                            salaryForPeriod += salaryCalculator.daySalary(Integer.parseInt(personalData[2]), Integer.parseInt(personalData[3]));
                        }
                    } catch (ParseException e) {
                        System.out.println("Incorrect data.");
                    }
                }
                builder.append("\n" + name + " - " + salaryForPeriod);
            }
        return builder.toString();
    }
}
