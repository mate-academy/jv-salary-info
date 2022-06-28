package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    private static final byte DATE = 0;
    private static final byte WORKERNAME = 1;
    private static final byte HOURS = 2;
    private static final byte PAYPERHOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder resultSalaryReport =
                new StringBuilder()
                        .append("Report for period ")
                        .append(dateFrom)
                        .append(" - ")
                        .append(dateTo);
        try {
            Date dateBegin = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
            Date dateEnd = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
            for (String workerName : names) {
                resultSalaryReport
                        .append(System.lineSeparator())
                        .append(workerName)
                        .append(" - ")
                        .append(workerSalaryCalculator(workerName, data, dateBegin, dateEnd));
            }
        } catch (ParseException e) {
            System.out.println("bad data parameters format");
            return null;
        }

        return resultSalaryReport.toString();
    }

    int workerSalaryCalculator(String workerName, String[] data, Date dateBegin, Date dateEnd) {
        int totalWorkerSalary = 0;
        for (String workerJobInfo : data) {
            String[] dataParsedString = workerJobInfo.split(" ");
            try {
                Date salaryDate = new SimpleDateFormat("dd.MM.yyyy")
                        .parse(dataParsedString[DATE]);
                totalWorkerSalary += ((salaryDate.after(dateBegin) || salaryDate.equals(dateBegin))
                        && (salaryDate.before(dateEnd) || salaryDate.equals(dateEnd))
                        && workerName.equals(dataParsedString[WORKERNAME])) ?
                        Integer.parseInt(dataParsedString[HOURS])
                        * Integer.parseInt(dataParsedString[PAYPERHOUR]) : 0 ;
            } catch (ParseException e) {
                System.out.println("bad user data format");
            }
        }
        return totalWorkerSalary;
    }
}
