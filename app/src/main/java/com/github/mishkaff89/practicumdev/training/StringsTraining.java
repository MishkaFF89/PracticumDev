package com.github.mishkaff89.practicumdev.training;

import java.util.ArrayList;
import java.util.List;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see StringsTrainingTest.
 */
public class StringsTraining {

    /**
     * Метод по созданию строки,
     * состоящей из нечетных символов
     * входной строки в том же порядке
     * (нумерация символов идет с нуля)
     *
     * @param text строка для выборки
     * @return новая строка из нечетных
     * элементов строки text
     */
    public String getOddCharacterString(String text) {

        char[] array = text.toCharArray();

        int count = 0;

        String odd = "";
        for (char ch : array) {

            if (count % 2 != 0) {
                odd = odd + ch;
            }
            count++;
        }
        return odd;
    }

    /**
     * Метод для определения количества
     * символов, идентичных последнему
     * в данной строке
     *
     * @param text строка для выборки
     * @return массив с номерами символов,
     * идентичных последнему. Если таких нет,
     * вернуть пустой массив
     */
    public int[] getArrayLastSymbol(String text) {
        char[] chArray = text.toCharArray();
        char lastCh = chArray[chArray.length - 1];
        List<Integer> newArray = new ArrayList<>();
        for (int i = 0; i<chArray.length; i++){
            if (chArray[i] == lastCh){
                newArray.add(i);
            }
        }
        int[] ints = new int[newArray.size()];
        for (int i=0; i < ints.length; i++)
        {
            ints[i] = newArray.get(i);
        }

        if (ints.length == 1){
            return new int[]{};
        } else {
            return ints;
        }
    }

    /**
     * Метод по получению количества
     * цифр в строке
     *
     * @param text строка для выборки
     * @return количество цифр в строке
     */
    public int getNumbersCount(String text) {
        int digits = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 48 && text.charAt(i) <= 57)
                digits++;
        }
        return digits;
    }

    /**
     * Дан текст. Заменить все цифры
     * соответствующими словами.
     *
     * @param text текст для поиска и замены
     * @return текст, где цифры заменены словами
     */
    public String replaceAllNumbers(String text) {
        String newArray = text.replace("0","zero").replace("1","one")
                .replace("2","two").replace("3","three")
                .replace("4","four").replace("5","five")
                .replace("6","six").replace("7","seven")
                .replace("8","eight").replace("9","nine");
        return newArray;

    }

    /**
     * Метод должен заменить заглавные буквы
     * на прописные, а прописные на заглавные
     *
     * @param text строка для изменения
     * @return измененная строка
     */
    public String capitalReverse(String text) {
        char[] a = text.toCharArray();
        String newText = "";
        for (int i = 0; i<a.length; i++){
            if (Character.isLetter(a[i])){
                if (Character.isLowerCase(a[i])){
                    newText += Character.toUpperCase(a[i]);
                } else {
                    newText += Character.toLowerCase(a[i]);
                }
            } else {
                newText += a[i];
            }
        }
        return newText;
    }

}
