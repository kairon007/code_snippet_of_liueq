## 关于IM的一些内容整理

*Update: 2015/07/07*

**IMModule**

> 初始化或释放IMKernal

**VCardProvider**

> 管理配置用户默认基础信息。
    
**IMKernel**

* PublicDatabaseManager(公用数据库管理)
* IMDatabaseManager(存储IM内容的数据库管理)
* IMConfigManager(设置IM行为的Manager)
* VCardProvider(管理配置用户默认基础信息)
* IMFilePathManager(IM文件传输Manager)
* RecentChatManager(最近聊天Manager)
* XIMSystem(包含了所有IM功能的系统，主要是注册了各种Runner)
* 注册了冲突以及密码错误的Activity
* 注册了冲突，密码错误，登陆失败Listener

