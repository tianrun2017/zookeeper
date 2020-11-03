package toby.test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.AddWatchMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
/**
 * 描述
 *
 * @author fanqinhai
 * @since 2020/7/4 5:53 下午
 */
public class ZkTest {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 30 * 1000, new Watcher() {

            @Override
            public void process(WatchedEvent event) {

                System.out.println("来了");

            }
        });

        zooKeeper.getData("/test", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("getData======"+event);
            }
        }, null);

        zooKeeper.addWatch("/test", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("addWatch====="+event);
            }
        }, AddWatchMode.PERSISTENT);

        new CountDownLatch(1).await();


    }
}
