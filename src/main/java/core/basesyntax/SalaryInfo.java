package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder resultSalaryReport =
                new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        try {
            Date dateBegin = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
            Date dateEnd = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
            for (String name : names) {
                resultSalaryReport.append(
                        "\r\n").append(name).append(" - ").append(salaryCounter(name, data, dateBegin, dateEnd));
            }
        } catch (ParseException e) {
            System.out.println("bad data parameters format");
            return null;
        }

        return resultSalaryReport.toString();
    }

    int salaryCounter(String name, String[] data, Date dateBegin, Date dateEnd) {
        int result = 0;
//        String[] dataParsedString = new String[]{"","","",""};
        for (String jobInfo : data) {
            String[] dataParsedString = jobInfo.split(" ");
            try {
                Date salaryDate = new SimpleDateFormat("dd.MM.yyyy")
                        .parse(dataParsedString[0]);
                if ((salaryDate.after(dateBegin) || salaryDate.equals(dateBegin))
                        && (salaryDate.before(dateEnd) || salaryDate.equals(dateEnd))
                        && name.equals(dataParsedString[1])) {
                    result += Integer.parseInt(dataParsedString[2])
                            * Integer.parseInt(dataParsedString[3]);
                }
            } catch (ParseException e) {
                System.out.println("bad user data format");
            }
        }
        return result;
    }
}
