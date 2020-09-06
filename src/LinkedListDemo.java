import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.Stack;

/**
 * @author hjw
 * @create 2020-09-04 23:33
 */
public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        HeroNode node1 = new HeroNode(1, "宋江");
        HeroNode node2= new HeroNode(2, "卢俊义");
        HeroNode node3 = new HeroNode(3, "吴用");

        list.addByOrder(node1);
        list.addByOrder(node3);
        list.addByOrder(node2);

        list.list();

//        System.out.println("修改后");

//        list.update(new HeroNode(0, "jaja"));
//        list.list();


//        System.out.println("刪除后");
//        list.dle(1);
//
//        list.dle(2);
//        list.dle(1);
//        list.list();
//
//        System.out.println("獲取一個");
//
//        list.get(3);

//        //获取有效个数
//        System.out.println(LinkedList.getSum(list.getHead()));
//
//        //获取倒数第k个
//        System.out.println(LinkedList.reverseNode(0, list.getHead()));

        System.out.println("反转后");
        //反转单链表（腾讯题）
//        LinkedList.reverse(list.getHead());
        LinkedList.revStack(list.getHead());
//        list.list();
    }
}

//定义链表
class LinkedList{
    //利用stack实现反转，不会改变原有链表******************************************************************
public static void revStack(HeroNode head){
    //只有一個（頭）或只有2个，无需反转
    if (head.next==null || head.next.next == null){
        return;
    }

    Stack<HeroNode> stack = new Stack<>();
    HeroNode temp = head.next;
    while (temp !=null){
        stack.add(temp);
        temp= temp.next;
    }
    while(stack.size()>0){
        System.out.println(stack.pop());
    }
}

    //反轉數組******************************************************************
    //会改变原有链表
    public static void reverse(HeroNode head){
        //只有一個（頭）或只有2个，无需反转
        if (head.next==null || head.next.next == null){
            return;
        }
        HeroNode temp = head.next; //当前节点，辅助指针，遍历用
        HeroNode next;//辅助指针。临时保存当前节点【temp】的下一个节点
        HeroNode rev= new HeroNode(0, null);
       //遍历取出新节点，先把rev的next保存给temp的next，
        // 再把temp指向rev的next,
        while (temp != null){
            next=temp.next; // 临时保存当前节点的下一个节点
            temp.next=rev.next;
            rev.next=temp; //将遍历到的连在rev的最前面（next）
            temp=next;  //后移
        }
        //共同指向一个节点，一波操作后链表已被破坏，原head只剩下2个节点。
        head.next=rev.next;//把rev反转后的链表返回给head
    }

    //获取头节点******************************************************************
    public HeroNode getHead() {
        return head;
    }

//获取倒数第index个节点******************************************************************
    public static HeroNode reverseNode(int index,HeroNode head){
        //没有一个
        if (head.next == null){
            return null;
        }

        //1、遍历获取有效个数，
        // 2从第一个有效节点开始遍历第（ sum - index）个即找到
        int sum = getSum(head);

        //指针在第一个有效数据中
        HeroNode temp = head.next;
        //3、index校验（比如index超大，则直接返回第一个有效节点了）
        if (index <=0 || index > sum){
            return null;
        }
        for (int i = 0; i < sum - index; i++) {

            temp=temp.next;
        }
        return temp;
    }
//获取有效节点数(若有头节点。则不算头节点)******************************************************************
public static int getSum(HeroNode head){
        if (head.next == null){
            return 0;
        }
        int sum = 0;
        HeroNode temp = head.next;
        while (temp != null){
            sum ++ ;
            temp=temp.next;
        }
        return sum;
}


    //头节点不存放具体信息
    HeroNode head = new HeroNode(0,null);

   //按id修改******************************************************************
   public void update(HeroNode node){

       if (head.next==null){
           System.out.println("链表为空");
           return;
       }

       HeroNode temp=head.next;

       while (true){

           if (temp == null){
               //遍历到底
               System.out.println("没有匹配的");
               break;
           }

           if (temp.id == node.id){
               temp.name=node.name;
               return;
           }
           temp=temp.next;
       }
   }
   //按id刪除******************************************************************
   public void dle(int id){

       if (head.next==null){
           System.out.println("链表为空");
           return;
       }

       //找id的上一個
       HeroNode temp=head;
       while (true){

            //判斷是否空要在前面。否則後續的可能空指針********
           if (temp.next == null){
               //遍历到底
               System.out.println("没有匹配的");
               break;
           }

           if (temp.next.id == id){
               //要删除的没有被引用。会被GVC回收
               temp.next=temp.next.next;
               break;
           }
           temp=temp.next;
       }
   }

    //按指定顺序插入******************************************************************
    public void addByOrder(HeroNode node) {
        //因为是单向链表，所以temp从头开始
        HeroNode temp=head;
        boolean flag = true; //不同id
        while (true){
            if (temp.next==null){
                break; //即在链表最后
            }

            if (temp.next.id>node.id){
                //找到了，就在此temp后插入（temp后节点的值比参数的大）
                break;

            }else if (temp.next.id == node.id){
                System.out.println("该hero"+node.name+"已存在");
                flag=false;
                break;
            }

            temp=temp.next;//后移，遍历链表
        }

        if (flag){

            //找到相应位置
            node.next=temp.next;
            //参数节点指向当前节点的下一个节点
            temp.next=node; //当前节点指向参数节点

        }
    }


    //添加元素******************************************************************
    public void add(HeroNode node) {
        //遍历找到最后节点

        //指向同一个地址值
        HeroNode temp=head;
        while (true) {
            //判断有无下一个节点
            if (temp.next == null) {
                break;
            }
            //有下一节点，则临时变量后移
            temp=temp.next;
        }
        //结束循环，参数赋值为当前节点的next
        temp.next=node;
    }

    //遍历链表******************************************************************
    public void list(){
        HeroNode temp=head.next;

        //若头节点的next为空，打印、结束方法
        if (temp == null) {
            System.out.println("链表为空");
            return;
        }

        //有至少一个。循环遍历
        while (true) {
            if (temp == null) {
                return;
            }
            System.out.println(temp);
            //后移
            temp=temp.next;
        }
    }
    //******************************************************************
    public void get(int id){
        HeroNode temp=head.next;

        //若头节点的next为空，打印、结束方法
        if (temp == null) {
            System.out.println("链表为空");
            return;
        }

        //有至少一个。循环遍历
        while (true) {
            if (temp == null) {
                System.out.println("不存在");
                return;
            }
                if (temp.id == id ){
                    System.out.println(temp);
                    return;
                }
            temp=temp.next;
        }

        }

}

class HeroNode{
    public int id ;
    
    public String name ;

    public HeroNode next;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name=" + name+'}';
    }
}