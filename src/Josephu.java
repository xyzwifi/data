import jdk.nashorn.internal.runtime.linker.LinkerCallSite;

/**
 * @author hjw
 * @create 2020-09-06 14:34
 */
public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.add(5);
        list.show();
        System.out.println("出队后");
        list.out(1,2,5); //编号1开始，每次数2下，共5个编号    2-4-1-5-3
    }
}

//创建环形单向链表
class CircleSingleLinkedList{
    private Boy first = null ;

    //添加*************************************************************************************
    public void add(int nums){
        //nums数据校验

        if (nums <1){
            System.out.println("输入有误");
            return;
        }

        Boy curBoy=null; //辅助指针
        for (int i = 1; i <= nums ; i++) {
            //根据编号创建节点
            Boy boy = new Boy(i);
            if (i == 1){
                first = boy;
                first.setNext(first);//成环
                curBoy=first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;
            }
        }
    }

    //出圈*************************************************************************************
    /**
     *
     * @param startNo 从编号为startNo的人开始报数
     * @param count     数到count次
     * @param nums      （最初有几人）数据验证，防止编号过大，超出实际有效编号
     */
    public void out(int startNo,int count,int nums){
        //数据验证
        if (startNo <1 || startNo > nums || count <1 ||first==null){
            System.out.println("输入不合法");
            return;
        }

        //出队列前 先映射队列最后那个
        Boy last =first;
        while (true){
            if (last.getNext()==first){
                break;
            }
            last=last.getNext();
        }
        //报数前。把first移动到startNo编号，last同理
        for (int i=0; i < startNo-1;i++){
            first=first.getNext();
            last=last.getNext();
        }

        while (true) {
            //若只有一个。直接出圈后结束方法
            if (last==first){
                System.out.println(last);
                return;
            }

            //开始数数，把first，last移动count - 1次，firs到相应count节点
            for (int i = 0; i < count - 1; i++) {
                first = first.getNext();
                last = last.getNext();
            }
            System.out.println(first);
            first = first.getNext();
            last.setNext(first);
        }
    }

    //遍历*************************************************************************************
    public void show(){
        if (first == null){
            System.out.println("数据为空");
            return;
        }

        Boy temp= first;//first不能动

        while (true){
            System.out.println(temp);
            //打完一圈(先判断再后移。避免不必要的资源消耗)
            if (temp.getNext() == first){
                return;
            }

            temp=temp.getNext();
        }

    }

}

class Boy{
    private int no ;

    private Boy next ;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}