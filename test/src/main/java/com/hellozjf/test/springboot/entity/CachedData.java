package com.hellozjf.test.springboot.entity;

/**
 * @author Jingfeng Zhou
 */
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author amber2012
 *
 * 读写锁：ReadWriteLock
 *
 * 在多线程的环境下，对同一份数据进行读写，会涉及到线程安全的问题。比如在一个线程读取数据的时候，另外一个线程在
 * 写数据，而导致前后数据的不一致性；一个线程在写数据的时候，另一个线程也在写，同样也会导致线程前后看到的数据的
 * 不一致性。
 *
 * 这时候可以在读写方法中加入互斥锁，任何时候只能允许一个线程的一个读或写操作，而不允许其他线程的读或写操作，这
 * 样是可以解决这样以上的问题，但是效率却大打折扣了。因为在真实的业务场景中，一份数据，读取数据的操作次数通常高
 * 于写入数据的操作，而线程与线程间的读读操作是不涉及到线程安全的问题，没有必要加入互斥锁，只要在读-写，写-写期
 * 间上锁就行了。
 *
 * 对于这种情况，读写锁则最好的解决方案！
 *
 * 读写锁的机制：
 *      "读-读"不互斥
 *      "读-写"互斥
 *      "写-写"互斥
 *
 * 即在任何时候必须保证：
 *      只有一个线程在写入；
 *      线程正在读取的时候，写入操作等待；
 *      线程正在写入的时候，其他线程的写入操作和读取操作都要等待；
 *
 * 以下是一个缓存类：用于演示读写锁的操作：重入、降级
 */
public class CachedData {

    // 缓存都应该是单例的，在这里用单例模式设计：
    private static CachedData cachedData = new CachedData();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();//读写锁
    private Map<String, Object> cache = new HashMap<String, Object>();//缓存

    private CachedData(){
    }

    public static CachedData getInstance(){
        return cachedData;
    }

    // 读取缓存：
    public Object read(String key) {
        lock.readLock().lock();
        Object obj = null;
        try {
            obj = cache.get(key);
            if (obj == null) {
                lock.readLock().unlock();
                // 在这里的时候，其他的线程有可能获取到锁
                lock.writeLock().lock();
                try {
                    if (obj == null) {
                        obj = "查找数据库"; // 实际动作是查找数据库
                        // 把数据更新到缓存中：
                        cache.put(key, obj);
                    }
                } finally {
                    // 当前线程在获取到写锁的过程中，可以获取到读锁，这叫锁的重入，然后导致了写锁的降级，称为降级锁。
                    // 利用重入可以将写锁降级，但只能在当前线程保持的所有写入锁都已经释放后，才允许重入 reader使用
                    // 它们。所以在重入的过程中，其他的线程不会有获取到锁的机会（这样做的好处）。试想，先释放写锁，在
                    // 上读锁，这样做有什么弊端？--如果这样做，那么在释放写锁后，在得到读锁前，有可能被其他线程打断。
                    // 重入————>降级锁的步骤：先获取写入锁，然后获取读取锁，最后释放写入锁（重点）
                    lock.readLock().lock();
                    lock.writeLock().unlock();
                }
            }
        } finally {
            lock.readLock().unlock();
        }
        return obj;
    }
}