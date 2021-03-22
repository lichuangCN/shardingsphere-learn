package site.muzhi;

import site.muzhi.hash.IHashBuilder;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author lichuang
 * @date 2021/03/22
 * @description 一致性hash
 */
public class ConsistentHash<T> {
    private final IHashBuilder hashBuilder;
    /**
     * 每台真实服务映射的虚拟节点数
     */
    private final int numberOfReplicates;
    /**
     * 环形虚拟节点
     */
    private final TreeMap<Long, T> circle = new TreeMap<>();

    public ConsistentHash(IHashBuilder hashBuilder, int numberOfReplicates, Collection<T> nodes) {
        this.hashBuilder = hashBuilder;
        this.numberOfReplicates = numberOfReplicates;
        for (T node : nodes) {
            add(node);
        }
    }

    /**
     * 增加真实节点
     *
     * @param node
     */
    public void add(T node) {
        // 真实节点node映射指定数目的虚拟节点
        for (int i = 0; i < numberOfReplicates; i++) {
            circle.put(hashBuilder.hash(node.toString() + i), node);
        }
    }

    /**
     * 删除真实节点
     *
     * @param node
     */
    public void del(T node) {
        // 删除与真实节点node映射关联的虚拟节点
        for (int i = 0; i < numberOfReplicates; i++) {
            circle.remove(hashBuilder.hash(node.toString() + i));
        }
    }

    public T get(String key) {
        if (circle.isEmpty()) {
            return null;
        }
        Long hash = hashBuilder.hash(key);

        // 顺时针寻找
        if (!circle.containsKey(hash)) {
            SortedMap<Long, T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }
}
