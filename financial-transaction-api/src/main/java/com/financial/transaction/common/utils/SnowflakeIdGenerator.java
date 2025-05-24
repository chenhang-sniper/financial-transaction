package com.financial.transaction.common.utils;

/**
 * SnowflakeIdGenerator is a class for generating unique IDs using the Snowflake algorithm.
 * The Snowflake algorithm is a distributed ID generation algorithm that produces a unique ID by combining the current timestamp, data center ID, worker ID, and sequence number.
 */
public class SnowflakeIdGenerator {
    private final long twepoch = 1288834974657L; // 开始时间戳（2021年11月1日）
    private final long workerIdBits = 5L; // 机器ID位数
    private final long datacenterIdBits = 5L; // 数据中心ID位数
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits); // 最大机器ID
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits); // 最大数据中心ID
    private final long sequenceBits = 12L; // 序列号位数
    private final long workerIdShift = sequenceBits; // 机器ID左移位数
    private final long datacenterIdShift = sequenceBits + workerIdBits; // 数据中心ID左移位数
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits; // 时间戳左移位数
    private final long sequenceMask = -1L ^ (-1L << sequenceBits); // 序列号掩码

    private long workerId;
    private long datacenterId;
    private long sequence = 0L; // 序列号
    private long lastTimestamp = -1L; // 上次时间戳

    public SnowflakeIdGenerator() {
        this(0L, 0L);
    }

    public SnowflakeIdGenerator(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException("workerId cannot be greater than " + maxWorkerId + " or less than 0");
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId cannot be greater than " + maxDatacenterId + " or less than 0");
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(0, 0);
        for (int i = 0; i < 10; i++) {
            System.out.println(idGenerator.nextId());
        }
    }
}