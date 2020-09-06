



import java.io.*;



/**
 * @author hjw
 *
 */
public class SparseArray {

    public static void main(String[] args) {
        //1、创建二维数组
        int chess[][] = new int[11][11];

        //2、赋值,  1 黑棋；2白棋
        chess[1][2] = 1;
        chess[2][3] = 2;


        System.out.println("初始的二维数组");

        for (int[] ints : chess) {
            for (int i : ints) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }

        //4、遍历获取参数
        int count = 0; //棋盘有效个数(非0个数)
        for (int[] ints : chess) {
            for (int i : ints) {
                if (i != 0) {
                    count++;
                }
            }
        }


        //3、创建稀疏数组、赋值
        int sparse[][] = new int[count + 1][3];
        sparse[0][0] = chess.length;
        sparse[0][1] = 11;
        sparse[0][2] = count;


        int flag = 0; //计数器
        //遍历二维数组
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[i].length; j++) {
                if (chess[i][j] != 0) {
                    flag++;
                    sparse[flag][0] = i; //行
                    sparse[flag][1] = j; //列
                    sparse[flag][2] = chess[i][j];
                }
            }
        }

        System.out.println("初始的稀疏数组");
        for (int i = 0; i < sparse.length; i++) {
            for (int j = 0; j < sparse[i].length; j++) {
                System.out.print(sparse[i][j] + "\t");
            }
            System.out.println();
        }



        //***************稀疏数组写出磁盘*******************

        FileWriter writer = null;
        try {
            writer = new FileWriter(new File("sparse.txt"));
            for (int[] ints : sparse) {
                for (int i : ints) {
                    writer.write(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //***************磁盘写入稀疏数组*******************
        
        FileReader reader = null;
        StringBuffer buffer = new StringBuffer();
        try {
            reader = new FileReader(new File("sparse.txt"));
            char[] cbuf = new char[1024];
            int len;
            while ((len = reader.read(cbuf)) != -1) {
                buffer.append(cbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        String string = new String(buffer);

        int chess3[][] = new int[string.length()/ 3][3];


        int k=0; //计数器
        for (int i = 0; i < chess3.length; i++) {
            for (int j = 0; j < chess3[i].length; j++) {
                chess3[i][j]= string.charAt(k);
                k++;
            }
        }

System.out.println("磁盘复原的稀疏数组");
        for (int[] ints : chess3) {
            for (int i : ints) {
                System.out.print(i+"\t");
            }
            System.out.println();
        }



        //从稀疏数组恢复二维数组
        int chess2[][] = new int[chess3[0][0]][chess3[0][1]];
        //读取后几行
        for (int i = 1; i < chess3.length; i++) {

            chess2[chess3[i][0]][sparse[i][1]] = chess3[i][2];

        }


System.out.println("复原的二维数组");
        for (int[] ints : chess2) {
            for (int i : ints) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }



    }




}