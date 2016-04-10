package net.sensof.weixin.test;

import java.io.File;
import java.util.Date;

import net.sensof.weixin.global.CommonConst;
import net.sensof.weixin.po.AccessToken;
import net.sensof.weixin.util.FileUtil;
import net.sensof.weixin.util.WeixinUtil;

public class WeixinTest {

	public static void main(String[] args) {
		String rootPath = System.getProperty("user.dir");
		String fileFullPath = rootPath + File.separator + CommonConst.TOKEN_FILE_NAME;
		AccessToken token_json = FileUtil.fileToObject(fileFullPath);
		if(token_json != null){
			Date ndate = new Date();
			long diffTime = ndate.getTime() - token_json.getCreateTime();
			if(diffTime > token_json.getExpriesIn() * 1000){
				token_json = WeixinUtil.getAccessToken();
				FileUtil.jsonObjectToFile(token_json, rootPath, CommonConst.TOKEN_FILE_NAME);
			}
			System.out.println(token_json.getToken());
			System.out.println(token_json.getExpriesIn());
		}else{
			AccessToken token = WeixinUtil.getAccessToken();
			System.out.println(token.getToken());
			System.out.println(token.getExpriesIn());
			FileUtil.jsonObjectToFile(token, rootPath, CommonConst.TOKEN_FILE_NAME);
		}
	}

}
