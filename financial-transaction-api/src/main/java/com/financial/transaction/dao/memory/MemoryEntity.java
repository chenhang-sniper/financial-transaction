package com.financial.transaction.dao.memory;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * MemoryEntity类是内存数据库中的实体类
 * 它代表了一个具有主键和时间戳的数据项
 */
@Data
public class MemoryEntity {
    private Long id;//主键
    private LocalDateTime timestamp;//时间戳
}
