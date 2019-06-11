package com.wd.tech.app;

/**
 * @Author：Chen
 * @E-mail： 1850915912@qq.com
 * @Date：2019/3/15 11:29
 * @Description：描述信息
 */
public class StaticClass {

    /*
     * 全局参数存储类 添加参数请做好注释具体作用在哪
     */
    public static int NetState = 0;  //断网判断当前所属页面标识

    /*
     * 当前请求所要用到URL 作为静态调用 请做好注释属于哪个接口
     */

    public static String REGISTER = "techApi/user/v1/register";  //注册接口
    public static String LOGIN = "techApi/user/v1/login";  //登录接口
    public static String USERMESSAGE = "techApi/user/verify/v1/getUserInfoByUserId"; //根据ID查询用户所有信息




    public static String addFriend = "techApi/chat/verify/v1/addFriend";  //添加好友
    public static String deleteFriendRelation = "techApi/chat/verify/v1/deleteFriendRelation";  //删除好友
    public static String modifyFriendRemark = "techApi/chat/verify/v1/modifyFriendRemark";  //修改好友备注
    public static String checkMyFriend = "techApi/chat/verify/v1/checkMyFriend";  //检测是否为我的好友
    public static String addFriendGroup = "techApi/chat/verify/v1/addFriendGroup"; //创建自定义好友分组
    public static String findFriendGroupList = "techApi/chat/verify/v1/findFriendGroupList";//查询用户所有分组
    public static String modifyGroupName = "techApi/chat/verify/v1/modifyGroupName";//修改好友分组名称
    public static String transferFriendGroup = "techApi/chat/verify/v1/transferFriendGroup";//转移好友到其他分组
    public static String deleteFriendGroup = "techApi/chat/verify/v1/deleteFriendGroup";//删除用户好友分组
    public static String findFriendListByGroupId = "techApi/chat/verify/v1/findFriendListByGroupId";//查询分组下的好友列表信息
    public static String findFriendNoticePageList = "techApi/chat/verify/v1/findFriendNoticePageList";//查询用户的好友通知记录
    public static String reviewFriendApply = "techApi/chat/verify/v1/reviewFriendApply";//审核好友申请
    public static String sendMessage = "techApi/chat/verify/v1/sendMessage";//发送消息
    public static String findChatRecordPageList = "techApi/chat/verify/v1/findChatRecordPageList";//查询好友聊天记录
    public static String findDialogueRecordPageList = "techApi/chat/verify/v1/findDialogueRecordPageList";//查询好友对话记录
    public static String deleteChatRecord = "techApi/chat/verify/v1/deleteChatRecord";//删除好友聊天记录
    public static String searchFriend = "techApi/chat/verify/v1/searchFriend";//查询我的好友列表
    public static String initFriendList = "techApi/chat/verify/v1/initFriendList";//初始化我的好友列表全量信息
    public static String createGroup = "techApi/group/verify/v1/createGroup";//创建群
    public static String modifyGroupName2 = "techApi/group/verify/v1/modifyGroupName";//修改群组名
    public static String modifyGroupDescription = "techApi/group/verify/v1/modifyGroupDescription";//修改群简介
    public static String disbandGroup = "techApi/group/verify/v1/disbandGroup";//解散群组
    public static String findGroupsByUserId = "techApi/group/verify/v1/findGroupsByUserId";//查询我创建的群组
    public static String findUserJoinedGroup = "techApi/group/verify/v1/findUserJoinedGroup";//查询我所有加入的群组
    public static String findGroupMemberList = "techApi/group/verify/v1/findGroupMemberList";//查询群组内所有用户信息
    public static String findGroupInfo = "techApi/group/verify/v1/findGroupInfo";//查询群组详细信息
    public static String sendGroupMessage = "techApi/group/verify/v1/sendGroupMessage";//发送群信息
    public static String findGroupChatRecordPage = "techApi/group/verify/v1/findGroupChatRecordPage";//查询群聊天内容
    public static String removeGroupMember = "techApi/group/verify/v1/removeGroupMember";//移出群成员(管理员与群主才有的权限)
    public static String modifyPermission = "techApi/group/verify/v1/modifyPermission";//调整群成员角色(群主才有的权限)
    public static String whetherInGroup = "techApi/group/verify/v1/whetherInGroup";//判断用户是否已在群内
    public static String applyAddGroup = "techApi/group/verify/v1/applyAddGroup";//申请进群
    public static String inviteAddGroup = "techApi/group/verify/v1/inviteAddGroup";//邀请加群
    public static String batchInviteAddGroup = "techApi/group/verify/v1/batchInviteAddGroup";//批量邀请加群
    public static String findGroupNoticePageList = "techApi/group/verify/v1/findGroupNoticePageList";//查询群通知记录
    public static String reviewGroupApply = "techApi/group/verify/v1/reviewGroupApply";//审核群申请
    public static String uploadGroupHeadPic = "techApi/group/verify/v1/uploadGroupHeadPic";//上传群头像
    public static String retreat = "techApi/group/verify/v1/retreat";//退群





    /*
     * 下面是全局静态常量 userID sessionId 可以直接类名点进行调用 切勿进行值的覆盖或者更改 指空等操作
     */
    public static int userId = 0;
    public static String sessionId = null;

}
