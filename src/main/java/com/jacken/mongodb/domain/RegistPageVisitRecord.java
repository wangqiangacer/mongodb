package com.jacken.mongodb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "regist_page_visit_recode")
public class RegistPageVisitRecord {

    /**
     * 注册页码
     */
    private String flag;
    /**
     * 渠道ID
     */
    private Long channelId;
    /**
     * 渠道名称
     */
    private String channelName;
    /**
     * 渠道类型(0-正常 1-短信 2-微信 3-信息流)
     */
    private Integer channelType;
    /**
     * IP
     */
    private String ip;
    /**
     * 系统
     */
    private String os;
    /**
     * 时间戳
     */
    private Long dateTime;
    /**
     * 日期（yyyyMMdd）
     */
    private Integer day;
    /**
     * 小时
     */
    private Integer hours;


}