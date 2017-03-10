package jfinal;

import com.alibaba.fastjson.JSONObject;
import com.xiaoan.wlt.utils.Aes;
import com.xiaoan.wlt.utils.MD5Util;
import com.xiaoan.wlt.utils.Md5;
import com.xiaoan.wlt.utils.PostServer;

public class test {
	public static void main(String[] args) {
		
		
		System.out.println(MD5Util.getMD5String("888888"));
	}

	private static void tuling() {
		// 图灵网站上的secret
		String secret = "fa3db024728ef44e";
		// 图灵网站上的apiKey
		String apiKey = "3ef62d951e4643e5943125a5470e13c8";
		String cmd = "你好";// 测试用例
		// 待加密的json数据
		String data = "{\"key\":\"" + apiKey + "\",\"info\":\"" + cmd + "\"}";
		// 获取时间戳
		String timestamp = String.valueOf(System.currentTimeMillis());
		// 生成密钥
		String keyParam = secret + timestamp + apiKey;
		String key = Md5.MD5(keyParam);
		// 加密
		Aes mc = new Aes(key);
		data = mc.encrypt(data);
		// 封装请求参数
		JSONObject json = new JSONObject();
		json.put("key", apiKey);
		json.put("timestamp", timestamp);
		json.put("data", data);
		// 请求图灵api
		String result = PostServer.SendPost(json.toString(), "http://www.tuling123.com/openapi/api");
		System.out.println(result);
	}
}
