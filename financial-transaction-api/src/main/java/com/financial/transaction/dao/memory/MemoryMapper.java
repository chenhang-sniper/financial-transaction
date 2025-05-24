package com.financial.transaction.dao.memory;

import com.financial.transaction.dao.pojo.Transaction;
import lombok.NonNull;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 内存映射器，用于处理内存中的实体数据操作
 * 该类提供了基本的CRUD操作，包括保存、删除、查询和更新实体
 * 使用并发数据结构确保线程安全
 *
 * @param <Entity> 继承了MemoryEntity的实体类型
 */
public class MemoryMapper<Entity extends MemoryEntity> {
    // ID生成器（线程安全）
    private final AtomicLong idGenerator = new AtomicLong(0);

    // 索引存储：按ID快速查找（线程安全）
    private final ConcurrentMap<Long, Entity> indexMapping = new ConcurrentHashMap<>();

    /**
     * 保存一个实体如果实体的ID为空，则生成一个新的ID
     * 并将当前时间戳设置为实体的时间戳
     *
     * @param model 要保存的实体
     * @return 保存后的实体
     */
    public Entity save(Entity model) {
        if (model.getId() == null) {
            //自增
            model.setId(idGenerator.incrementAndGet());
        }
        model.setTimestamp(LocalDateTime.now());
        indexMapping.put(model.getId(), model);
        return model;
    }

    /**
     * 根据ID删除实体
     *
     * @param id 要删除的实体的ID
     * @return 删除操作是否成功
     */
    public boolean deleteById(Long id) {
        Entity removed = indexMapping.remove(id);
        return true;
    }

    /**
     * 根据ID查找实体
     *
     * @param id 要查找的实体的ID
     * @return 对应ID的实体，如果不存在则返回null
     */
    public Entity findById(Long id) {
        return indexMapping.get(id);
    }

    /**
     * 根据ID更新实体
     *
     * @param model 要更新的实体
     * @return 更新前的实体，如果不存在则返回null
     */
    public Entity updateById(@NonNull Entity model) {
        Entity existing = indexMapping.get(model.getId());
        if (existing == null) {
            return null;
        }

        // 更新数据
        model.setTimestamp(LocalDateTime.now());
        indexMapping.put(model.getId(), model);
        return existing;
    }

    /**
     * 查询所有实体列表
     *
     * @return 实体列表，如果为空则返回空列表
     */
    public List<Transaction> queryList() {
        if(CollectionUtils.isEmpty(indexMapping)){
            return Collections.emptyList();
        }
        List<Transaction> list = (List<Transaction>) indexMapping.values().stream().toList();
        return list;
    }
}
