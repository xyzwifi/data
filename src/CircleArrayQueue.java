import java.util.Scanner;

/**
 * @author hjw
 * @create 2020-09-04 20:41
 */
public class CircleArrayQueue {
    public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
        CircleQueue queue = new CircleQueue(4);//有效数据是3个。最后一数据后面空一位

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
    }
class CircleQueue{

    private int front ;//指向队列第一个元素
    private int rear ;    //指向队列最后一个元素的下一个位置

    private int maxSize ;
    private int [] arr;

    public CircleQueue(int size){

        this.maxSize=size;
        arr= new int[maxSize];
    }

    public boolean isEmpty(){
        return front==rear;
    }

    public boolean isFull(){
        return (rear+1)%maxSize== front;
    }

    public void add(int a){
        if (isFull()){
            System.out.println("已满");
            return;
        }
        //可能轮圈。需要考虑取余
        arr[rear] = a;
        rear=(rear+1) % maxSize;
    }


    public int get(){
        if (isEmpty()){
            throw new RuntimeException("为空");
        }

             int var= arr[front];
        //可能轮圈。需要考虑取余
           front=(front+1) % maxSize;
          return var;
    }

    public void show(){
        if (isEmpty()){
            System.out.println("队列空");
            return ; //void才能return,否则手动抛异常
        }
        //从front开始。遍历 有效个数据
        for (int i = front; i < front + (rear+maxSize-front) % maxSize; i++) {

            System.out.println("["+i %maxSize +"]:" + arr[i%maxSize]);
        }
    }

    //显示队列头数据
    public int headQueue() {
        if (isEmpty()) {

            throw new RuntimeException("为空");
        }
        return arr[front];
    }


}
