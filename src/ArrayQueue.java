import org.junit.Test;

import java.util.Scanner;

/**
 * @author hjw
 * @create 2020-09-04 14:16
 */

public class ArrayQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue queue = new Queue(3);


        char q;
        boolean flag = true;
        while (flag) {
            System.out.println("'a':添加数据");
            System.out.println("'h':获取队列头数据");
            System.out.println("'g':获取数据");
            System.out.println("'s':展示队列");
            System.out.println("'e':退出");
            q = scanner.next().charAt(0);

            switch (q) {
                case 'a':
                    System.out.println("请输入一个数字");
                    queue.add(scanner.nextInt());
                    break;
                case 'g':
                    System.out.println("请输入一数字");

                    try {
                        System.out.println("取出的数据是："+queue.get());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 's':

                    queue.show();
                    break;

                case 'h':
                    try {
                        System.out.println(queue.headQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();

                    flag = false;
                    break;
                default:
                    break;
            }
        }
    }
    @Test
    public void test(){
        int a=4;

        int b=a;
        b=0;
        System.out.println(a);
        System.out.println(b);
//        while (true){
//            System.out.println(b);
//            b++;
//            if (b==a){
//                break;
//            }
//        }
//        System.out.println("fgf");
    }





    }


class Queue{

    private int front ;
    private int rear ;
    private int maxSize ;
    private int [] arr;

    public Queue(int size){
        this.front=-1;  //指向头部元素前一个
        this.rear=-1;   //指向尾部最后一个元素
        this.maxSize=size;
        arr= new int[maxSize];
    }

    public boolean isEmpty(){
        return front==rear;
    }

    public boolean isFull(){
        return maxSize-1 == rear;
    }

    public void add(int a){
        if (isFull()){
            System.out.println("已满");
            return;
        }
        arr[++rear] = a;
    }

    public int get(){
        if (isEmpty()){
            throw new RuntimeException("为空");
        }

        return arr[++front];
    }

    public void show(){
        if (isEmpty()){
            System.out.println("队列空");
            return ; //void才能return,否则手动抛异常
        }
        for (int i : arr) {
            System.out.println(i+"  ");
        }
    }
    //显示队列头数据
    public int headQueue() {
        if (isEmpty()) {

            throw new RuntimeException("为空");
        }
        return arr[front+1];
    }

}