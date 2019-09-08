package com.company;

public class Lesson5 {
    public static void main(String[] args) throws InterruptedException {
        Lesson5 l = new Lesson5();
        l.createArr();
        l.createRunnable();
    }

    public void createArr() {
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        long start;
        long finish;

        for (int i = 0; i <arr.length; i++){
            arr[i] = 1.0f;
        }
        start = System.currentTimeMillis();
        for(int i = 0; i < arr.length; i++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        finish = System.currentTimeMillis();
        System.out.println("Первый метод занимает " + (finish - start) + " мс.");
    }


    public void createRunnable() throws InterruptedException {
        long start;
        long finish;
        long time1, time2, time3, time4, time5;

        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];

        for (int i = 0; i <arr.length; i++){
            arr[i] = 1.0f;
        }

        float[] arr1 = new float[h];
        float[] arr2 = new float[h];

        start = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);
        time1 = System.currentTimeMillis();


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < arr1.length; i++){
                    arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        time2 = System.currentTimeMillis();
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < arr2.length; i++){
                    arr2[i] = (float)(arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        time4 = System.currentTimeMillis();
        thread2.start();
        //time3 = System.currentTimeMillis();


        thread1.join();
        time3 = System.currentTimeMillis();
        thread2.join();
        time5 = System.currentTimeMillis();
        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);

        finish = System.currentTimeMillis();


        System.out.println("Разбивка занимает " + (time1 - start) + " мс.");
        System.out.println("Время работы первого потока занимает: " + (time3 - time2));
        System.out.println("Время работы второго потока занимает: " + (time5 - time4));
        System.out.println("Cклейка занимает " + (finish - time5) + " мс.");
        System.out.println("Второй метод занимает " + (finish - start) + " мс.");

    }
}
