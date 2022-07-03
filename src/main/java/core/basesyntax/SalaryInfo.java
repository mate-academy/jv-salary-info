package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private StringBuilder totalSalaryInfo = new StringBuilder();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        totalSalaryInfo.delete(0, totalSalaryInfo.length());
        totalSalaryInfo.append("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int usersSalary = 0;
            if (name == null || name.equals("")) {
                continue;
            }
            for (int i = 0; i < data.length; i++) {
                try {
                    String[] dataParse = data[i].split("\\s");
                    Date dataParseDate =
                            new SimpleDateFormat("dd.MM.yyyy").parse(dataParse[0]);
                    Date dateFromDat =
                            new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
                    Date dateToDate =
                            new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
                    if ((dataParseDate.after(dateFromDat)
                            || dataParseDate.equals(dateFromDat))
                            && (dataParseDate.before(dateToDate)
                            || dataParseDate.equals(dateToDate))
                            && name.equals(dataParse[1])) {
                        usersSalary += Integer.valueOf(dataParse[2])
                                * Integer.valueOf(dataParse[3]);
                    }
                } catch (Exception e) {
                    System.out.println("We have IOExeption");
                }
            }
            totalSalaryInfo.append(System.lineSeparator() + name + " - " + usersSalary);
        }
        return totalSalaryInfo.toString();
    }
}
