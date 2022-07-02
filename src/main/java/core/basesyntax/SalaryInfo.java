package core.basesyntax;

public class SalaryInfo {
    private static final int DATA_TO_CHECK = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder builder = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");
        for (int i = 0; i < names.length; i++) {
            int totalSalaryOfAPerson = 0;
            for (int j = 0; j < data.length; j++) {
                String[] oneLineOfData = data[j].split(" ");
                if (!oneLineOfData[NAME].equals(names[i])) {
                    continue;
                }
                if (isDataRelevant(oneLineOfData[DATA_TO_CHECK], dateFrom, dateTo)) {
                    totalSalaryOfAPerson += Integer.parseInt(oneLineOfData[HOURS])
                            * Integer.parseInt(oneLineOfData[INCOME_PER_HOUR]);
                }
            }
            builder.append(names[i]).append(" - ").append(totalSalaryOfAPerson).append("\n");
        }
        return builder.toString();
    }

    private boolean isDataRelevant(String dataToCheck, String dataFrom, String dataTo) {
        String[] splitedDataToCheck = dataToCheck.split(".");
        String[] splitedDataFrom = dataFrom.split(".");
        String[] splitedDataTo = dataTo.split(".");
        if (Integer.parseInt(splitedDataToCheck[2]) < Integer.parseInt(splitedDataFrom[2])
                || Integer.parseInt(splitedDataToCheck[2]) > Integer.parseInt(splitedDataTo[2])
                || Integer.parseInt(splitedDataToCheck[1]) < Integer.parseInt(splitedDataFrom[1])
                || Integer.parseInt(splitedDataToCheck[1]) > Integer.parseInt(splitedDataFrom[1])
                || Integer.parseInt(splitedDataToCheck[0]) < Integer.parseInt(splitedDataFrom[0])
                || Integer.parseInt(splitedDataToCheck[0]) > Integer.parseInt(splitedDataFrom[0])) {
            return false;
        }
        return true;
    }
}
