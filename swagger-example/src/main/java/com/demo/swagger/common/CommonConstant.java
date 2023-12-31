package com.demo.swagger.common;

/**
 * @auther gzhen
 * @date 2023-10-13  15:17
 * @description
 */

public class CommonConstant {


    /**
     * 公共请求报文头：设备类型
     */
    String HEADER_DEVICE_TYPE = "x-device-type";

    /**
     * 公共请求报文头：设备类型
     */
    String HEADER_DEVICE_ID = "x-device-id";

    /**
     * 公共请求报文头：访问token
     */
    String HEADER_ACCESS_TOKEN = "x-access-token";

    /**
     * 公共请求报文头：刷新token
     */
    String HEADER_REFRESH_TOKEN = "x-refresh-token";

    /**
     * 公共请求报文头：全局流水号
     */
    String HEADER_GLOBAL_ID = "x-global-id";

    /**
     * 公共请求报文头：请求发生的时间
     */
    String HEADER_REQUEST_TIME = "x-request-time";

    /**
     * 公共请求报文头：原始服务
     */
    String HEADER_REQUEST_ORIGIN_SERVICE = "x-request-origin-service";

    /**
     * 公共请求报文头：当前服务
     */
    String HEADER_REQUEST_SERVICE = "x-request-service";

    /**
     * 公共请求报文头：原始请求URL
     */
    String HEADER_REQUEST_ORIGIN_URL = "x-request-origin-url";

    /**
     * 公共请求报文头：当前请求URL
     */
    String HEADER_REQUEST_URL = "x-request-url";

    /**
     * 正常状态
     */
    Integer STATUS_NORMAL = 0;

    /**
     * 禁用状态
     */
    Integer STATUS_DISABLE = -1;

    /**
     * 删除标志
     */
    Integer DEL_FLAG_1 = 1;

    /**
     * 未删除
     */
    Integer DEL_FLAG_0 = 0;

    /**
     * 系统日志类型： 登录
     */
    int LOG_TYPE_1 = 1;

    /**
     * 系统日志类型： 操作
     */
    int LOG_TYPE_2 = 2;

    /**
     * 操作日志类型： 查询
     */
    int OPERATE_TYPE_1 = 1;

    /**
     * 操作日志类型： 添加
     */
    int OPERATE_TYPE_2 = 2;

    /**
     * 操作日志类型： 更新
     */
    int OPERATE_TYPE_3 = 3;

    /**
     * 操作日志类型： 删除
     */
    int OPERATE_TYPE_4 = 4;

    /**
     * 操作日志类型： 导入
     */
    int OPERATE_TYPE_5 = 5;

    /**
     * 操作日志类型： 导出
     */
    int OPERATE_TYPE_6 = 6;


    /** {@code 500 Server Error} (HTTP/1.0 - RFC 1945) */
    Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /** {@code 200 OK} (HTTP/1.0 - RFC 1945) */
    Integer SC_OK_200 = 200;

    /**访问权限认证未通过 510*/
    Integer SC_JEECG_NO_AUTHZ=510;

    /** 登录用户Shiro权限缓存KEY前缀 */
    public static String PREFIX_USER_SHIRO_CACHE  = "shiro:cache:cn.smartcl.config.shiro.ShiroRealm.authorizationCache:";
    /** 登录用户Token令牌缓存KEY前缀 */
    String PREFIX_USER_TOKEN  = "prefix_user_token_";
    /** Token缓存时间：3600秒即一小时 */
    int  TOKEN_EXPIRE_TIME  = 3600;


    /**
     *  0：一级菜单
     */
    Integer MENU_TYPE_0  = 0;
    /**
     *  1：子菜单
     */
    Integer MENU_TYPE_1  = 1;
    /**
     *  2：按钮权限
     */
    Integer MENU_TYPE_2  = 2;

    /**通告对象类型（USER:指定用户，ALL:全体用户）*/
    String MSG_TYPE_UESR  = "USER";
    String MSG_TYPE_ALL  = "ALL";

    /**发布状态（0未发布，1已发布，2已撤销）*/
    String NO_SEND  = "0";
    String HAS_SEND  = "1";
    String HAS_CANCLE  = "2";

    /**阅读状态（0未读，1已读）*/
    String HAS_READ_FLAG  = "1";
    String NO_READ_FLAG  = "0";

    /**优先级（L低，M中，H高）*/
    String PRIORITY_L  = "L";
    String PRIORITY_M  = "M";
    String PRIORITY_H  = "H";

