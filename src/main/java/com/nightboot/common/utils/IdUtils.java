package com.nightboot.common.utils;

import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 雪花算法ID生成器
 * @author xuss
 * @date 2021/6/9
 */
public class IdUtils {
    /**
     * 开始时间截 (2018-01-01)
     */
    private static final long twepoch = 1514736000000L;

    /**
     * 机器id所占的位数
     */
    private static final long workerIdBits = 5L;

    /**
     * 数据标识id所占的位数
     */
    private static final long datacenterIdBits = 5L;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 支持的最大数据标识id，结果是31
     */
    private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     * 序列在id中占的位数
     */
    private static final long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private static final long workerIdShift = sequenceBits;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private static final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间截向左移22位(5+5+12)
     */
    private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器ID(0~31)
     */
    public static long workerId;

    /**
     * 数据中心ID(0~31)
     */
    private static long datacenterId;

    /**
     * 毫秒内序列(0~4095)
     */
    private static long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private static long lastTimestamp = -1L;
// TODO 后期增加
//    /**
//     * 构造函数
//     * @param workerId 工作ID (0~31)
//     * @param datacenterId 数据中心ID (0~31)
//     */
//    static {
//        Long ip = IPUtils.ipToLong(IPUtils.getLocalIP());
//        datacenterId = ip >> 8 & maxDatacenterId;
//    }

    public static synchronized Long nextTimestamp() {
        long timestamp = timeGen();
        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
//            log.error("IdUtils.nextId timestamp < lastTimestamp:timestamp={},lastTimestamp={}", timestamp, lastTimestamp);
//            throw PlugException.INSTANCE(ErrorConstant.FAILURE, I18nConstant.FAILURE, "未知异常 !");
            throw new RuntimeException("当前系统时间戳小于ID最后生成时间戳!!!");
        }

        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        // 时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        // 上次生成ID的时间截
        lastTimestamp = timestamp;
        return timestamp;
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public static synchronized Long nextLongId() {
        // 上次生成ID的时间截
        Long timestamp = nextTimestamp();

        // 移位并通过或运算拼到一起组成64位的ID
        Long longId = ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
        return longId;
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public static synchronized String nextOrderId() {
        // 上次生成ID的时间截
        Long timestamp = nextTimestamp();

        // 移位并通过或运算拼到一起组成64位的ID
        Long longId = ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
        return LocalDateTime.ofEpochSecond(timestamp / 1000, 0, ZoneOffset.of("+8"))
                .format(DateTimeFormatter.ofPattern("yyMMddHHmm"))
                + longId;
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public static synchronized String nextId() {
        // 上次生成ID的时间截
        Long timestamp = nextTimestamp();

        // 移位并通过或运算拼到一起组成64位的ID
        return (((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence) + "";
//        return "N" +
//                (((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence);
    }

    public static void main(String[] args) {
        System.out.println(nextId());
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected static long timeGen() {
        return System.currentTimeMillis();
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 身份证加密
     *
     * @param idCard
     * @return
     */
    public static String maskIdCard(String idCard) {
        if (StringUtils.isEmpty(idCard)) {
            return idCard;
        }
        String leftOffset = idCard.substring(0, idCard.length() - 4);
        StringBuffer left = new StringBuffer();
        for (int i = 0; i < leftOffset.length() - 1; i++) {
            left.append("*");
        }
        String right = idCard.substring(idCard.length() - 4);
        return left + right;
    }

}
