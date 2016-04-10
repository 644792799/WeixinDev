package net.sensof.weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sensof.weixin.global.CommonConst;
import net.sensof.weixin.po.News;
import net.sensof.weixin.po.NewsMessage;
import net.sensof.weixin.po.TextMessage;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

public class MessageUtil {
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		Element root = doc.getRootElement();
		List<Element> list = root.elements();
		for(Element e : list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	
	public static String textMessageToXml(TextMessage textMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", TextMessage.class);
		return xstream.toXML(textMessage);
	}
	
	public static String initText(String toUserName, String fromUserName, String content){
		TextMessage textMessage = new TextMessage();
		textMessage.setFromUserName(toUserName);
		textMessage.setToUserName(fromUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(CommonConst.MSGTYPE_TEXT);
		textMessage.setContent(content);
		return textMessageToXml(textMessage);
	}
	
	public static String menuText(){
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎您的关注，请按照菜单提示进行操作: \n\n");
		sb.append("1:回复1查看最新文章\n");
		sb.append("2:回复2查看历史文章\n");
		sb.append("3:回复？或?调出主菜单\n");
		return sb.toString();
	}
	
	public static String firstMenuText(){
		StringBuffer sb = new StringBuffer();
		sb.append("12121212");
		return sb.toString();
	}
	
	public static String secondMenuText(){
		StringBuffer sb = new StringBuffer();
		sb.append("4343434343");
		return sb.toString();
	}
	/**
	 * 
	 * @param newsMessage
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", NewsMessage.class);
		xstream.alias("item", News.class);
		return xstream.toXML(newsMessage);
	}
	
	/**
	 * 图文消息组装
	 * @param toUserName
	 * @param fromUserName
	 * @param content
	 * @return
	 */
	public static String initNewsMessage(String toUserName, String fromUserName){
		List<News> newsList = new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();
		
		News news = new News();
		news.setTitle("一条图文消息");
		news.setDescription("这是图文消息描述");
		news.setPicUrl("http://dd29755f.ngrok.io/WeixinDev/image/dm.jpg");
		news.setUrl("www.baidu.com");
		
		newsList.add(news);
		
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());
		newsMessage.setMsgType(CommonConst.MSGTYPE_NEWS);
		
		return newsMessageToXml(newsMessage);
	}
}
