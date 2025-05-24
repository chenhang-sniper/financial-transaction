package com.financial.transaction.dao.memory;

import java.time.LocalDateTime;

/**
 * MemoryEntity类是内存数据库中的实体类
 * 它代表了一个具有主键和时间戳的数据项
 */
public class MemoryEntity {
    private Long id;//主键

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private LocalDateTime timestamp;//时间戳
}
