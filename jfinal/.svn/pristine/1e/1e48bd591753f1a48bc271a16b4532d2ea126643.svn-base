package com.xiaoan.wlt.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.upload.UploadFile;

/**
 * ueditor控制器，包括返回配置文件，上传图片处理等
 * 
 * @author LeeSin
 *
 */
public class UeditorController extends BaseController {
	private static final String RESOURCE_DIR;
	static {
		RESOURCE_DIR = PropKit.get("RESOURCE_DIR");
	}
	public void config() {
		try {
			renderJson(readFile(forward2backSlash(PathKit.getWebRootPath())
					+ "/static/ueditor-1.4.3.3/jsp/config.json"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void index() {
		render("/ueditor.jsp");
	}
	
	public void uploadImage() {
		List<UploadFile> fileList = getFiles(RESOURCE_DIR);
		String imgpath = forward2backSlash(PathKit.getWebRootPath())
				+ RESOURCE_DIR + "/";
		String title = "";
		String fname = "";
		String newFileName = "";
		String state = "SUCCESS";
		if (fileList != null && fileList.size() > 0) {
			UploadFile file = (UploadFile) fileList.get(0);
			UploadFile f = (UploadFile) file;
			// 获取文件名
			fname = f.getFileName();
			// 获取的是.jpg
			String suwf = fname.substring(fname.lastIndexOf("."),
					fname.length());
			// 使用时间戳生成新的文件名
			newFileName = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date()) + (int) (Math.random() * 99) + suwf;
			File destFile = new File(imgpath + newFileName);
			try {
				FileUtils.moveFile(f.getFile(), destFile);
				// f.getFile().renameTo(new File(imgpath + newFileName));
				Iterator<ImageReader> readers = ImageIO
						.getImageReadersByFormatName(suwf.substring(1,
								suwf.length()));
				ImageReader reader = (ImageReader) readers.next();
				ImageInputStream iis = ImageIO.createImageInputStream(destFile);
				reader.setInput(iis, true);
				iis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 实际上是原图片名
			title = getPara("pictitle");
			if (title == null || title.trim().length() == 0) {
				title = fname;
			}
		} else {
			state = "";
		}
		// title = title.replace("&", "&amp;").replace("'", "&qpos;")
		// .replace("\"", "").replace("<", "&lt;").replace(">", "&gt;");
		// renderText("{'original':'" + fname + "','url':'http://"
		// + getRequest().getServerName() + ":"
		// + getRequest().getServerPort() + getRequest().getContextPath()
		// + RESOURCE_DIR + "/" + newFileName + "','title':'" + title
		// + "','state':'" + state + "'}");
		// url,type,original
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("url", "http://" + getRequest().getServerName() + ":"
				+ getRequest().getServerPort() + getRequest().getContextPath()
				+ RESOURCE_DIR + "/" + newFileName);
		jsonObj.put("type", ".jpg");
		jsonObj.put("original", fname);
		jsonObj.put("state", state);
		renderJson(jsonObj);
	}

	private String forward2backSlash(String src) {
		// return src.replaceAll("\\\\", "/");
		return src.replaceAll(Matcher.quoteReplacement("\\"),
				Matcher.quoteReplacement("/"));
	}

	private String readFile(String path) throws IOException {
		StringBuilder builder = new StringBuilder();
		try {

			InputStreamReader reader = new InputStreamReader(
					new FileInputStream(path), "UTF-8");
			BufferedReader bfReader = new BufferedReader(reader);

			String tmpContent = null;

			while ((tmpContent = bfReader.readLine()) != null) {
				builder.append(tmpContent);
			}
			bfReader.close();

		} catch (UnsupportedEncodingException e) {
			// 忽略
		}
		return this.filter(builder.toString());
	}

	// 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
	private String filter(String input) {

		return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");

	}
}
