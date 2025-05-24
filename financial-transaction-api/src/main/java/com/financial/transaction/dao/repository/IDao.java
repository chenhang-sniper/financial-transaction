package com.financial.transaction.dao.repository;

import java.io.Serializable;
import java.util.Optional;

import lombok.NonNull;

/**
 * 数据访问对象（DAO）接口，定义了对实体进行基本的增删查改操作的方法
 * @param <Entity> 实体类的类型
 */
public interface IDao<Entity> {

    /**
     * 根据ID查询实体对象
     * @param id 实体的唯一标识符
     * @return 返回查询到的实体对象，如果不存在则返回null
     */
    Entity queryById(@NonNull Serializable id);

    /**
     * 根据ID查询实体对象，返回一个Optional对象
     * @param id 实体的唯一标识符
     * @return 返回一个包含查询到的实体对象的Optional，如果不存在则返回Optional.empty()
     */
    Optional<Entity> queryOptionalById(@NonNull Serializable id);

    /**
     * 插入一个新的实体对象到数据库中
     * @param model 要插入的实体对象
     * @return 返回插入操作是否成功
     */
    boolean insert(@NonNull Entity model);

    /**
     * 根据ID更新实体对象
     * @param model 包含更新数据的实体对象
     * @return 返回更新操作是否成功
     */
    boolean updateById(@NonNull Entity model);

    /**
     * 根据ID删除实体对象
     * @param id 要删除的实体的唯一标识符
     * @return 返回删除操作是否成功
     */
    boolean deleteById(@NonNull Serializable id);

    /**
     * 根据ID检查实体对象是否存在
     * @param id 要检查的实体的唯一标识符
     * @return 返回实体是否存在
     */
    boolean existsById(@NonNull Serializable id);
}