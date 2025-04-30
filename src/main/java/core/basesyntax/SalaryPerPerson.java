package core.basesyntax;

public class SalaryPerPerson {
    public String[] getSalaryPerPerson(String[] names, String[] daysFromTheRange) {

        String[] personTotalMoney = new String[names.length];
        StringBuilder personToString = new StringBuilder();

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int totalMoney = 0;

            for (String dayFromTheRange : daysFromTheRange) {
                String[] elements = dayFromTheRange.split(" ");
                if (elements.length < 4) {
                    System.out.println("Błąd formatu danych: " + dayFromTheRange);
                    continue;
                }

                String nameFromArray = elements[1];
                int hours = Integer.parseInt(elements[2]);
                int moneyPerHour = Integer.parseInt(elements[3]);

                try {
                    hours = Integer.parseInt(elements[2]);
                    moneyPerHour = Integer.parseInt(elements[3]);
                } catch (NumberFormatException e) {
                    System.out.println("Number format error. Invalid data: "
                            + elements[2] + " " + elements[3]);
                    continue;
                }

                if (nameFromArray.equals(name)) {
                    totalMoney += hours * moneyPerHour;
                }
            }

            personToString.append(name).append(" - ").append(totalMoney);
            personTotalMoney[i] = personToString.toString();
        }

        if (personTotalMoney.length == 0) {
            System.out.println("Brak danych do wyświetlenia.");
        }

        return personTotalMoney;
    }
}
