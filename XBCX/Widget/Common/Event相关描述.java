/**
 * Title: Event相关描述
 * Tag: Event, tips
 * Update: 2015/07/06
 * Description: 介绍了一些Event相关的知识
 */


/*
1. EventManager is Singleton, 可以从Application的任何位置获取，并且pushEvent.
2.调用XBaseActivity中的addAndManageEventListener 方法，不需要手动注销Lisnter，如果是直接使用EventManager注册Lisnter，需要在onDestory中注销。
*/
