package by.it.poznyakbogdan.lesson02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class C_GreedyKnapsack {
    private class Item implements Comparable<Item> {
        int cost;
        int weight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Item o) {
            double rel = this.cost / this.weight;
            double o_rel = o.cost / o.weight;
            if (rel > o_rel){
                return 1;
            }else if(rel < o_rel){
                return -1;
            }else {
                return 0;
            }
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      //сколько предметов в файле
        int W = input.nextInt();      //какой вес у рюкзака
        Item[] items = new Item[n];   //получим список предметов
        for (int i = 0; i < n; i++) { //создавая каждый конструктором
            items[i] = new Item(input.nextInt(), input.nextInt());
        }
        //покажем предметы
        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);

        //тут необходимо реализовать решение задачи
        //итогом является максимально воможная стоимость вещей в рюкзаке
        //вещи можно резать на кусочки (непрерывный рюкзак)
        double result = 0;
        //тут реализуйте алгоритм сбора рюкзака
        //будет особенно хорошо, если с собственной сортировкой
        //ваше решение. кроме того, можете описать свой компаратор в классе Item

        for (int i = 0; i < items.length ; i++){
            Item comp;
            for (int j = 0; j < items.length; j++){
                if (items[i].compareTo(items[j]) == 1){
                    comp = items[j];
                    items[j] = items[i];
                    items[i] = comp;
                }
            }
        }

        for (int i = 0; i < items.length; i++){
            System.out.println(items[i]);
        }
        boolean flag = true;
        int k = 0;
        while (W != 0){
            int cost = items[k].cost;
            int weight = items[k].weight;
            double rel = cost / weight;
            if(weight > W){
                result = result + (rel * W);
                W = 0;
            }else {
                W = W - weight;
                result = result + cost;
            }
            System.out.println(W + " | " + result);
            k++;
        }
        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/poznyakbogdan/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}