    /**
     * 短信模板方式  0 .登录模板、1.注册模板、2.忘记密码模板
     */
    String SMS_TPL_TYPE_0  = "0";
    String SMS_TPL_TYPE_1  = "1";
    String SMS_TPL_TYPE_2  = "2";

    /**
     * 状态(0无效1有效)
     */
    String STATUS_0 = "0";
    String STATUS_1 = "1";

    /**
     * 同步工作流引擎1同步0不同步
     */
    Integer ACT_SYNC_1 = 1;
    Integer ACT_SYNC_0 = 0;

    /**
     * 消息类型1:通知公告2:系统消息
     */
    String MSG_CATEGORY_1 = "1";
    String MSG_CATEGORY_2 = "2";

    /**
     * 是否配置菜单的数据权限 1是0否
     */
    Integer RULE_FLAG_0 = 0;
    Integer RULE_FLAG_1 = 1;

    /**
     * 是否用户已被冻结 1正常(解冻) 2冻结
     */
    Integer USER_UNFREEZE = 1;
    Integer USER_FREEZE = 2;

    /**字典翻译文本后缀*/
    String DICT_TEXT_SUFFIX = "_dictText";

    /**
     * 表单设计器主表类型
     */
    Integer DESIGN_FORM_TYPE_MAIN = 1;

    /**
     * 表单设计器子表表类型
     */
    Integer DESIGN_FORM_TYPE_SUB = 2;

    /**
     * 表单设计器URL授权通过
     */
    Integer DESIGN_FORM_URL_STATUS_PASSED = 1;

    /**
     * 表单设计器URL授权未通过
     */
    Integer DESIGN_FORM_URL_STATUS_NOT_PASSED = 2;

    /**
     * 表单设计器新增 Flag
     */
    String DESIGN_FORM_URL_TYPE_ADD = "add";
    /**
     * 表单设计器修改 Flag
     */
    String DESIGN_FORM_URL_TYPE_EDIT = "edit";
    /**
     * 表单设计器详情 Flag
     */
    String DESIGN_FORM_URL_TYPE_DETAIL = "detail";
    /**
     * 表单设计器复用数据 Flag
     */
    String DESIGN_FORM_URL_TYPE_REUSE = "reuse";
    /**
     * 表单设计器编辑 Flag （已弃用）
     */
    String DESIGN_FORM_URL_TYPE_VIEW = "view";

    /**
     * online参数值设置（是：Y, 否：N）
     */
    String ONLINE_PARAM_VAL_IS_TURE = "Y";
    String ONLINE_PARAM_VAL_IS_FALSE = "N";

    /**
     * 文件上传类型（本地：local，Minio：minio，阿里云：alioss）
     */
    String UPLOAD_TYPE_LOCAL = "local";
    String UPLOAD_TYPE_MINIO = "minio";
    String UPLOAD_TYPE_OSS = "alioss";

    /**
     * 文档上传自定义桶名称
     */
    String UPLOAD_CUSTOM_BUCKET = "eoafile";
    /**
     * 文档上传自定义路径
     */
    String UPLOAD_CUSTOM_PATH = "eoafile";
    /**
     * 文件外链接有效天数
     */
    Integer UPLOAD_EFFECTIVE_DAYS = 1;

    /**
     * 员工身份 （1:普通员工  2:上级）
     */
    Integer USER_IDENTITY_1 = 1;
    Integer USER_IDENTITY_2 = 2;

    /** sys_user 表 username 唯一键索引 */
    String SQL_INDEX_UNIQ_SYS_USER_USERNAME = "uniq_sys_user_username";
    /** sys_user 表 work_no 唯一键索引 */
    String SQL_INDEX_UNIQ_SYS_USER_WORK_NO = "uniq_sys_user_work_no";
    /** sys_user 表 phone 唯一键索引 */
    String SQL_INDEX_UNIQ_SYS_USER_PHONE = "uniq_sys_user_phone";
    /** sys_user 表 email 唯一键索引 */
    String SQL_INDEX_UNIQ_SYS_USER_EMAIL = "uniq_sys_user_email";
    /** sys_quartz_job 表 job_class_name 唯一键索引 */
    String SQL_INDEX_UNIQ_JOB_CLASS_NAME = "uniq_job_class_name";
    /** sys_position 表 code 唯一键索引 */
    String SQL_INDEX_UNIQ_CODE = "uniq_code";
    /** sys_role 表 code 唯一键索引 */
    String SQL_INDEX_UNIQ_SYS_ROLE_CODE = "uniq_sys_role_role_code";
    /** sys_depart 表 code 唯一键索引 */
    String SQL_INDEX_UNIQ_DEPART_ORG_CODE = "uniq_depart_org_code";
    /**
     * 在线聊天 是否为默认分组
     */
    String IM_DEFAULT_GROUP = "1";
    /**
     * 在线聊天 图片文件保存路径
     */
    String IM_UPLOAD_CUSTOM_PATH = "imfile";
    /**
     * 在线聊天 用户状态
     */
    String IM_STATUS_ONLINE = "online";

