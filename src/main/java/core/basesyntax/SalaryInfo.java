package core.basesyntax;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder resultMessage = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int sumSalary = 0;
            for (String currentData : data) {
                String[] splitData = currentData.split(" ");
                if (splitData[1].equals(name) && isBetween(getDateFromString(dateFrom),
                        getDateFromString(dateTo),
                        getDateFromString(splitData[0]))) {
                    sumSalary += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            resultMessage
                    .append("\n")
                    .append(name)
                    .append(" - ")
                    .append(sumSalary);
        }
        return resultMessage.toString();
    }

    public Date getDateFromString(String date) {
        String[] splitDate = date.split("\\.");
        int[] splitIntDate = new int[splitDate.length];

        for (int i = 0; i < splitDate.length; i++) {
            splitIntDate[i] = Integer.parseInt(splitDate[i]);
        }

        Calendar calendar = new GregorianCalendar(splitIntDate[2], splitIntDate[1]-1, splitIntDate[0]);
        return calendar.getTime();
    }

    public boolean isBetween (Date startData, Date endData, Date currentDate) {
        return currentDate.before(endData) && currentDate.after(startData);
    }
}
