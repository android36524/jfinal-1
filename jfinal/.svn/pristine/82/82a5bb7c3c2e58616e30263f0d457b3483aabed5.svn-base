package com.xiaoan.wlt.controller.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresUser;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.upload.UploadFile;
import com.xiaoan.wlt.controller.BaseController;
import com.xiaoan.wlt.utils.Common;

/**
 * 
 * @author liangjiahong
 * @date 2016年11月14日
 */
@RequiresUser
public class UploadController extends BaseController {

	private static final String[] IMAGE_EXTS = { ".bmp", ".jpg", ".jpeg",
			".png", ".gif" };

	public void imageUpload() {

		UploadFile uploadFile = getFile();
		// 异步上传时，无法通过uploadFile.getFileName()获取文件名
		String fileName = getPara("fileName");
		fileName = fileName.substring(fileName.lastIndexOf("\\") + 1); // 去掉路径

		// 异步上传时，无法通过File source = uploadFile.getFile();获取文件
		File source = uploadFile.getFile();

		String extension = fileName.substring(fileName.lastIndexOf("."));
		String savePath = PathKit.getWebRootPath() + "/upload/images/"
				+ Common.getCurrentDate();
		JSONObject json = new JSONObject();

		if (".png".equals(extension) || ".jpg".equals(extension)
				|| ".gif".equals(extension) || "jpeg".equals(extension)
				|| "bmp".equals(extension)) {
			fileName = Common.getCurrentTime() + extension;

			try {
				FileInputStream fis = new FileInputStream(source);

				File targetDir = new File(savePath);
				if (!targetDir.exists()) {
					targetDir.mkdirs();
				}

				File target = new File(targetDir, fileName);
				if (!target.exists()) {
					target.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(target);
				byte[] bts = new byte[1024 * 20];
				while (fis.read(bts, 0, 1024 * 20) != -1) {
					fos.write(bts, 0, 1024 * 20);
				}

				fos.close();
				fis.close();
				json.put("error", 0);
				json.put("src", "upload/images/" + Common.getCurrentDate()
						+ "/" + fileName); // 相对地址，显示图片用
				source.delete();

			} catch (FileNotFoundException e) {
				json.put("error", 1);
				json.put("message", "上传出现错误，请稍后再上传");
			} catch (IOException e) {
				json.put("error", 1);
				json.put("message", "文件写入服务器出现错误，请稍后再上传");
			}
		} else {
			source.delete();
			json.put("error", 1);
			json.put("message", "只允许上传png,jpg,jpeg,gif,bmp类型的图片文件");
		}

		renderJson(json.toJSONString());
	}

	public void uploadHead() {

		UploadFile uploadFile = getFile();
		// 异步上传时，无法通过uploadFile.getFileName()获取文件名
		String fileName = getPara("fileName");
		fileName = fileName.substring(fileName.lastIndexOf("\\") + 1); // 去掉路径
		// 异步上传时，无法通过File source = uploadFile.getFile();获取文件
		File source = uploadFile.getFile();
		String extension = fileName.substring(fileName.lastIndexOf("."));
		String savePath = PathKit.getWebRootPath() + "/upload/images/head";
		JSONObject json = new JSONObject();
		if (Arrays.asList(IMAGE_EXTS).indexOf(extension) >= 0) {
			fileName = Common.getCurrentTime() + extension;
			try {
				FileInputStream fis = new FileInputStream(source);

				File targetDir = new File(savePath);
				if (!targetDir.exists()) {
					targetDir.mkdirs();
				}

				File target = new File(targetDir, fileName);
				if (!target.exists()) {
					target.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(target);
				byte[] bts = new byte[1024 * 20];
				while (fis.read(bts, 0, 1024 * 20) != -1) {
					fos.write(bts, 0, 1024 * 20);
				}

				fos.close();
				fis.close();
				json.put("error", 0);
				json.put("src", "/upload/images/head/" + fileName); // 相对地址，显示图片用
				source.delete();

			} catch (FileNotFoundException e) {
				json.put("error", 1);
				json.put("message", "上传出现错误，请稍后再上传");
			} catch (IOException e) {
				json.put("error", 1);
				json.put("message", "文件写入服务器出现错误，请稍后再上传");
			}
		} else {
			source.delete();
			json.put("error", 1);
			json.put("message", "只允许上传png,jpg,jpeg,gif,bmp类型的图片文件");
		}

		renderJson(json.toJSONString());
	}
	/**
	 * 资源库分类图标
	 */
	public void uploadCatIcon() {
		String path = "CAT_ICON_PATH";
		picUpload(path);
	}
	/**
	 * 资源主图上传
	 */
	public void mainPic() {
		String path = "RESOURCE_MAIN_PIC_DIR";
		picUpload(path);
	}

	private void picUpload(String path) {
		UploadFile uploadFile = getFile();
		String fileName = uploadFile.getFileName();
		fileName = fileName.substring(fileName.lastIndexOf("\\") + 1); // 去掉路径
		File source = uploadFile.getFile();
		String extension = fileName.substring(fileName.lastIndexOf("."));
		String savePath = forward2backSlash(PathKit.getWebRootPath()
				+ PropKit.get(path));
		String newFileName = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date()) + (int) (Math.random() * 99) + extension;
		File destFile = new File(savePath + "/" + newFileName);
		JSONObject json = new JSONObject();

		if (Arrays.asList(IMAGE_EXTS).indexOf(extension) >= 0) {
			try {
				FileUtils.moveFile(source, destFile);
				Iterator<ImageReader> readers = ImageIO
						.getImageReadersByFormatName(extension.substring(1,
								extension.length()));
				ImageReader reader = (ImageReader) readers.next();
				ImageInputStream iis = ImageIO.createImageInputStream(destFile);
				reader.setInput(iis, true);
				iis.close();
				
				json.put("error", 0);
				json.put("src", PropKit.get(path) + "/" + newFileName); // 相对地址，显示图片用
			} catch (FileNotFoundException e) {
				json.put("error", 1);
				json.put("message", "上传出现错误，请稍后再上传");
			} catch (IOException e) {
				json.put("error", 1);
				json.put("message", "文件写入服务器出现错误，请稍后再上传");
			}
		} else {
			source.delete();
			json.put("error", 1);
			json.put("message", "只允许上传png,jpg,jpeg,gif,bmp类型的图片文件");
		}

		renderJson(json.toJSONString());
	}

	private String forward2backSlash(String src) {
		// return src.replaceAll("\\\\", "/");
		return src.replaceAll(Matcher.quoteReplacement("\\"),
				Matcher.quoteReplacement("/"));
	}
}