    /**
     * 在线聊天 SOCKET消息类型
     */
    String IM_SOCKET_TYPE = "chatMessage";

    /**
     * 在线聊天 是否开启默认添加好友 1是 0否
     */
    String IM_DEFAULT_ADD_FRIEND = "1";

    /**
     * 在线聊天 用户好友缓存前缀
     */
    String IM_PREFIX_USER_FRIEND_CACHE = "sys:cache:im:im_prefix_user_friend_";

    /**
     * 考勤补卡业务状态 （1：同意  2：不同意）
     */
    String SIGN_PATCH_BIZ_STATUS_1 = "1";
    String SIGN_PATCH_BIZ_STATUS_2 = "2";

    /**
     * 公文文档上传自定义路径
     */
    String UPLOAD_CUSTOM_PATH_OFFICIAL = "officialdoc";
    /**
     * 公文文档下载自定义路径
     */
    String DOWNLOAD_CUSTOM_PATH_OFFICIAL = "officaldown";

    /**
     * WPS存储值类别(1 code文号 2 text（WPS模板还是公文发文模板）)
     */
    String WPS_TYPE_1="1";
    String WPS_TYPE_2="2";


    public final static String X_ACCESS_TOKEN = "X-Access-Token";
    public final static String X_SIGN = "X-Sign";
    public final static String X_TIMESTAMP = "X-TIMESTAMP";
    public final static String TOKEN_IS_INVALID_MSG = "Token失效，请重新登录!";

    /**
     * 多租户 请求头
     */
    public final static String TENANT_ID = "tenant-id";

    /**
     * 微服务读取配置文件属性 服务地址
     */
    public final static String CLOUD_SERVER_KEY = "spring.cloud.nacos.discovery.server-addr";

    /**
     * 第三方登录 验证密码/创建用户 都需要设置一个操作码 防止被恶意调用
     */
    public final static String THIRD_LOGIN_CODE = "third_login_code";

    /**
     * 第三方APP同步方向：本地 --> 第三方APP
     */
    String THIRD_SYNC_TO_APP = "SYNC_TO_APP";
    /**
     * 第三方APP同步方向：第三方APP --> 本地
     */
    String THIRD_SYNC_TO_LOCAL = "SYNC_TO_LOCAL";

    /** 系统通告消息状态：0=未发布 */
    String ANNOUNCEMENT_SEND_STATUS_0 = "0";
    /** 系统通告消息状态：1=已发布 */
    String ANNOUNCEMENT_SEND_STATUS_1 = "1";
    /** 系统通告消息状态：2=已撤销 */
    String ANNOUNCEMENT_SEND_STATUS_2 = "2";

    /**
     * token的字段
     */
    String TOKEN_INFO_USERNAME = "username";
    String TOKEN_INFO_CHANNEL = "channel";
    String TOKEN_INFO_FIRSTLOGIN = "firstLogin";
    String FIRST_LOGIN_0 = "0";
    String FIRST_LOGIN_1 = "1";
    String CHANNEL_PAD = "pad";
    String CHANNEL_PC = "pc";
    String CHANNEL_APP = "app";
    String CHANNEL_HTML5 = "html5";
    String CHANNEL_WECHAT = "wechat";
    String CHANNEL_DINGTALK = "dingtalk";
    /**用户状态，1-正常,2-冻结,3-停用,4-锁定*/
    int SYS_USER_STATUS_1 = 1;
    /**用户状态，1-正常,2-冻结,3-停用,4-锁定*/
    int SYS_USER_STATUS_2 = 2;
    /**用户状态，1-正常,2-冻结,3-停用,4-锁定*/
    int SYS_USER_STATUS_3 = 3;
    /**用户状态，1-正常,2-冻结,3-停用,4-锁定*/
    int SYS_USER_STATUS_4 = 4;
}
