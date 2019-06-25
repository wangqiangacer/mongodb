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
@Document(collection = "user_login_recode")
public class UserLoginRecord {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户手机号
     */
    private String userPhone;
    /**
     *  渠道ID
     */
    private Long channelId;
    /**
     *  渠道名称
     */
    private String channelName;
    /**
     *  渠道类型(0-正常 1-短信 2-微信 3-信息流)
     */
    private Integer channelType;
    /**
     *  平台
     */
    private String platform;
    /**
     *  新老用户登录(0-新用户 1-老用户)
     */
    private Integer isNewUser;
    /**
     *  操作平台 (0-注册页登录 1-APP登录 2-WEB登录)
     */
    private Integer operatedPlatform;
    /**
     *  版本号
     */
    private String versionNumber;
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
    private  String countNum;
    private  String countDay;

}
