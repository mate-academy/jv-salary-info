package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SalaryInfo info = new SalaryInfo();
        StringBuilder result = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String name : names) {
            int amontOfMoney = 0;
            for (String datum : data) {
                String[] split = datum.split(" ");
                if (name.equals(split[1])) {
                    if (info.getDateCode(split[0]) >= info.getDateCode(dateFrom)
                            && info.getDateCode(split[0]) <= info.getDateCode(dateTo)) {
                        amontOfMoney += Integer.parseInt(split[2]) * Integer.parseInt(split[3]);
                    }
                }
            }
            result.append(System.lineSeparator()).append(name)
                    .append(" - ").append(amontOfMoney);
        }
        return result.toString();
    }

    private double getDateCode(String date) {
        String[] split = date.split("\\.");
        double resultedNumber = 0;
        for (int i = split.length - 1, j = 1; i >= 0; i--, j *= 100) {
            resultedNumber += Double.parseDouble(split[i]) / j;
        }
        return resultedNumber;
    }
}
