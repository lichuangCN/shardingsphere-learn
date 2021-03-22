package site.muzhi.main;

import site.muzhi.ConsistentHash;
import site.muzhi.hash.IHashBuilder;
import site.muzhi.hash.MurMurHashBuilder;
import site.muzhi.node.ServerNode;

import java.util.*;

/**
 * @author lichuang
 * @date 2021/03/22
 * @description
 */
public class MainTest {
    // 机器节点IP前缀
    private static final String IP_PREFIX = "192.168.0.";

    public static void main(String[] args) {
        // 每台真实机器节点上保存的记录条数
        Map<String, Integer> map = new HashMap<>();

        // 真实机器节点, 模拟10台
        List<ServerNode<String>> nodes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            map.put(IP_PREFIX + i, 0); // 初始化记录
            ServerNode<String> node = new ServerNode<>(IP_PREFIX + i, "node" + i);
            nodes.add(node);
        }

        IHashBuilder hashBuilder = new MurMurHashBuilder();
        // 每台真实机器引入100个虚拟节点
        ConsistentHash<ServerNode<String>> consistentHash = new ConsistentHash<>(hashBuilder, 500, nodes);

        // 将5000条记录尽可能均匀的存储到10台机器节点上
        for (int i = 0; i < 5000; i++) {
            // 产生随机一个字符串当做一条记录，可以是其它更复杂的业务对象,比如随机字符串相当于对象的业务唯一标识
            String data = UUID.randomUUID().toString() + i;
            // 通过记录找到真实机器节点
            ServerNode<String> node = consistentHash.get(data);
            // 再这里可以能过其它工具将记录存储真实机器节点上，比如MemoryCache等
            // ...
            // 每台真实机器节点上保存的记录条数加1
            map.put(node.getIp(), map.get(node.getIp()) + 1);

            // 模拟设备中途新增节点
            if (i % 1000 == 0) {
                String ip = IP_PREFIX + (i / 1000 + 100);
                consistentHash.add(new ServerNode<String>(ip, "new_node" + i / 1000));
                map.put(ip, 0);
            }
        }

        // 打印每台真实机器节点保存的记录条数
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "节点记录条数：" + entry.getValue());
        }
    }
}
