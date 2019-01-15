import java.util.ArrayList;
import java.util.List;

//第一题，输入string和int k， 将string从后往前按照长度k分段，返回分段后的string list。

public class TestWord {

    public static String  reverseString (String str){
        StringBuilder stringBuffer = new StringBuilder (str);
        String revString = stringBuffer.reverse().toString();

        return revString;
    }
    public static void main (String args[]){
        String inputString= "Hello Alibaba Ant";
        int k = 3;
        List<String> listString = new ArrayList<>();
        //倒叙处理
        String revString= reverseString(inputString);
        //判断可以切成多少段
        int size = revString.length()/k;

        for (int i =0;i<=size;i++){
            if((i+1)*k>revString.length()){
                listString.add(revString.substring(i*k,revString.length()));
                break;
            }
            listString.add(revString.substring(i*k,(i+1)*k));
        }
        System.out.println(listString);
    }
}
