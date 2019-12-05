package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    /**
     * <p>Реализуйте метод getSalaryInfo(String[] names, String[] data,
     * String dateFrom, String dateTo)
     * вычисляющий зnод методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * Создать класс-ошибку IllegalDateParametersException и сделать так, чтобы
     * метод getSalaryInfo выбрасывал IllegalDateParametersException,
     * если dateFrom > dateTo, с сообщнием "Wrong parameters"</p>
     *
     * <p>Пример ввода: date from = 01.04.2019 date to = 30.04.2019</p>
     *
     * <p>names:
     * Сергей
     * Андрей
     * София</p>
     *
     * <p>data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50</p>
     *
     * <p>Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900</p>
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception, IllegalArgumentException {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateStart = LocalDate.parse(dateFrom, dateFormat);
        LocalDate dateEnd = LocalDate.parse(dateTo, dateFormat);

        if (dateStart.isAfter(dateEnd)) {
            throw new IllegalDateParametersException();
        }

        StringBuilder report = new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo);

        for (String workerName :
                names) {
            int sumOfMoney = 0;
            for (String dataLine :
                    data) {
                if (dataLine.contains(workerName)) {
                    String[] tempData = dataLine.split(" ");
                    LocalDate tempStart = LocalDate.parse(tempData[0], dateFormat);
                    if (isaCorrectDate(dateStart, dateEnd, tempStart)) {
                        sumOfMoney += Integer.parseInt(tempData[2]) * Integer.parseInt(tempData[3]);
                    }
                }
            }
            report.append("\n").append(workerName).append(" - ").append(sumOfMoney);
        }
        return String.valueOf(report.append("\n"));
    }

    private boolean isaCorrectDate(LocalDate dateStart, LocalDate dateEnd, LocalDate tempStart) {
        return tempStart.isBefore(dateEnd)
            && (tempStart.isAfter(dateStart)
            || tempStart.equals(dateStart))
            || tempStart.equals(dateEnd);
    }
}
