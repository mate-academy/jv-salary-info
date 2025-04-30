package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder().append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            String tempName = names[i];
            int sum = 0;
            for (String personInfo :data) {
                String[] tempData = personInfo.split(" ");
                if (tempName.equals(tempData[1])) {
                    if (convertDateToInt(tempData[0]) >= convertDateToInt(dateFrom)
                            && convertDateToInt(tempData[0]) <= convertDateToInt(dateTo)) {
                        sum += Integer.parseInt(tempData[2]) * Integer.parseInt(tempData[3]);
                    }
                }
            }
            result.append("\n").append(tempName).append(" - ").append(sum);
        }

        return result.toString();
    }

    private int convertDateToInt(String date) {
        String[] parts = date.split("\\.");
        return Integer.parseInt(parts[2] + parts[1] + parts[0]);
    }
}
