package Nova.ch21;
import java.sql.Time;
import java.util.concurrent.TimeUnit;
/*
* 二个线程共享变量
* 预期会出现指令重排
* 但是实际上并没有出现指令重排的现象。这个李林峰的书中可能有问题
* 但是如加volatile，一个线程修改之后，另外一个立即能看到
* */
public class ThreadStopExample {
    //private static boolean stop;
    private static volatile boolean stop;
    public static void main(String[] args) throws InterruptedException{
        java.lang.Thread workTread=new java.lang.Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (!stop){
                    i++;
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    }catch (InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        workTread.start();
        TimeUnit.SECONDS.sleep(3);
        stop=true;
    }
}
