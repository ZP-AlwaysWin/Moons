public class HungryMan {

    //饿汉单例模式
    //在类加载时就完成了初始化，所以类加载较慢，但获取对象的速度快

    private static HungryMan instance = new HungryMan(); //静态私有成员，已初始化

    private HungryMan() {
        //私有构造函数
    }

    public static HungryMan getInstance() {  //静态，不用同步（类加载时已初始化，不会有多线程的问题）
        return instance;
    }

    public static void main(String[] args) {


        for (int i = 1; i < 50; i++) {
            new Thread() {
                public void run() {
                    System.out.println(getInstance().hashCode());
                }
            }.start();
        }

    }
}



