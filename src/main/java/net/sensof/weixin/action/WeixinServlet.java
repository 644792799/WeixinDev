package net.sensof.weixin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import net.sensof.weixin.enm.MessageParamNameEnum;
import net.sensof.weixin.global.CommonConst;
import net.sensof.weixin.po.TextMessage;
import net.sensof.weixin.util.CheckUtil;
import net.sensof.weixin.util.MessageUtil;

public class WeixinServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		String signature = request.getParameter("signature");
		//时间戳
		String timestamp = request.getParameter("timestamp");
		//随机数
		String nonce = request.getParameter("nonce");
		//随机字符串
		String echostr = request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		if(CheckUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Map<String, String> map;
		PrintWriter out = response.getWriter();
		try {
			map = MessageUtil.xmlToMap(request);
			String toUserName = map.get(MessageParamNameEnum.ToUserName.getContext());
			String fromUserName = map.get(MessageParamNameEnum.FromUserName.getContext());
			String msgType = map.get(MessageParamNameEnum.MsgType.getContext());
			String content = map.get(MessageParamNameEnum.Content.getContext());
			
			String message = null;
			if(CommonConst.MSGTYPE_TEXT.equals(msgType)){
				if("1".equals(content)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMenuText());
				}else if("2".equals(content)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.secondMenuText());
				}else if("?".equals(content) || "？".equals(content)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}
			}else if(CommonConst.MSGTYPE_EVENT.equals(msgType)){
				String eventType = map.get(MessageParamNameEnum.Event.getContext());
				if(CommonConst.EVENT_CLICK.equals(eventType)){
					
				}else if(CommonConst.EVENT_LOCATION.equals(eventType)){
					
				}else if(CommonConst.EVENT_SCAN.equals(eventType)){
					
				}else if(CommonConst.EVENT_SUBSCRIBE.equals(eventType)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}else if(CommonConst.EVENT_UNSUBSCRIBE.equals(eventType)){
					
				}else if(CommonConst.EVENT_VIEW.equals(eventType)){
					
				}
			}
			out.print(message);
			System.out.println(message);
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally{
			out.close();
		}
	}
}
