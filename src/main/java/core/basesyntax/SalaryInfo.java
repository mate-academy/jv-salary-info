package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = formatter(dateFrom);
        LocalDate endDate = formatter(dateTo);
        LocalDate tempDate;
        int tempSalary = 0;
        String[] salaryInfo = new String[names.length];

        for (int i = 0; i < names.length; i++) {
            for (String info: data) {
                if (info.split(" ")[1].equals(names[i])) {
                    tempDate = formatter(info.split(" ")[0]);
                    if (tempDate.isAfter(startDate) && tempDate.isBefore(endDate)
                            || tempDate.equals(endDate)) {
                        tempSalary += Integer.parseInt(info.split(" ")[2])
                                * Integer.parseInt(info.split(" ")[3]);
                    }
                }
            }
            salaryInfo[i] = names[i] + " - " + tempSalary;
            tempSalary = 0;
        }

        return messageCreator(salaryInfo, dateFrom, dateTo);
    }

    private LocalDate formatter(String date) {
        StringBuilder builder = new StringBuilder();

        for (int i = 2; i >= 0; i--) {
            builder.append(date.split("\\.")[i]);
            builder.append("-");
        }

        builder.deleteCharAt(builder.length() - 1);
        return LocalDate.parse(builder.toString());
    }

    private String messageCreator(String[] salaryInfo, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);

        for (String personInfo: salaryInfo) {
            builder.append(System.lineSeparator());
            builder.append(personInfo);
        }

        return builder.toString();
    }
}
