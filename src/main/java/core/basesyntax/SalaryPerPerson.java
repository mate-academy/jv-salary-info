package core.basesyntax;

public class SalaryPerPerson {
    public String[] getSalaryPerPerson(String[] names, String[] daysFromTheRange) {

        String[] personTotalMoney = new String[names.length];

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int totalMoney = 0;

            for (String dayFromTheRange : daysFromTheRange) {
                String[] elements = dayFromTheRange.split(" ");
                if (elements.length < 4) {
                    continue;
                }

                String nameFromArray = elements[1];
                int hours = Integer.parseInt(elements[2]);
                int moneyPerHour = Integer.parseInt(elements[3]);

                if (nameFromArray.equals(name)) {
                    totalMoney += hours * moneyPerHour;
                }
            }

            personTotalMoney[i] = name + " - " + totalMoney;
        }

        return personTotalMoney;
    }
}
