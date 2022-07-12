package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            salaryInfo.append(name)
                    .append(" - ")
                    .append(getSalaryAmount(name, data, dateFrom, dateTo))
                    .append(System.lineSeparator());
        }
        salaryInfo.setLength(salaryInfo.length() - System.lineSeparator().length());
        return String.valueOf(salaryInfo);
    }

    private String getSalaryAmount(String name, String[] data, String dateFrom, String dateTo) {
        int salaryAmount = 0;
        for (String dataForProcessing : data) {
            if (dataForProcessing.contains(name)) {
                salaryAmount += payForCheckedDay(dataForProcessing, dateFrom, dateTo);
            }
        }
        return String.valueOf(salaryAmount);
    }

    private int payForCheckedDay(String dataForProcessing, String dateFrom, String dateTo) {
        String[] delimitedString = dataForProcessing.split(" ");
        int payForCheckedDay = 0;
        try {
            Date startDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
            Date endDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
            Date checkedDate = new SimpleDateFormat("dd.MM.yyyy").parse(delimitedString[0]);
            if ((checkedDate.equals(startDate) || checkedDate.after(startDate))
                    && (checkedDate.before(endDate) || checkedDate.equals(endDate))) {
                payForCheckedDay = Integer.parseInt(delimitedString[2])
                        * Integer.parseInt(delimitedString[3]);
            }
        } catch (ParseException e) {
            System.out.println("Date parsing error");
        }
        return payForCheckedDay;
    }
}
