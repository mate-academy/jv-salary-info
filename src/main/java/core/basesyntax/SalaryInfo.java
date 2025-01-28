package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder infoBuilder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            infoBuilder.append("\n").append(name);
            int sumSalary = 0;
            for (String dataString : data) {
                String[] dataSplit = dataString.split(" ");
                if (dataSplit[1].equals(name)) {
                    int dataDay = Integer.parseInt(dataSplit[0].substring(0, 2));
                    int dataMonth = Integer.parseInt(dataSplit[0].substring(3, 5));
                    int dataNum = dataMonth * 10 + dataDay;
                    int fromDay = Integer.parseInt(dateFrom.substring(0, 2));
                    int fromMonth = Integer.parseInt(dateFrom.substring(3, 5));
                    int fromNum = fromMonth * 10 + fromDay;
                    int toDay = Integer.parseInt(dateTo.substring(0, 2));
                    int toMonth = Integer.parseInt(dateTo.substring(3, 5));
                    int toNum = toMonth * 10 + toDay;
                    if (dataNum >= fromNum && dataNum <= toNum) {
                        sumSalary += Integer.parseInt(dataSplit[2])
                                * Integer.parseInt(dataSplit[3]);
                    }
                }
            }
            infoBuilder.append(" - ").append(sumSalary);
        }
        return infoBuilder.toString();
    }
}
