package com.github.mishkaff89.practicumdev.training;

/**
 * Набор тренингов по работе с массивами в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see ArraysTrainingTest.
 */
public class ArraysTraining {

    /**
     * Метод должен сортировать входящий массив
     * по возрастранию пузырьковым методом
     *
     * @param valuesArray массив для сортировки
     * @return отсортированный массив
     */
    public int[] sort(int[] valuesArray) {
        for (int i = 0; i < valuesArray.length - 1; i++) {
            for (int j = 0; j < valuesArray.length - i - 1; j++) {
                if (valuesArray[j + 1] < valuesArray[j]) {
                    int swap = valuesArray[j];
                    valuesArray[j] = valuesArray[j + 1];
                    valuesArray[j + 1] = swap;
                }
            }
        }
        return valuesArray;
    }

    /**
     * Метод должен возвращать максимальное
     * значение из введенных. Если входящие числа
     * отсутствуют - вернуть 0
     *
     * @param values входящие числа
     * @return максимальное число или 0
     */
    public int maxValue(int... values) {
        int maxValue = 0;
        if (values.length < 1){
            return 0;
        } else {
            for (int i = 0; i<values.length; i++){
                if(values[i]>maxValue){
                    maxValue = values[i];
                }
            }
        }
        return maxValue;
    }

    /**
     * Переставить элементы массива
     * в обратном порядке
     *
     * @param array массив для преобразования
     * @return входящий массив в обратном порядке
     */
    public int[] reverse(int[] array) {
        int[] reversArray = new int[array.length];
        int j = array.length;
        for (int i = 0; i < array.length; i++) {
            reversArray[j - 1] = array[i];
            j = j - 1;

        }
        return reversArray;
    }

    /**
     * Метод должен вернуть массив,
     * состоящий из чисел Фибоначчи
     *
     * @param numbersCount количество чисел Фибоначчи,
     *                     требуемое в исходящем массиве.
     *                     Если numbersCount < 1, исходный
     *                     массив должен быть пуст.
     * @return массив из чисел Фибоначчи
     */
    public int[] fibonacciNumbers(int numbersCount) {
        if (numbersCount < 1){
            return new int[]{};
        } else {
            int num1 = 0, num2 = 1;
            int counter = 0;
            int[] array = new int[numbersCount];
            while (counter < numbersCount) {
                int num3 = num2 + num1;
                array[counter] = num1;
                num1 = num2;
                num2 = num3;
                counter = counter + 1;
            }
            return array;
        }
    }

    /**
     * В данном массиве найти максимальное
     * количество одинаковых элементов.
     *
     * @param array массив для выборки
     * @return количество максимально встречающихся
     * элементов
     */
    public int maxCountSymbol(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j + 1] < array[j]) {
                    int swap = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = swap;
                }
            }
        }
        int maxCount = 0;
        int tempMaxCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (i == 0 || array[i] == array[i - 1]) {
                tempMaxCount++;
            } else {
                if (tempMaxCount > maxCount) {
                    maxCount = tempMaxCount;
                }
                tempMaxCount = 1;
            }
        }
        if (tempMaxCount > maxCount)
            maxCount = tempMaxCount;

        return maxCount;
    }
}
