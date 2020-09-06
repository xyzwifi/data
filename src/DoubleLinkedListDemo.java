import java.util.Stack;

/**
 * @author hjw
 * @create 2020-09-04 23:33
 */
public class DoubleLinkedListDemo{
    public static void main(String[] args) {
        LinkedListDouble list = new LinkedListDouble();

        HeroNode2 node1 = new HeroNode2(1, "宋江");
        HeroNode2 node2= new HeroNode2(2, "卢俊义");
        HeroNode2 node3 = new HeroNode2(3, "吴用");

        list.addByOrder(node3);
        list.addByOrder(node1);
        list.addByOrder(node2);

        list.list();

//        System.out.println("修改后");
//
//        list.update(new HeroNode2(5, "jaja"));
//        list.list();


//        System.out.println("刪除后");
//        list.dle(2);
//
//        list.dle(2);
//        list.dle(1);
//        list.list();
//
//        System.out.println("獲取一個");
//
//        list.get(3);

//        //获取有效个数
//        System.out.println(LinkedListDouble.getSum(list.getHead()));
//
//        //获取倒数第k个
//        System.out.println(LinkedListDouble.reverseNode(0, list.getHead()));

//        System.out.println("反转后");
        //反转单链表（腾讯题）
//        LinkedListDouble.reverse(list.getHead());

//        list.list();
    }
}

//定义链表
class LinkedListDouble{
    

    //获取头节点******************************************************************
    public HeroNode2 getHead() {
        return head;
    }


    //头节点不存放具体信息
    HeroNode2 head = new HeroNode2(0,null);

   //按id修改******************************************************************
   public void update(HeroNode2 node){

       if (head.next==null){
           System.out.println("链表为空");
           return;
       }

       HeroNode2 temp=head.next;

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

       //直接找要删除的
       HeroNode2 temp=head.next;
       while (true){

            //判斷是否空要在前面。否則後續的可能空指針********
           if (temp == null){
               //遍历到底
               System.out.println("没有匹配的");
               break;
           }

           if (temp.id == id){
               //要删除的没有被引用。会被GVC回收
               temp.pre.next=temp.next;

               //若要删除的是最后一个，则要判断，否则空指针
               if (temp.next !=null){
               temp.next.pre=temp.pre;

               break;
           }}
           temp=temp.next;
       }
   }

    //按指定顺序插入******************************************************************
    public void addByOrder(HeroNode2 node) {
        //因为是单向链表，所以temp从头开始
        HeroNode2 temp=head;
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
            node.pre=temp;


            temp.next=node;
            //判断添加的节点位置时候有后续节点
            if (node.next != null){
                node.next.pre=node;
            }
        }
    }


    //添加元素(往最后添加)******************************************************************
    public void add(HeroNode2 node) {
        //遍历找到最后节点

        //指向同一个地址值
        HeroNode2 temp=head;
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
        node.pre=temp;
    }

    //遍历链表******************************************************************
    public void list(){
        HeroNode2 temp=head.next;

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
        HeroNode2 temp=head.next;

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

class HeroNode2{
    public int id ;
    
    public String name ;

    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "id=" + id +
                ", name=" + name+'}';
    }
}