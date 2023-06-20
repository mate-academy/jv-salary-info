package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    static final int SALARY_DATA_DATE = 0;
    static final int SALARY_DATA_NAME = 1;
    static final int SALARY_DATA_HOUR = 2;
    static final int SALARY_DATA_SUM = 3;


    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] sumSalary = new int[names.length];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date dateStart;
        try {
            dateStart = simpleDateFormat.parse(dateFrom);
        } catch (ParseException e) {
            throw new DateFormatException("Error while parsing dateStart...");
        }
        Date dateEnd;
        try {
            dateEnd = simpleDateFormat.parse(dateTo);
        } catch (ParseException e) {
            throw new DateFormatException("Error while parsing dateEnd...");
        }
        for (String datum : data) {
            String[] salaryData = datum.split(" ");
            Date salaryDate;
            try {
                salaryDate = simpleDateFormat.parse(salaryData[SALARY_DATA_DATE]);
            } catch (ParseException e) {
                throw new DateFormatException("Error while parsing salaryDate...");
            }
            for (int j = 0; j < names.length; j++) {
                if (names[j].equals(salaryData[SALARY_DATA_NAME])) {
                    if (salaryDate.compareTo(dateStart) >= 0
                            && salaryDate.compareTo(dateEnd) <= 0) {
                        sumSalary[j] += Integer.parseInt(salaryData[SALARY_DATA_HOUR])
                                * Integer.parseInt(salaryData[SALARY_DATA_SUM]);
                    }
                }
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(sumSalary[i]);
        }
        return result.toString();
    }
}
