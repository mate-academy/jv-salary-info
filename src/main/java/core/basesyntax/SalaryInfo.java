package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        LocalDate tempDate;
        int tempSalary = 0;
        String[] salaryInfo = new String[names.length];
        int indexOfDate = 0;
        int indexOfName = 1;
        int indexOfWorkingHour = 2;
        int indexOfIncome = 3;

        for (int i = 0; i < names.length; i++) {
            for (String info : data) {
                String[] splittedData = info.split(" ");
                if (splittedData[indexOfName].equals(names[i])) {
                    tempDate = LocalDate.parse(splittedData[indexOfDate], formatter);
                    if (tempDate.isAfter(startDate) && tempDate.isBefore(endDate)
                            || tempDate.equals(endDate)) {
                        tempSalary += Integer.parseInt(splittedData[indexOfWorkingHour])
                                * Integer.parseInt(splittedData[indexOfIncome]);
                    }
                }
            }
            salaryInfo[i] = names[i] + " - " + tempSalary;
            tempSalary = 0;
        }

        return createMessage(salaryInfo, dateFrom, dateTo);
    }
    
    private String createMessage(String[] salaryInfo, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);

        for (String personInfo: salaryInfo) {
            builder.append(System.lineSeparator());
            builder.append(personInfo);
        }

        return builder.toString();
    }
}
