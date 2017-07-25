package com.agjsj.fleamarket.params;


public class GlobalParams {

	public static boolean DEBUG = true;

	/**
	 * APN的代理
	 */
	public static String PROXY = "";
	
	/**
	 * APN的端口
	 */
	public static int PORT = 0;
	
	/**
	 * 存储获取服务器的结果
	 */
//	public static Message result = null;

	public static int WIN_WIDTH = 480;
	/**
	 * 
	 */
	public static String DESPASSWORD = "d6f9c12f970b843a";

	public static String SPLIT_IMAGE_URL = ";";

	public static final int PAGE_SIZE = 20;


	/**
	 * View Type
	 */
	public static final int VIEW_SCHOOL = 0002;
	public static final int VIEW_MESSSAGE = 0003;
	public static final int VIEW_HOME = 0001;
	public static final int VIEW_SEARCH = 1000;
	public static final int VIEW_GOODSDETAIL = 1001;
	public static final int VIEW_ME = 0004;
	public static final int VIEW_MY_SEND_GOODS = 40001;
	public static final int VIEW_MY_SEND_FOUNDCASE = 40002;


	public static final String MESSAGE_ATTR_IS_VOICE_CALL = "is_voice_call";
	public static final String MESSAGE_ATTR_IS_VIDEO_CALL = "is_video_call";

	public static final String MESSAGE_ATTR_IS_BIG_EXPRESSION = "em_is_big_expression";
	public static final String MESSAGE_ATTR_EXPRESSION_ID = "em_expression_id";



	public static final int CHATTYPE_SINGLE = 1;
	public static final int CHATTYPE_GROUP = 2;
	public static final int CHATTYPE_CHATROOM = 3;

	public static final String EXTRA_CHAT_TYPE = "chatType";
	public static final String EXTRA_USER_ID = "userId";
	public static final String NEW_FRIENDS_USERNAME = "item_new_friends";
	public static final String GROUP_USERNAME = "item_groups";
	public static final String CHAT_ROOM = "item_chatroom";
	public static final String ACCOUNT_REMOVED = "account_removed";
	public static final String ACCOUNT_CONFLICT = "conflict";
	public static final String CHAT_ROBOT = "item_robots";
	public static final String MESSAGE_ATTR_ROBOT_MSGTYPE = "msgtype";
	public static final String ACTION_GROUP_CHANAGED = "action_group_changed";
	public static final String ACTION_CONTACT_CHANAGED = "action_contact_changed";

	//Myinfo中保存环信账号
	public static final String KEY_USERNAME = "username";


}
