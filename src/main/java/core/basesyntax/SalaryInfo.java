package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    /**
     * Реализуйте метод getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     *
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019
     *
     * names:
     * Сергей
     * Андрей
     * София
     *
     * data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     *
     * Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] strDateFrom = dateFrom.replaceAll("[^0-9]+", " ").split(" ");
        String[] strDateTo = dateTo.replaceAll("[^0-9]+", " ").split(" ");
        String[] strDateCheck;
        int[] numDateFrom = new int[strDateFrom.length];
        int[] numDateTo = new int[strDateTo.length];
        int[] numDateCheck = new int[strDateTo.length];

        for (int i = 0; i < strDateFrom.length; i++) {
            numDateFrom[i] = Integer.parseInt(strDateFrom[i]);
            numDateTo[i] = Integer.parseInt(strDateTo[i]);
        }
        if (LocalDate.of(numDateFrom[2], numDateFrom[1], numDateFrom[0])
                .isAfter(LocalDate.of(numDateTo[2], numDateTo[1], numDateTo[0]))) {
            return null;
        }

        String[][] dataName = new String[data.length][];
        int[] salary = new int[names.length];
        for (int i = 0; i < data.length; i++) {
            dataName[i] = data[i].split(" ");
        }
        for (int i = 0; i < dataName.length; i++) {
            strDateCheck = dataName[i][0].replaceAll("[^0-9]+", " ").split(" ");
            for (int j = 0; j < strDateFrom.length; j++) {
                numDateCheck[j] = Integer.parseInt(strDateCheck[j]);
            }
            if (LocalDate.of(numDateCheck[2], numDateCheck[1], numDateCheck[0])
                    .isBefore(LocalDate.of(numDateFrom[2], numDateFrom[1], numDateFrom[0]))
                    || LocalDate.of(numDateTo[2], numDateTo[1], numDateTo[0])
                    .isBefore(LocalDate.of(numDateCheck[2], numDateCheck[1], numDateCheck[0]))) {
                break;
            }
            for (int j = 0; j < names.length; j++) {
                if (names[j].equals(dataName[i][1])) {
                    salary[j] += Integer.parseInt(dataName[i][2])
                            * Integer.parseInt(dataName[i][3]);
                }
            }

        }
        String result = "Отчёт за период " + dateFrom + " - " + dateTo + "\n";
        for (int i = 0; i < names.length; i++) {
            result = result + names[i] + " - " + salary[i] + "\n";
        }
        return result;
    }
}
