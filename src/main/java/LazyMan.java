public class LazyMan {

    //懒汉式单例模式
    //比较懒，在类加载时，不创建实例，因此类加载速度快，但运行时获取对象的速度慢

    private static LazyMan intance = null; //静态私用成员，没有初始化

    private LazyMan(){
        //私有构造函数
    }

    public static synchronized LazyMan getInstance(){ //静态，同步，公开访问点
        if(intance == null){
            intance = new LazyMan();
        }
        return intance;
    }

    public static void main(String[] args) {
        //测试代码，验证50个线程去获取单例是不是一个实例
        for (int i = 0; i < 50; i++) {
            new Thread(() -> System.out.println(LazyMan.getInstance().hashCode())).start();
        }
    }
}

