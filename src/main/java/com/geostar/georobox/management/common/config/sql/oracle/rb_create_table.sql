declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_APK_DOWNLOAD') ;
    if num > 0 then
        execute immediate 'drop table RB_APK_DOWNLOAD' ;
    end if;
    end;
/
CREATE TABLE "RB_APK_DOWNLOAD" (
"ID" VARCHAR2(255 BYTE) NOT NULL ,
"VERSION_ID" VARCHAR2(255 BYTE) NULL ,
"USER_INFO" VARCHAR2(255 BYTE) NULL ,
"MODE_INFO" VARCHAR2(255 BYTE) NULL ,
"OTHER_INFO" VARCHAR2(255 BYTE) NULL ,
"DATETIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_APK_DOWNLOAD"."ID" IS '主键ID'
/
COMMENT ON COLUMN "RB_APK_DOWNLOAD"."VERSION_ID" IS 'APK版本ID'
/
COMMENT ON COLUMN "RB_APK_DOWNLOAD"."USER_INFO" IS '用户信息'
/
COMMENT ON COLUMN "RB_APK_DOWNLOAD"."MODE_INFO" IS '设备信息'
/
COMMENT ON COLUMN "RB_APK_DOWNLOAD"."OTHER_INFO" IS '其他信息'
/
COMMENT ON COLUMN "RB_APK_DOWNLOAD"."DATETIME" IS '下载时间'
/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_APK_INFO') ;
    if num > 0 then
        execute immediate 'drop table RB_APK_INFO' ;
    end if;
    end;
/
CREATE TABLE "RB_APK_INFO" (
"ID" VARCHAR2(255 BYTE) NOT NULL ,
"APK_NAME" VARCHAR2(100 BYTE) NULL ,
"APK_VERSION" VARCHAR2(30 BYTE) NULL ,
"APK_VERSION_CODE" VARCHAR2(30 BYTE) NULL ,
"APK_DESCRIBE" VARCHAR2(500 BYTE) NULL ,
"APK_ICON_URL" VARCHAR2(255 BYTE) NULL ,
"APK_UPLOAD_INFO" VARCHAR2(500 BYTE) NULL ,
"APK_DESCRIBE_IMAGE" VARCHAR2(255 BYTE) NULL ,
"APK_URL" VARCHAR2(255 BYTE) NULL ,
"APK_PACKAGENAME" VARCHAR2(255 BYTE) NULL ,
"DOWNLOAD_NUM" NUMBER DEFAULT 0  NULL ,
"APK_SIZE" VARCHAR2(20 BYTE) NULL ,
"MASTUPLOAD" NUMBER DEFAULT 0  NULL ,
"DATETIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_APK_INFO"."APK_NAME" IS 'APK名称'
/
COMMENT ON COLUMN "RB_APK_INFO"."APK_VERSION" IS 'APK版本'
/
COMMENT ON COLUMN "RB_APK_INFO"."APK_VERSION_CODE" IS 'APK版本号'
/
COMMENT ON COLUMN "RB_APK_INFO"."APK_DESCRIBE" IS 'APK描述'
/
COMMENT ON COLUMN "RB_APK_INFO"."APK_ICON_URL" IS 'APK图标地址'
/
COMMENT ON COLUMN "RB_APK_INFO"."APK_UPLOAD_INFO" IS 'APK更新说明'
/
COMMENT ON COLUMN "RB_APK_INFO"."APK_DESCRIBE_IMAGE" IS 'APK详情图片'
/
COMMENT ON COLUMN "RB_APK_INFO"."APK_URL" IS 'APK下载地址'
/
COMMENT ON COLUMN "RB_APK_INFO"."APK_PACKAGENAME" IS 'APK包名'
/
COMMENT ON COLUMN "RB_APK_INFO"."DOWNLOAD_NUM" IS '下载数量'
/
COMMENT ON COLUMN "RB_APK_INFO"."APK_SIZE" IS 'APK大小'
/
COMMENT ON COLUMN "RB_APK_INFO"."MASTUPLOAD" IS '是否强制更新（0：不强制更新，1：强制更新）'
/
COMMENT ON COLUMN "RB_APK_INFO"."DATETIME" IS '上传时间'
/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_CONFIG') ;
    if num > 0 then
        execute immediate 'drop table RB_CONFIG' ;
    end if;
    end;
/
CREATE TABLE "RB_CONFIG" (
"ID" VARCHAR2(255 BYTE) NOT NULL ,
"NAME" VARCHAR2(80 BYTE) NOT NULL ,
"TYPE" VARCHAR2(255 BYTE) NULL ,
"URL" VARCHAR2(255 BYTE) NULL ,
"PACKAGENAME" VARCHAR2(255 BYTE) NULL ,
"NATIVEPATH" VARCHAR2(255 BYTE) NULL ,
"VERSION" VARCHAR2(255 BYTE) NULL ,
"DATETIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_CONFIG"."ID" IS '主键ID'
/
COMMENT ON COLUMN "RB_CONFIG"."NAME" IS '资源名称'
/
COMMENT ON COLUMN "RB_CONFIG"."TYPE" IS '资源类型（XML/PIC/APK）'
/
COMMENT ON COLUMN "RB_CONFIG"."URL" IS '资源地址'
/
COMMENT ON COLUMN "RB_CONFIG"."PACKAGENAME" IS '资源包名'
/
COMMENT ON COLUMN "RB_CONFIG"."NATIVEPATH" IS '资源路径'
/
COMMENT ON COLUMN "RB_CONFIG"."VERSION" IS '资源版本'
/
COMMENT ON COLUMN "RB_CONFIG"."DATETIME" IS '上传时间'
/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_DOCUMENT') ;
    if num > 0 then
        execute immediate 'drop table RB_DOCUMENT' ;
    end if;
    end;
/
CREATE TABLE "RB_DOCUMENT" (
"ID" VARCHAR2(255 BYTE) NOT NULL ,
"FILE_NAME" VARCHAR2(255 BYTE) NULL ,
"FILE_PATH" VARCHAR2(255 BYTE) NULL ,
"FILE_SIZE" VARCHAR2(30 BYTE) NULL ,
"FILE_STATE" NUMBER NULL ,
"DATETIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_DOCUMENT"."ID" IS '主键ID'
/
COMMENT ON COLUMN "RB_DOCUMENT"."FILE_NAME" IS '文件名称'
/
COMMENT ON COLUMN "RB_DOCUMENT"."FILE_PATH" IS '文件地址'
/
COMMENT ON COLUMN "RB_DOCUMENT"."FILE_SIZE" IS '文件大小'
/
COMMENT ON COLUMN "RB_DOCUMENT"."FILE_STATE" IS '文件状态'
/
COMMENT ON COLUMN "RB_DOCUMENT"."DATETIME" IS '上传时间'
/


declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_ERROR_LOG') ;
    if num > 0 then
        execute immediate 'drop table RB_ERROR_LOG' ;
    end if;
    end;
/
CREATE TABLE "RB_ERROR_LOG" (
"ID" VARCHAR2(255 BYTE) NOT NULL ,
"PHONE_MODE" VARCHAR2(255 BYTE) NULL ,
"PHONE_VERSION" VARCHAR2(255 BYTE) NULL ,
"PHONE_MANUFACTURER" VARCHAR2(255 BYTE) NULL ,
"PHONE_MAC" VARCHAR2(255 BYTE) NULL ,
"CODE_VERSIONNAME" VARCHAR2(255 BYTE) NULL ,
"CODE_VERSIONCODE" VARCHAR2(255 BYTE) NULL ,
"APP_PACKAGENAME" VARCHAR2(255 BYTE) NULL ,
"APP_NAME" VARCHAR2(255 BYTE) NULL ,
"LOG_URL" VARCHAR2(255 BYTE) NULL ,
"INFO" VARCHAR2(1000 BYTE) NULL ,
"USER_INFO" VARCHAR2(255 BYTE) NULL ,
"DATETIME" DATE NULL ,
"IS_COMPLETED" NUMBER DEFAULT 0  NULL ,
"ERROR_INFO" VARCHAR2(4000 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_ERROR_LOG"."ID" IS '主键ID'
/
COMMENT ON COLUMN "RB_ERROR_LOG"."PHONE_MODE" IS '手机型号'
/
COMMENT ON COLUMN "RB_ERROR_LOG"."PHONE_VERSION" IS '手机版本'
/
COMMENT ON COLUMN "RB_ERROR_LOG"."PHONE_MANUFACTURER" IS '手机制造商'
/
COMMENT ON COLUMN "RB_ERROR_LOG"."PHONE_MAC" IS 'MAC地址'
/
COMMENT ON COLUMN "RB_ERROR_LOG"."CODE_VERSIONNAME" IS '版本名称'
/
COMMENT ON COLUMN "RB_ERROR_LOG"."CODE_VERSIONCODE" IS '版本号'
/
COMMENT ON COLUMN "RB_ERROR_LOG"."APP_PACKAGENAME" IS 'APP包名'
/
COMMENT ON COLUMN "RB_ERROR_LOG"."APP_NAME" IS 'APP名称'
/
COMMENT ON COLUMN "RB_ERROR_LOG"."LOG_URL" IS '错误文件地址'
/
COMMENT ON COLUMN "RB_ERROR_LOG"."INFO" IS '其他信息'
/
COMMENT ON COLUMN "RB_ERROR_LOG"."USER_INFO" IS '用户信息'
/
COMMENT ON COLUMN "RB_ERROR_LOG"."DATETIME" IS '上传时间'
/
COMMENT ON COLUMN "RB_ERROR_LOG"."IS_COMPLETED" IS '是否处理（0：未处理，1：已处理）'
/
COMMENT ON COLUMN "RB_ERROR_LOG"."ERROR_INFO" IS '详细错误内容'
/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_LOG') ;
    if num > 0 then
        execute immediate 'drop table RB_LOG' ;
    end if;
    end;
/
CREATE TABLE "RB_LOG" (
"ID" VARCHAR2(255 BYTE) NOT NULL ,
"FILE_URL" VARCHAR2(255 BYTE) NULL ,
"FILE_NAME" VARCHAR2(255 BYTE) NULL ,
"LOG_TEXT" VARCHAR2(1000 BYTE) NULL ,
"DATETIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_LOG"."ID" IS '主键ID'
/
COMMENT ON COLUMN "RB_LOG"."FILE_URL" IS '日志文件地址'
/
COMMENT ON COLUMN "RB_LOG"."FILE_NAME" IS '日志文件名称'
/
COMMENT ON COLUMN "RB_LOG"."LOG_TEXT" IS 'LOG内容'
/
COMMENT ON COLUMN "RB_LOG"."DATETIME" IS '上传时间'
/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_NAV_CONGFIG') ;
    if num > 0 then
        execute immediate 'drop table RB_NAV_CONGFIG' ;
    end if;
    end;
/
CREATE TABLE "RB_NAV_CONGFIG" (
"ID" VARCHAR2(255 BYTE) NOT NULL ,
"TITLE" VARCHAR2(20 BYTE) NULL ,
"ICON" VARCHAR2(50 BYTE) NULL ,
"HREF" VARCHAR2(100 BYTE) NULL ,
"IS_OPEN" NUMBER DEFAULT 1  NULL ,
"DATETIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_NAV_CONGFIG"."ID" IS '主键ID'
/
COMMENT ON COLUMN "RB_NAV_CONGFIG"."TITLE" IS '导航栏名称'
/
COMMENT ON COLUMN "RB_NAV_CONGFIG"."ICON" IS '导航栏图标'
/
COMMENT ON COLUMN "RB_NAV_CONGFIG"."HREF" IS '导航栏地址'
/
COMMENT ON COLUMN "RB_NAV_CONGFIG"."IS_OPEN" IS '是否开启（1：开启，0：关闭）'
/
COMMENT ON COLUMN "RB_NAV_CONGFIG"."DATETIME" IS '时间'
/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_PLUG') ;
    if num > 0 then
        execute immediate 'drop table RB_PLUG' ;
    end if;
    end;
/
CREATE TABLE "RB_PLUG" (
"PLUG_ID" VARCHAR2(255 BYTE) NOT NULL ,
"PLUG_NAME" VARCHAR2(255 BYTE) NOT NULL ,
"PLUG_TYPE" NUMBER NOT NULL ,
"PLUG_URL" VARCHAR2(255 BYTE) NOT NULL ,
"PLUG_ICON" VARCHAR2(255 BYTE) NULL ,
"PLUG_PACKAGE" VARCHAR2(255 BYTE) NULL ,
"PLUG_VERSIONCODE" VARCHAR2(255 BYTE) NULL ,
"PLUG_DETAILS" VARCHAR2(255 BYTE) NULL ,
"PLUG_SORT" NUMBER NULL ,
"PLUG_UP_DATE" DATE NULL ,
"PLUG_IS_DOWN" NUMBER NULL ,
"PLUG_DOWN_DATE" DATE NULL ,
"PLUG_NEEDINSTALL" NUMBER DEFAULT 0  NULL ,
"PLUS_LAUNCHER_ACTIVITY" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_PLUG"."PLUG_NAME" IS '插件名称'
/
COMMENT ON COLUMN "RB_PLUG"."PLUG_TYPE" IS '插件类型1APK2链接3zip'
/
COMMENT ON COLUMN "RB_PLUG"."PLUG_URL" IS '插件地址'
/
COMMENT ON COLUMN "RB_PLUG"."PLUG_ICON" IS '插件icon地址'
/
COMMENT ON COLUMN "RB_PLUG"."PLUG_PACKAGE" IS '插件包名'
/
COMMENT ON COLUMN "RB_PLUG"."PLUG_VERSIONCODE" IS '插件版本Code'
/
COMMENT ON COLUMN "RB_PLUG"."PLUG_DETAILS" IS '插件详情'
/
COMMENT ON COLUMN "RB_PLUG"."PLUG_SORT" IS '插件排序'
/
COMMENT ON COLUMN "RB_PLUG"."PLUG_UP_DATE" IS '上架时间'
/
COMMENT ON COLUMN "RB_PLUG"."PLUG_IS_DOWN" IS '是否下架0下架1上线'
/
COMMENT ON COLUMN "RB_PLUG"."PLUG_DOWN_DATE" IS '下架时间'
/
COMMENT ON COLUMN "RB_PLUG"."PLUG_NEEDINSTALL" IS '是否需要调用系统方法安装0不需要1需要'
/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_PLUG_AND_C') ;
    if num > 0 then
        execute immediate 'drop table RB_PLUG_AND_C' ;
    end if;
    end;
/
CREATE TABLE "RB_PLUG_AND_C" (
"PLUG_ID" VARCHAR2(255 BYTE) NOT NULL ,
"CATEGORY_ID" VARCHAR2(255 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_PLUG_AND_P') ;
    if num > 0 then
        execute immediate 'drop table RB_PLUG_AND_P' ;
    end if;
    end;
/
CREATE TABLE "RB_PLUG_AND_P" (
"PLUG_ID" VARCHAR2(255 BYTE) NOT NULL ,
"PERMISSION_ID" VARCHAR2(255 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_PLUG_AUDIT') ;
    if num > 0 then
        execute immediate 'drop table RB_PLUG_AUDIT' ;
    end if;
    end;
/
CREATE TABLE "RB_PLUG_AUDIT" (
"PLUG_TEMP_ID" VARCHAR2(255 BYTE) NOT NULL ,
"PLUG_ID" VARCHAR2(255 BYTE) NULL ,
"PLUG_NAME" VARCHAR2(255 BYTE) NOT NULL ,
"PLUG_TYPE" NUMBER NOT NULL ,
"PLUG_URL" VARCHAR2(255 BYTE) NOT NULL ,
"PLUG_ICON" VARCHAR2(255 BYTE) NULL ,
"PLUG_PACKAGE" VARCHAR2(255 BYTE) NULL ,
"PLUG_VERSIONCODE" VARCHAR2(255 BYTE) NULL ,
"PLUG_DETAILS" VARCHAR2(1000 BYTE) NULL ,
"PLUG_SORT" NUMBER NULL ,
"PLUG_NEEDINSTALL" NUMBER DEFAULT 0  NULL ,
"PLUG_PERMISSION" VARCHAR2(2000 BYTE) NULL ,
"PLUG_CATEGORY" VARCHAR2(2000 BYTE) NULL ,
"PLUS_LAUNCHER_ACTIVITY" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_PLUG_AUDIT"."PLUG_TEMP_ID" IS '主键ID'
/
COMMENT ON COLUMN "RB_PLUG_AUDIT"."PLUG_ID" IS '旧版本ID,如果是修改则审核通过下架旧版本'
/
COMMENT ON COLUMN "RB_PLUG_AUDIT"."PLUG_NAME" IS '插件名称'
/
COMMENT ON COLUMN "RB_PLUG_AUDIT"."PLUG_TYPE" IS '插件类型'
/
COMMENT ON COLUMN "RB_PLUG_AUDIT"."PLUG_URL" IS '插件地址'
/
COMMENT ON COLUMN "RB_PLUG_AUDIT"."PLUG_ICON" IS '插件icon地址'
/
COMMENT ON COLUMN "RB_PLUG_AUDIT"."PLUG_PACKAGE" IS '插件包名'
/
COMMENT ON COLUMN "RB_PLUG_AUDIT"."PLUG_VERSIONCODE" IS '插件版本Code'
/
COMMENT ON COLUMN "RB_PLUG_AUDIT"."PLUG_DETAILS" IS '插件详情'
/
COMMENT ON COLUMN "RB_PLUG_AUDIT"."PLUG_SORT" IS '插件排序'
/
COMMENT ON COLUMN "RB_PLUG_AUDIT"."PLUG_NEEDINSTALL" IS '是否需要调用系统方法安装0不需要1需要'
/
COMMENT ON COLUMN "RB_PLUG_AUDIT"."PLUG_PERMISSION" IS '权限'
/
COMMENT ON COLUMN "RB_PLUG_AUDIT"."PLUG_CATEGORY" IS '分类'
/


declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_PLUG_CATEGORY') ;
    if num > 0 then
        execute immediate 'drop table RB_PLUG_CATEGORY' ;
    end if;
    end;
/
CREATE TABLE "RB_PLUG_CATEGORY" (
"CATEGORY_ID" VARCHAR2(255 BYTE) NOT NULL ,
"CATEGORY_NAME" VARCHAR2(255 BYTE) NOT NULL ,
"CATEGORY_DES" VARCHAR2(255 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/


declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_PLUG_PREMISSION') ;
    if num > 0 then
        execute immediate 'drop table RB_PLUG_PREMISSION' ;
    end if;
    end;
/
CREATE TABLE "RB_PLUG_PREMISSION" (
"PERMISSION_ID" VARCHAR2(255 BYTE) NOT NULL ,
"PERMISSION_NAME" VARCHAR2(255 BYTE) NOT NULL ,
"PERMISSION_DES" VARCHAR2(255 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_PLUG_USER') ;
    if num > 0 then
        execute immediate 'drop table RB_PLUG_USER' ;
    end if;
    end;
/
CREATE TABLE "RB_PLUG_USER" (
"PLUG_ID" VARCHAR2(255 BYTE) NOT NULL ,
"USER_ID" VARCHAR2(255 BYTE) NOT NULL ,
"TAG" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_PLUG_USER"."TAG" IS 'TAG用户自定义用途'
/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_RC_INFO') ;
    if num > 0 then
        execute immediate 'drop table RB_RC_INFO' ;
    end if;
    end;
/
CREATE TABLE "RB_RC_INFO" (
"MESSAGE_ID" VARCHAR2(255 BYTE) NOT NULL ,
"IS_PUBLIC" NUMBER NULL ,
"TYPE" NUMBER NULL ,
"TITLE" VARCHAR2(255 BYTE) NULL ,
"START_TIME" DATE NULL ,
"END_TIME" DATE NULL ,
"ADDRESS" VARCHAR2(255 BYTE) NULL ,
"REMIND" NUMBER DEFAULT 1  NULL ,
"REMIND_WAY" NUMBER NULL ,
"REMIND_AHEAD_TIME" NUMBER NULL ,
"REMIND_REPEAT_INTERVAL" NUMBER NULL ,
"INFO" VARCHAR2(255 BYTE) NULL ,
"CREATE_ID" VARCHAR2(255 BYTE) NULL ,
"CREATE_TIME" DATE NULL ,
"REMIND_REPEAT" NUMBER NULL ,
"REMIND_STATE" NUMBER NULL ,
"EVENT_ID" VARCHAR2(255 BYTE) NULL ,
"IMPORTANT" NUMBER NULL ,
"CREATE_NAME" VARCHAR2(255 BYTE) NULL ,
"OPERATION_STATE" NUMBER NULL ,
"ALL_DAY" NUMBER NULL ,
"REMIND_AHEAD_TIME_CODE" VARCHAR2(255 BYTE) NULL ,
"START_TIME_BUCKET" DATE NULL ,
"END_TIME_BUCKET" DATE NULL ,
"CHANGE_TIME" DATE NULL ,
"CHANGE_TIME_POINT" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_RC_INFO"."IS_PUBLIC" IS '是否公开（0：不公开，1：公开）'
/
COMMENT ON COLUMN "RB_RC_INFO"."TYPE" IS '日程类型'
/
COMMENT ON COLUMN "RB_RC_INFO"."TITLE" IS '日程标题'
/
COMMENT ON COLUMN "RB_RC_INFO"."START_TIME" IS '开始时间'
/
COMMENT ON COLUMN "RB_RC_INFO"."END_TIME" IS '结束时间'
/
COMMENT ON COLUMN "RB_RC_INFO"."ADDRESS" IS '日程地点'
/
COMMENT ON COLUMN "RB_RC_INFO"."REMIND" IS '是否提醒（0：不提醒，1：提醒）'
/
COMMENT ON COLUMN "RB_RC_INFO"."REMIND_WAY" IS '提醒方式'
/
COMMENT ON COLUMN "RB_RC_INFO"."REMIND_AHEAD_TIME" IS '提醒提前时间'
/
COMMENT ON COLUMN "RB_RC_INFO"."REMIND_REPEAT_INTERVAL" IS '提醒重复间隔'
/
COMMENT ON COLUMN "RB_RC_INFO"."INFO" IS '说明（备注）'
/
COMMENT ON COLUMN "RB_RC_INFO"."CREATE_ID" IS '创建者ID'
/
COMMENT ON COLUMN "RB_RC_INFO"."CREATE_TIME" IS '创建时间'
/
COMMENT ON COLUMN "RB_RC_INFO"."REMIND_REPEAT" IS '是否重复提醒 (0,不重复   1每天  2 每周，3每两周，4每月，5每年）'
/
COMMENT ON COLUMN "RB_RC_INFO"."REMIND_STATE" IS '提醒状态'
/
COMMENT ON COLUMN "RB_RC_INFO"."EVENT_ID" IS '日程事件ID'
/
COMMENT ON COLUMN "RB_RC_INFO"."IMPORTANT" IS '重要性'
/
COMMENT ON COLUMN "RB_RC_INFO"."CREATE_NAME" IS '创建人名称'
/
COMMENT ON COLUMN "RB_RC_INFO"."OPERATION_STATE" IS '操作状态'
/
COMMENT ON COLUMN "RB_RC_INFO"."ALL_DAY" IS '是否全天（0：不是全天，1：全天）'
/
COMMENT ON COLUMN "RB_RC_INFO"."REMIND_AHEAD_TIME_CODE" IS '提醒提前时间代码'
/
COMMENT ON COLUMN "RB_RC_INFO"."START_TIME_BUCKET" IS '开始时间段'
/
COMMENT ON COLUMN "RB_RC_INFO"."END_TIME_BUCKET" IS '结束时间段'
/
COMMENT ON COLUMN "RB_RC_INFO"."CHANGE_TIME" IS '修改时间'
/
COMMENT ON COLUMN "RB_RC_INFO"."CHANGE_TIME_POINT" IS '修改时间点'
/


declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_RC_MEMBER') ;
    if num > 0 then
        execute immediate 'drop table RB_RC_MEMBER' ;
    end if;
    end;
/
CREATE TABLE "RB_RC_MEMBER" (
"PARTICIPATE_ID" VARCHAR2(255 BYTE) NOT NULL ,
"MESSAGE_ID" VARCHAR2(255 BYTE) NULL ,
"PARTICIPATOR_ID" VARCHAR2(255 BYTE) NULL ,
"PARTICIPATOR_NAME" VARCHAR2(255 BYTE) NULL ,
"PARTICIPATOR_TYPE" NUMBER NULL ,
"PARTICIPATOR_TEL" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_RC_MEMBER"."PARTICIPATE_ID" IS '日程参与ID'
/
COMMENT ON COLUMN "RB_RC_MEMBER"."MESSAGE_ID" IS '日程信息ID'
/
COMMENT ON COLUMN "RB_RC_MEMBER"."PARTICIPATOR_ID" IS '参与者ID'
/
COMMENT ON COLUMN "RB_RC_MEMBER"."PARTICIPATOR_NAME" IS '参与者名称'
/
COMMENT ON COLUMN "RB_RC_MEMBER"."PARTICIPATOR_TYPE" IS '参与者类型'
/
COMMENT ON COLUMN "RB_RC_MEMBER"."PARTICIPATOR_TEL" IS '参与者联系方式'
/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_RUN_CONFIG') ;
    if num > 0 then
        execute immediate 'drop table RB_RUN_CONFIG' ;
    end if;
    end;
/
CREATE TABLE "RB_RUN_CONFIG" (
"ID" VARCHAR2(255 BYTE) NOT NULL ,
"APP_NAME" VARCHAR2(80 BYTE) NULL ,
"APP_PACKAGE" VARCHAR2(255 BYTE) NULL ,
"CLASS" VARCHAR2(255 BYTE) NULL ,
"INFO_MODE" VARCHAR2(255 BYTE) NULL ,
"INFO_USER" VARCHAR2(255 BYTE) NULL ,
"INFO_OPERATION" VARCHAR2(255 BYTE) NULL ,
"INFO_OTHER" VARCHAR2(1000 BYTE) NULL ,
"MODE_VERISON" VARCHAR2(60 BYTE) NULL ,
"DATETIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_RUN_CONFIG"."APP_NAME" IS '插件名称'
/
COMMENT ON COLUMN "RB_RUN_CONFIG"."APP_PACKAGE" IS '插件包名'
/
COMMENT ON COLUMN "RB_RUN_CONFIG"."CLASS" IS '插件CLASS'
/
COMMENT ON COLUMN "RB_RUN_CONFIG"."INFO_MODE" IS '设备信息'
/
COMMENT ON COLUMN "RB_RUN_CONFIG"."INFO_USER" IS '用户信息'
/
COMMENT ON COLUMN "RB_RUN_CONFIG"."INFO_OPERATION" IS '操作信息'
/
COMMENT ON COLUMN "RB_RUN_CONFIG"."INFO_OTHER" IS '其他信息'
/
COMMENT ON COLUMN "RB_RUN_CONFIG"."MODE_VERISON" IS '版本号'
/
COMMENT ON COLUMN "RB_RUN_CONFIG"."DATETIME" IS '运行时间'
/


declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_RUN_MODE') ;
    if num > 0 then
        execute immediate 'drop table RB_RUN_MODE' ;
    end if;
    end;
/
CREATE TABLE "RB_RUN_MODE" (
"MODE_EQUIPMENT" VARCHAR2(50 BYTE) NOT NULL ,
"MODE_NAME" VARCHAR2(50 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_RUN_MODE"."MODE_EQUIPMENT" IS '设备型号'
/
COMMENT ON COLUMN "RB_RUN_MODE"."MODE_NAME" IS '设备名称'
/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_SCORE') ;
    if num > 0 then
        execute immediate 'drop table RB_SCORE' ;
    end if;
    end;
/
CREATE TABLE "RB_SCORE" (
"ID" VARCHAR2(255 BYTE) NOT NULL ,
"USER_INFO" VARCHAR2(255 BYTE) NULL ,
"MODE_INFO" VARCHAR2(255 BYTE) NULL ,
"APP_INFO" VARCHAR2(255 BYTE) NULL ,
"BODY" VARCHAR2(255 BYTE) NULL ,
"STAR_SCORE" VARCHAR2(255 BYTE) NULL ,
"TAG" VARCHAR2(255 BYTE) NULL ,
"IMAGE_URL" VARCHAR2(255 BYTE) NULL ,
"OTHER_INFO" VARCHAR2(500 BYTE) NULL ,
"DATETIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_SCORE"."ID" IS '主键ID'
/
COMMENT ON COLUMN "RB_SCORE"."USER_INFO" IS '用户信息'
/
COMMENT ON COLUMN "RB_SCORE"."MODE_INFO" IS '设备信息'
/
COMMENT ON COLUMN "RB_SCORE"."APP_INFO" IS 'APP信息'
/
COMMENT ON COLUMN "RB_SCORE"."BODY" IS '评分内容'
/
COMMENT ON COLUMN "RB_SCORE"."STAR_SCORE" IS '评分星数'
/
COMMENT ON COLUMN "RB_SCORE"."TAG" IS '标签'
/
COMMENT ON COLUMN "RB_SCORE"."IMAGE_URL" IS '评分图片地址（最多四张分号分割）'
/
COMMENT ON COLUMN "RB_SCORE"."OTHER_INFO" IS '其他信息'
/
COMMENT ON COLUMN "RB_SCORE"."DATETIME" IS '评分时间'
/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_SCORE_TAG') ;
    if num > 0 then
        execute immediate 'drop table RB_SCORE_TAG' ;
    end if;
    end;
/
CREATE TABLE "RB_SCORE_TAG" (
"TAG" VARCHAR2(255 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_SCORE_TAG"."TAG" IS '标签内容'
/


declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_SERVER_INFO') ;
    if num > 0 then
        execute immediate 'drop table RB_SERVER_INFO' ;
    end if;
    end;
/
CREATE TABLE "RB_SERVER_INFO" (
"ID" VARCHAR2(255 BYTE) NOT NULL ,
"SERVER_NAME" VARCHAR2(255 BYTE) NOT NULL ,
"SERVER_URL" VARCHAR2(255 BYTE) NOT NULL ,
"SERVER_DIS" VARCHAR2(255 BYTE) NULL ,
"IS_OPEN" NUMBER DEFAULT 1  NULL ,
"DATETIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_SERVER_INFO"."ID" IS '主键ID'
/
COMMENT ON COLUMN "RB_SERVER_INFO"."SERVER_NAME" IS '服务器名称'
/
COMMENT ON COLUMN "RB_SERVER_INFO"."SERVER_URL" IS '服务器地址'
/
COMMENT ON COLUMN "RB_SERVER_INFO"."SERVER_DIS" IS '服务器说明'
/
COMMENT ON COLUMN "RB_SERVER_INFO"."IS_OPEN" IS '是否处理（0：不可用，1：可用）默认为1'
/
COMMENT ON COLUMN "RB_SERVER_INFO"."DATETIME" IS '上传时间'
/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_USERBACK') ;
    if num > 0 then
        execute immediate 'drop table RB_USERBACK' ;
    end if;
    end;
/
CREATE TABLE "RB_USERBACK" (
"ID" VARCHAR2(255 BYTE) NOT NULL ,
"INFO_USER" VARCHAR2(255 BYTE) NULL ,
"INFO_BACK" VARCHAR2(2000 BYTE) NULL ,
"INFO_BACK_URL" VARCHAR2(255 BYTE) NULL ,
"INFO_MODE" VARCHAR2(255 BYTE) NULL ,
"INFO_APPINFO" VARCHAR2(255 BYTE) NULL ,
"INFO_OTHER" VARCHAR2(1000 BYTE) NULL ,
"DATETIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_USERBACK"."ID" IS '主键ID'
/
COMMENT ON COLUMN "RB_USERBACK"."INFO_USER" IS '用户信息'
/
COMMENT ON COLUMN "RB_USERBACK"."INFO_BACK" IS '反馈内容'
/
COMMENT ON COLUMN "RB_USERBACK"."INFO_BACK_URL" IS '反馈图片地址（最多四张分号分割）'
/
COMMENT ON COLUMN "RB_USERBACK"."INFO_MODE" IS '设备信息'
/
COMMENT ON COLUMN "RB_USERBACK"."INFO_APPINFO" IS 'APP信息'
/
COMMENT ON COLUMN "RB_USERBACK"."INFO_OTHER" IS '其他信息'
/
COMMENT ON COLUMN "RB_USERBACK"."DATETIME" IS '上传时间'
/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('RB_ZEUS_TV') ;
    if num > 0 then
        execute immediate 'drop table RB_ZEUS_TV' ;
    end if;
    end;
/
CREATE TABLE "RB_ZEUS_TV" (
"ID" VARCHAR2(255 BYTE) NOT NULL ,
"ZS_NAME" VARCHAR2(255 BYTE) NOT NULL ,
"ZS_SERVEURL" VARCHAR2(255 BYTE) NOT NULL ,
"IS_OPEN" NUMBER DEFAULT 1  NULL ,
"DATETIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "RB_ZEUS_TV"."ID" IS '主键ID'
/
COMMENT ON COLUMN "RB_ZEUS_TV"."ZS_NAME" IS '宙斯名称'
/
COMMENT ON COLUMN "RB_ZEUS_TV"."ZS_SERVEURL" IS '宙斯地址'
/
COMMENT ON COLUMN "RB_ZEUS_TV"."IS_OPEN" IS '（0：不可用，1：可用）默认为1'
/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('TB_ZHOUSI_IM_REMIND') ;
    if num > 0 then
        execute immediate 'drop table TB_ZHOUSI_IM_REMIND' ;
    end if;
    end;
/
CREATE TABLE "TB_ZHOUSI_IM_REMIND" (
"USER_ID" VARCHAR2(255 BYTE) NULL ,
"TYPE" NUMBER(1) DEFAULT 0  NULL ,
"OTHER_USER_ID" VARCHAR2(255 BYTE) NULL ,
"OTHER_ROOM_ID" VARCHAR2(255 BYTE) NULL ,
"IS_REMIND" VARCHAR2(255 BYTE) NULL ,
"DATETIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "TB_ZHOUSI_IM_REMIND"."TYPE" IS '0不提醒1提醒'
/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('TB_ZHOUSI_IM_ROOM_SPEAK') ;
    if num > 0 then
        execute immediate 'drop table TB_ZHOUSI_IM_ROOM_SPEAK' ;
    end if;
    end;
/
CREATE TABLE "TB_ZHOUSI_IM_ROOM_SPEAK" (
"ROOM_ID" VARCHAR2(255 BYTE) NOT NULL ,
"CAN_SPEAK" NUMBER(1) DEFAULT 0  NULL ,
"USER_ID" VARCHAR2(255 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "TB_ZHOUSI_IM_ROOM_SPEAK"."CAN_SPEAK" IS '不能说话,0 不能发言  1 可以发言'
/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('TB_ZHOUSI_IM_ROOM_STATE') ;
    if num > 0 then
        execute immediate 'drop table TB_ZHOUSI_IM_ROOM_STATE' ;
    end if;
    end;
/
CREATE TABLE "TB_ZHOUSI_IM_ROOM_STATE" (
"ROOM_ID" VARCHAR2(255 BYTE) NOT NULL ,
"IS_INVITE" NUMBER(1) DEFAULT 1  NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "TB_ZHOUSI_IM_ROOM_STATE"."IS_INVITE" IS '默认需要邀请，0不需要  1需要  '
/


declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('TB_ZHOUSI_IM_TOP') ;
    if num > 0 then
        execute immediate 'drop table TB_ZHOUSI_IM_TOP' ;
    end if;
    end;
/
CREATE TABLE "TB_ZHOUSI_IM_TOP" (
"USER_ID" VARCHAR2(255 BYTE) NULL ,
"TOP_TYPE" NUMBER(1) NULL ,
"OTHER_USER_ID" VARCHAR2(255 BYTE) NULL ,
"OTHER_ROOM_ID" VARCHAR2(255 BYTE) NULL ,
"DATETIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "TB_ZHOUSI_IM_TOP"."TOP_TYPE" IS '设置类型（1个人，2群组）'
/


declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('TB_ZHOUSI_IM_USER') ;
    if num > 0 then
        execute immediate 'drop table TB_ZHOUSI_IM_USER' ;
    end if;
    end;
/
CREATE TABLE "TB_ZHOUSI_IM_USER" (
"USER_ID" VARCHAR2(255 BYTE) NOT NULL ,
"USER_NAME" VARCHAR2(50 BYTE) NULL ,
"GENDER" VARCHAR2(50 BYTE) NULL ,
"TELPHONE" VARCHAR2(50 BYTE) NULL ,
"DEPT_ID" VARCHAR2(50 BYTE) NULL ,
"DEPT_NAME" VARCHAR2(50 BYTE) NULL ,
"ICON" VARCHAR2(300 BYTE) NULL ,
"ZORDER" VARCHAR2(50 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/
COMMENT ON COLUMN "TB_ZHOUSI_IM_USER"."DEPT_ID" IS '单位编码'
/
COMMENT ON COLUMN "TB_ZHOUSI_IM_USER"."DEPT_NAME" IS '单位名称'
/

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('TB_ZHOUSI_IM_USER_NIKENAME') ;
    if num > 0 then
        execute immediate 'drop table TB_ZHOUSI_IM_USER_NIKENAME' ;
    end if;
    end;
/
CREATE TABLE "TB_ZHOUSI_IM_USER_NIKENAME" (
"ROOM_ID" VARCHAR2(255 BYTE) NOT NULL ,
"USER_ID" VARCHAR2(255 BYTE) NOT NULL ,
"USER_NIKENAME" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

/

ALTER TABLE "RB_APK_DOWNLOAD" ADD CHECK ("ID" IS NOT NULL)
/

ALTER TABLE "RB_APK_DOWNLOAD" ADD PRIMARY KEY ("ID")
/

ALTER TABLE "RB_APK_INFO" ADD CHECK ("ID" IS NOT NULL)
/

ALTER TABLE "RB_APK_INFO" ADD PRIMARY KEY ("ID")
/

ALTER TABLE "RB_CONFIG" ADD CHECK ("ID" IS NOT NULL)
/
ALTER TABLE "RB_CONFIG" ADD CHECK ("NAME" IS NOT NULL)
/

ALTER TABLE "RB_CONFIG" ADD PRIMARY KEY ("ID")
/

ALTER TABLE "RB_DOCUMENT" ADD CHECK ("ID" IS NOT NULL)
/

ALTER TABLE "RB_DOCUMENT" ADD PRIMARY KEY ("ID")
/

ALTER TABLE "RB_ERROR_LOG" ADD CHECK ("ID" IS NOT NULL)
/

ALTER TABLE "RB_ERROR_LOG" ADD PRIMARY KEY ("ID")
/

ALTER TABLE "RB_LOG" ADD CHECK ("ID" IS NOT NULL)
/

ALTER TABLE "RB_LOG" ADD PRIMARY KEY ("ID")
/

ALTER TABLE "RB_NAV_CONGFIG" ADD CHECK ("ID" IS NOT NULL)
/

ALTER TABLE "RB_NAV_CONGFIG" ADD PRIMARY KEY ("ID")
/

ALTER TABLE "RB_PLUG" ADD CHECK ("PLUG_ID" IS NOT NULL)
/
ALTER TABLE "RB_PLUG" ADD CHECK ("PLUG_ID" IS NOT NULL)
/
ALTER TABLE "RB_PLUG" ADD CHECK ("PLUG_NAME" IS NOT NULL)
/
ALTER TABLE "RB_PLUG" ADD CHECK ("PLUG_TYPE" IS NOT NULL)
/
ALTER TABLE "RB_PLUG" ADD CHECK ("PLUG_URL" IS NOT NULL)
/

ALTER TABLE "RB_PLUG" ADD PRIMARY KEY ("PLUG_ID")
/

ALTER TABLE "RB_PLUG_AND_C" ADD CHECK ("PLUG_ID" IS NOT NULL)
/
ALTER TABLE "RB_PLUG_AND_C" ADD CHECK ("CATEGORY_ID" IS NOT NULL)
/
ALTER TABLE "RB_PLUG_AND_C" ADD CHECK ("PLUG_ID" IS NOT NULL)
/
ALTER TABLE "RB_PLUG_AND_C" ADD CHECK ("CATEGORY_ID" IS NOT NULL)
/

ALTER TABLE "RB_PLUG_AND_P" ADD CHECK ("PLUG_ID" IS NOT NULL)
/
ALTER TABLE "RB_PLUG_AND_P" ADD CHECK ("PERMISSION_ID" IS NOT NULL)
/

ALTER TABLE "RB_PLUG_AUDIT" ADD CHECK ("PLUG_TEMP_ID" IS NOT NULL)
/
ALTER TABLE "RB_PLUG_AUDIT" ADD CHECK ("PLUG_NAME" IS NOT NULL)
/
ALTER TABLE "RB_PLUG_AUDIT" ADD CHECK ("PLUG_TYPE" IS NOT NULL)
/
ALTER TABLE "RB_PLUG_AUDIT" ADD CHECK ("PLUG_URL" IS NOT NULL)
/

ALTER TABLE "RB_PLUG_AUDIT" ADD PRIMARY KEY ("PLUG_TEMP_ID")
/

ALTER TABLE "RB_PLUG_CATEGORY" ADD CHECK ("CATEGORY_ID" IS NOT NULL)
/
ALTER TABLE "RB_PLUG_CATEGORY" ADD CHECK ("CATEGORY_NAME" IS NOT NULL)
/
ALTER TABLE "RB_PLUG_CATEGORY" ADD CHECK ("CATEGORY_ID" IS NOT NULL)
/
ALTER TABLE "RB_PLUG_CATEGORY" ADD CHECK ("CATEGORY_NAME" IS NOT NULL)
/
ALTER TABLE "RB_PLUG_CATEGORY" ADD CHECK ("CATEGORY_DES" IS NOT NULL)
/

ALTER TABLE "RB_PLUG_CATEGORY" ADD PRIMARY KEY ("CATEGORY_ID")
/

ALTER TABLE "RB_PLUG_PREMISSION" ADD CHECK ("PERMISSION_ID" IS NOT NULL)
/
ALTER TABLE "RB_PLUG_PREMISSION" ADD CHECK ("PERMISSION_NAME" IS NOT NULL)
/
ALTER TABLE "RB_PLUG_PREMISSION" ADD CHECK ("PERMISSION_DES" IS NOT NULL)
/

ALTER TABLE "RB_PLUG_PREMISSION" ADD PRIMARY KEY ("PERMISSION_ID")
/

ALTER TABLE "RB_PLUG_USER" ADD CHECK ("PLUG_ID" IS NOT NULL)
/
ALTER TABLE "RB_PLUG_USER" ADD CHECK ("USER_ID" IS NOT NULL)
/
ALTER TABLE "RB_PLUG_USER" ADD CHECK ("PLUG_ID" IS NOT NULL)
/
ALTER TABLE "RB_PLUG_USER" ADD CHECK ("USER_ID" IS NOT NULL)
/

ALTER TABLE "RB_RC_INFO" ADD CHECK ("MESSAGE_ID" IS NOT NULL)
/

ALTER TABLE "RB_RC_INFO" ADD PRIMARY KEY ("MESSAGE_ID")
/

ALTER TABLE "RB_RC_MEMBER" ADD CHECK ("PARTICIPATE_ID" IS NOT NULL)
/

ALTER TABLE "RB_RC_MEMBER" ADD PRIMARY KEY ("PARTICIPATE_ID")
/

ALTER TABLE "RB_RUN_CONFIG" ADD CHECK ("ID" IS NOT NULL)
/

ALTER TABLE "RB_RUN_CONFIG" ADD PRIMARY KEY ("ID")
/

ALTER TABLE "RB_RUN_MODE" ADD CHECK ("MODE_EQUIPMENT" IS NOT NULL)
/

ALTER TABLE "RB_RUN_MODE" ADD PRIMARY KEY ("MODE_EQUIPMENT")
/

ALTER TABLE "RB_SCORE" ADD CHECK ("ID" IS NOT NULL)
/

ALTER TABLE "RB_SCORE" ADD PRIMARY KEY ("ID")
/

ALTER TABLE "RB_SCORE_TAG" ADD CHECK ("TAG" IS NOT NULL)
/

ALTER TABLE "RB_SCORE_TAG" ADD PRIMARY KEY ("TAG")
/

ALTER TABLE "RB_SERVER_INFO" ADD CHECK ("ID" IS NOT NULL)
/
ALTER TABLE "RB_SERVER_INFO" ADD CHECK ("SERVER_NAME" IS NOT NULL)
/
ALTER TABLE "RB_SERVER_INFO" ADD CHECK ("SERVER_URL" IS NOT NULL)
/

ALTER TABLE "RB_SERVER_INFO" ADD PRIMARY KEY ("ID")
/

ALTER TABLE "RB_USERBACK" ADD CHECK ("ID" IS NOT NULL)
/

ALTER TABLE "RB_USERBACK" ADD PRIMARY KEY ("ID")
/

ALTER TABLE "RB_ZEUS_TV" ADD CHECK ("ID" IS NOT NULL)
/
ALTER TABLE "RB_ZEUS_TV" ADD CHECK ("ZS_NAME" IS NOT NULL)
/
ALTER TABLE "RB_ZEUS_TV" ADD CHECK ("ZS_SERVEURL" IS NOT NULL)
/


ALTER TABLE "RB_ZEUS_TV" ADD PRIMARY KEY ("ID")
/


ALTER TABLE "TB_ZHOUSI_IM_ROOM_SPEAK" ADD CHECK ("ROOM_ID" IS NOT NULL)
/
ALTER TABLE "TB_ZHOUSI_IM_ROOM_SPEAK" ADD CHECK ("USER_ID" IS NOT NULL)
/


ALTER TABLE "TB_ZHOUSI_IM_ROOM_SPEAK" ADD PRIMARY KEY ("ROOM_ID", "USER_ID")
/


ALTER TABLE "TB_ZHOUSI_IM_ROOM_STATE" ADD CHECK ("ROOM_ID" IS NOT NULL)
/

ALTER TABLE "TB_ZHOUSI_IM_ROOM_STATE" ADD PRIMARY KEY ("ROOM_ID")
/

ALTER TABLE "TB_ZHOUSI_IM_USER" ADD CHECK ("USER_ID" IS NOT NULL)
/


ALTER TABLE "TB_ZHOUSI_IM_USER" ADD PRIMARY KEY ("USER_ID")
/



ALTER TABLE "TB_ZHOUSI_IM_USER_NIKENAME" ADD CHECK ("ROOM_ID" IS NOT NULL)
/
ALTER TABLE "TB_ZHOUSI_IM_USER_NIKENAME" ADD CHECK ("USER_ID" IS NOT NULL)
/


ALTER TABLE "TB_ZHOUSI_IM_USER_NIKENAME" ADD PRIMARY KEY ("USER_ID", "ROOM_ID")
/


ALTER TABLE "RB_PLUG_AND_C" ADD FOREIGN KEY ("CATEGORY_ID") REFERENCES "RB_PLUG_CATEGORY" ("CATEGORY_ID")
/
ALTER TABLE "RB_PLUG_AND_C" ADD FOREIGN KEY ("PLUG_ID") REFERENCES "RB_PLUG" ("PLUG_ID")
/

ALTER TABLE "RB_PLUG_AND_P" ADD FOREIGN KEY ("PLUG_ID") REFERENCES "RB_PLUG" ("PLUG_ID")
/
ALTER TABLE "RB_PLUG_AND_P" ADD FOREIGN KEY ("PERMISSION_ID") REFERENCES "RB_PLUG_PREMISSION" ("PERMISSION_ID")
/

ALTER TABLE "RB_PLUG_AUDIT" ADD FOREIGN KEY ("PLUG_ID") REFERENCES "RB_PLUG" ("PLUG_ID")
/
ALTER TABLE "RB_PLUG_USER" ADD FOREIGN KEY ("PLUG_ID") REFERENCES "RB_PLUG" ("PLUG_ID")
/