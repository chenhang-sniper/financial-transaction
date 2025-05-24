package com.financial.transaction.dao.repository;

import com.financial.transaction.dao.memory.MemoryEntity;
import com.financial.transaction.dao.memory.MemoryMapper;

import lombok.NonNull;

import java.io.Serializable;
import java.util.Optional;

/**
 * 抽象基础数据访问对象(DAO)类，提供了通用的CRUD操作
 * 它使用内存映射器（MemoryMapper）来执行实际的数据操作
 *
 * @param <Entity> 实体类类型，继承自MemoryEntity
 * @param <MAPPER> 映射器类型，继承自MemoryMapper
 */
public abstract class BaseDao<Entity extends MemoryEntity, MAPPER extends MemoryMapper<Entity>> implements IDao<Entity> {

    // 映射器实例，用于数据操作
    protected MemoryMapper memoryMapper;

    // 构造方法，初始化映射器
    public BaseDao() {
        this.memoryMapper = new MemoryMapper<Entity>();
    }

    /**
     * 根据ID查询实体
     *
     * @param id 实体的唯一标识符
     * @return 返回找到的实体对象，如果未找到则返回null
     */
    public Entity queryById(@NonNull Serializable id) {
        return (Entity) memoryMapper.findById((Long)id);
    }

    /**
     * 检查给定ID的实体是否存在
     *
     * @param id 实体的唯一标识符
     * @return 如果存在返回true，否则返回false
     */
    public boolean existsById(@NonNull Serializable id){
        return queryOptionalById(id).isPresent();
    }

    /**
     * 根据ID查询实体，返回一个Optional对象
     *
     * @param id 实体的唯一标识符
     * @return 返回一个包含实体的Optional对象，如果未找到则返回空的Optional
     */
    public Optional<Entity> queryOptionalById(@NonNull Serializable id) {
        return Optional.ofNullable(queryById(id));
    }

    /**
     * 插入一个新的实体
     *
     * @param model 要插入的实体对象
     * @return 插入成功返回true，否则返回false
     */
    public boolean insert(@NonNull Entity model) {
        return memoryMapper.save(model) != null;
    }

    /**
     * 根据ID更新实体
     *
     * @param model 要更新的实体对象
     * @return 更新成功返回true，否则返回false
     */
    public boolean updateById(@NonNull Entity model) {
        return memoryMapper.updateById(model) != null;
    }

    /**
     * 根据ID删除实体
     *
     * @param id 要删除的实体的唯一标识符
     * @return 删除成功返回true，否则返回false
     */
    public boolean deleteById(@NonNull Serializable id) {
        return memoryMapper.deleteById((Long)id);
    }

}
