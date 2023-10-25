package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, dateFormatter);

        StringBuilder employerInfo = new StringBuilder();
        employerInfo.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        for (String name : names) {
            int totalSalary = 0;

            for (String workingTimeData : data){
                String[] parsedData = workingTimeData.split(" ");

                LocalDate dateToCheck = LocalDate.parse(parsedData[DATE_INDEX], dateFormatter);
                String parsedName = parsedData[NAME_INDEX];
                int hours = Integer.parseInt(parsedData[HOURS_WORKED_INDEX]);
                int payForHourAmount = Integer.parseInt(parsedData[HOURLY_RATE_INDEX]);
                if (parsedName.equals(name) &&  !dateToCheck.isBefore(dateStart)
                        && !dateToCheck.isAfter(dateEnd)) {
                    totalSalary += hours * payForHourAmount;
                }
            }
            employerInfo.append(name).append(" - ").append(totalSalary).append(System.lineSeparator());
        }
        employerInfo.delete(employerInfo.length() - System.lineSeparator().length(), employerInfo.length());
        return employerInfo.toString();
    }
}
