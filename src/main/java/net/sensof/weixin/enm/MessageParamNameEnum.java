/**
 * 
 */
package net.sensof.weixin.enm;

/**
 * XML消息属性名称
 * @author Administrator
 *
 */
public enum MessageParamNameEnum {
	ToUserName("ToUserName"),
	FromUserName("FromUserName"),
	CreateTime("CreateTime"),
	MsgType("MsgType"),
	Content("Content"),
	MsgId("MsgId"),
	Event("Event");
	
	private String context;

	public String getContext() {
		return context;
	}
	
	private MessageParamNameEnum(String context){
		this.context = context;
	}
}
