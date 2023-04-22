package core.basesyntax;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append("\n");
        int sum = 0;
        for (int i = 0; i < names.length; i++) {
            for (int b = 0; b < data.length; b++) {
                if (names[i].equals(getNameFromData(data[b]))) {
                    String dataFromArray = data[b].substring(0, data[b].indexOf(" "));
                    if (compareDates(dateFrom, dataFromArray, dateTo)) {
                        sum += getSalary(data[b]);
                    }
                }
            }
            builder.append(names[i]).append(" - ").append(sum).append("\n");
            sum = 0;
        }
        return builder.toString();
    }

    public boolean compareDates (String dateFrom, String dateFromArray, String dateTo) {
        int dayFirstData = Integer.parseInt(dateFrom.substring(0, 2));
        int monthFirstData = Integer.parseInt(dateFrom.substring(3, 5));
        int yearFirstData = Integer.parseInt(dateFrom.substring(6, 10));
        int daySecondData = Integer.parseInt(dateFromArray.substring(0, 2));
        int monthSecondData = Integer.parseInt(dateFromArray.substring(3, 5));
        int yearSecondData = Integer.parseInt(dateFromArray.substring(6, 10));
        int dayThirdData = Integer.parseInt(dateTo.substring(0, 2));
        int monthThirdData = Integer.parseInt(dateTo.substring(3, 5));
        int yearThirdData = Integer.parseInt(dateTo.substring(6, 10));

        if (yearFirstData <= yearSecondData && monthFirstData <= monthSecondData && dayFirstData <= daySecondData) {
            if (yearSecondData <= yearThirdData && monthSecondData <= monthThirdData && daySecondData <= dayThirdData) {
                return true;
            }
        }
        return false;
    }

    public String getNameFromData(String name) {
        int index = name.indexOf(" ");
        String infoWithoutData = name.substring(index + 1);
        index = infoWithoutData.indexOf(" ");
        return infoWithoutData.substring(0, index);
    }

    public int getSalary(String data) {
        int index = data.indexOf(" ");
        String dataSubstring = data.substring(index + 1);
        index = dataSubstring.indexOf(" ");
        dataSubstring = dataSubstring.substring(index + 1);
        index = dataSubstring.indexOf(" ");
        int hour = Integer.parseInt(dataSubstring.substring(0, index));
        dataSubstring = dataSubstring.substring(index + 1);
        int rate = Integer.parseInt(dataSubstring);
        return hour * rate;

    }
}

