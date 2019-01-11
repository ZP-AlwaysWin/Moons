package actor;



/**
 * 简单本地发送异步消息的类
 * @author Moons
 *
 */
public class Local implements CallBack,Runnable{

    /**
     * 远程接收消息的类，模拟point-to-point
     */
    private Remote remote;

    /**
     * 发送出去的消息
     */
    private String message;

    public Local(Remote remote, String message) {
        super();
        this.remote = remote;
        this.message = message;
    }

    /**
     * 发送消息
     */
    public void sendMessage()
    {
        /**当前线程的名称**/
        System.out.println(Thread.currentThread().getName());
        /**创建一个新的线程发送消息**/
        Thread thread = new Thread(this);
        thread.start();
        /**当前线程继续执行**/
        System.out.println("Message has been sent by Local~!");
    }

    /**
     * 发送消息后的回调函数
     */
    public void execute(Object... objects ) {
        /**打印返回的消息**/
        System.out.println(objects[0]);
        /**打印发送消息的线程名称**/
        System.out.println(Thread.currentThread().getName());
        /**中断发送消息的线程**/
        Thread.interrupted();
    }

    public static void main(String[] args)
    {
        Local local = new Local(new Remote(),"Hello");

        local.sendMessage();
    }

    public void run() {
        System.out.println("Can you come in ?");
        remote.executeMessage(message, this);
//        注意Local类中该行：
//        remote.executeMessage(message, this);
//
//        executeMessage方法需要接收一个message参数，表示发送出去的消息，而CallBack参数是他自己，也就是这里的this。表示发送消息后，由Local类自己来处理，调用自身的execute方法来处理消息结果。
//
//        如果这里不是用this，而是用其他的CallBack接口的实现类的话，那就不能称之为“回调”了，在OO的世界里，那就属于“委派”。也就是说，“回调”必须是消息的发送者来处理消息结果，否则不能称之为回调。这个概念必须明确。

    }
}
