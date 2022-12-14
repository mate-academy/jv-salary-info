package core.basesyntax;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);
        final int index0 = 0;
        final int index1 = 1;
        final int index2 = 2;
        final int index3 = 3;
        for (String name : names) {
            int income = 0;
            for (String dataForSplit : data) {
                String[] dataArray = dataForSplit.split(" ");
                if (name.equals(dataArray[index1]) && parseDateToInt(dataArray[index0])
                        >= parseDateToInt(dateFrom) && parseDateToInt(dataArray[index0])
                        <= parseDateToInt(dateTo)) {
                    income = income + Integer.parseInt(dataArray[index3])
                            * Integer.parseInt(dataArray[index2]);
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(income);
        }
        return builder.toString();
    }

    private int parseDateToInt(String date) {
        String[] dateArray = date.split("\\.");
        return Integer.parseInt(dateArray[2] + dateArray[1] + dateArray[0]);
    }
}
