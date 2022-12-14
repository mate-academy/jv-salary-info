package core.basesyntax;

public class SalaryInfo {
    private int dateInt(String date) {
        String[] dataArray = date.split("\\.");
        return Integer.parseInt(dataArray[2] + dataArray[1] + dataArray[0]);
    }

    private int incomeToInt(String income) {
        return Integer.parseInt(income);
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);
        for (String name : names) {
            int income = 0;
            for (String datForSplit : data) {
                String[] dat = datForSplit.split(" ");
                if (name.equals(dat[1]) && dateInt(dat[0]) >= dateInt(dateFrom)
                        && dateInt(dat[0]) <= dateInt(dateTo)) {
                    income = income + incomeToInt(dat[3]) * incomeToInt(dat[2]);
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(income);
        }
        return builder.toString();
    }
}
