package core.basesyntax;

import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        Date dateStart = getDate(dateFrom);
        Date dateEnd = getDate(dateTo);

        for (String name : names) {
            result.append("\n").append(name).append(" - ");
            int salary = 0;
            for (String info : data) {
                if (info.contains(name)) {
                    String[] infoArray = info.split(" ");
                    Date worklDate = getDate(infoArray[0]);
                    if (worklDate.getTime() >= dateStart.getTime()
                            && worklDate.getTime() <= dateEnd.getTime()) {
                        int hour = Integer.parseInt(infoArray[2]);
                        int rate = Integer.parseInt(infoArray[3]);
                        salary += hour * rate;
                    }
                }
            }
            result.append(salary);
        }
        return result.toString();
    }

    private Date getDate(String stringDate) {
        String[] tempDateArray = stringDate.split("\\.");
        int tempDay = Integer.parseInt(tempDateArray[0]);
        int tempMonth = Integer.parseInt(tempDateArray[1]);
        int tempYear = Integer.parseInt(tempDateArray[2]);
        return new Date(tempYear, tempMonth, tempDay);
    }
}
