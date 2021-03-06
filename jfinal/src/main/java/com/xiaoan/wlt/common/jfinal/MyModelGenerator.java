package com.xiaoan.wlt.common.jfinal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.generator.ColumnMeta;
import com.jfinal.plugin.activerecord.generator.ModelGenerator;
import com.jfinal.plugin.activerecord.generator.TableMeta;
import com.xiaoan.wlt.common.WhereAndParas;
import com.xiaoan.wlt.utils.Common;


/**
 * 
 * @author liangjiahong
 * @date 2016年12月9日
 */

public class MyModelGenerator extends ModelGenerator {


	public MyModelGenerator(String modelPackageName, String baseModelPackageName, String modelOutputDir) {
		super(modelPackageName, baseModelPackageName, modelOutputDir);
	}
	
	protected String where = "\tpublic static final String TABLE = \" %s o \";%n";

	protected void genModelContent(TableMeta tableMeta) {
		StringBuilder ret = new StringBuilder();
		genPackage(tableMeta,ret);
		genImport(tableMeta, ret);
		genClassDefine(tableMeta, ret);
		genDao(tableMeta, ret);
//		genPage(tableMeta,ret);
//		genPageList(tableMeta,ret);
//		genSql(tableMeta, ret);
		
		ret.append(String.format("}%n"));
		tableMeta.modelContent = ret.toString();
	}
	
	protected void genPackage(TableMeta tableMeta, StringBuilder ret) {
		String tableName = tableMeta.name;
		String fullTabelName = StrKit.firstCharToUpperCase(StrKit.toCamelCase(tableName));
		String pack = fullTabelName.replace(tableMeta.modelName, "");
		pack = Common.underscoreName(pack);
		pack = "."+pack.toLowerCase().replace("_",".");
		ret.append(String.format(packageTemplate, modelPackageName + pack));
	}
	
	private void genPageList(TableMeta tableMeta, StringBuilder ret) {
		ret.append(String.format("\tpublic Page<%s> findPageList(%s %s) {%n",tableMeta.modelName,tableMeta.modelName,toLowerCaseFirstOne(tableMeta.modelName)));
		ret.append(String.format("\t\tWhereAndParas wp = %s.getWhereAndParas(%s);%n",tableMeta.modelName,toLowerCaseFirstOne(tableMeta.modelName)));
		ret.append(String.format("\t\treturn dao.paginate(%s.getPageNumber(),%s.getPageSize(),\"select * \",\"from\"+ %s.TABLE + \" o where 1=1 \" +wp.getWhere(),wp.getParas());%n",toLowerCaseFirstOne(tableMeta.modelName),toLowerCaseFirstOne(tableMeta.modelName),tableMeta.modelName));
		ret.append(String.format("\t}%n"));
	}

	private void genPage(TableMeta tableMeta, StringBuilder ret) {
		ret.append(String.format("\tprivate int pageNumber = 1;%n"));
		ret.append(String.format("\tprivate int pageSize = 15;%n"));
		ret.append(String.format("\tpublic int getPageSize() {return pageSize;}%n"));
		ret.append(String.format("\tpublic void setPageSize(int pageSize) {this.pageSize = pageSize;}%n"));
		ret.append(String.format("\tpublic int getPageNumber() {return pageNumber;}%n"));
		ret.append(String.format("\tpublic void setPageNumber(int pageNumber) {this.pageNumber = pageNumber;}%n"));
	}

	@Override
	protected void genImport(TableMeta tableMeta, StringBuilder ret) {
//		super.genImport(tableMeta, ret);
		ret.append(String.format(importTemplate, "org.apache.commons.lang3","StringUtils"));
		ret.append(String.format(importTemplate, "com.xiaoan.wlt.common","WhereAndParas"));
		ret.append(String.format(importTemplate, "java.util","ArrayList"));
		ret.append(String.format(importTemplate, "java.util","List"));
		ret.append(String.format(importTemplate, "com.jfinal.plugin.activerecord","Page"));
		
		
		String tableName = tableMeta.name;
		String fullTabelName = StrKit.firstCharToUpperCase(StrKit.toCamelCase(tableName));
		String pack = fullTabelName.replace(tableMeta.baseModelName.replace("Base", ""), "");
		pack = Common.underscoreName(pack);
		pack = "."+pack.toLowerCase().replace("_",".");
		
		ret.append(String.format(importTemplate, baseModelPackageName+pack,tableMeta.baseModelName));
		
		
		
	}

	private void genSql(TableMeta tableMeta, StringBuilder ret) {
		ret.append(String.format(where, tableMeta.name));
		ret.append(String.format("\tpublic static WhereAndParas getWhereAndParas(%s %s){%n",tableMeta.modelName,toLowerCaseFirstOne(tableMeta.modelName)));
		ret.append(String.format("\t\tStringBuffer sql = new StringBuffer();%n"));
		ret.append(String.format("\t\tList<Object> list = new ArrayList<Object>();%n"));
		ret.append(String.format("\t\tWhereAndParas wap = new WhereAndParas();%n"));
		for (ColumnMeta columnMeta : tableMeta.columnMetas) {
			if(columnMeta.javaType.indexOf("String")!= -1){
				ret.append(String.format("\t\tif(StringUtils.isNotEmpty(%s.get%s())){%n",toLowerCaseFirstOne(tableMeta.modelName),captureName(columnMeta.attrName)));
				ret.append(String.format("\t\t\tsql.append(\" and o.%s like ?\");%n",columnMeta.name));
				ret.append(String.format("\t\t\tlist.add(\"%s\"+%s.get%s()+\"%s\");%n","%",toLowerCaseFirstOne(tableMeta.modelName),captureName(columnMeta.attrName),"%"));
				ret.append(String.format("\t\t}%n"));
			}else if(columnMeta.javaType.indexOf("Integer") != -1 ||
					columnMeta.javaType.indexOf("Long") != -1 ||
					columnMeta.javaType.indexOf("Double") != -1 ||
					columnMeta.javaType.indexOf("Float") != -1){
				ret.append(String.format("\t\tif(%s.get%s() != null && %s.get%s() > 0){%n",toLowerCaseFirstOne(tableMeta.modelName),captureName(columnMeta.attrName),toLowerCaseFirstOne(tableMeta.modelName),captureName(columnMeta.attrName)));
				ret.append(String.format("\t\t\tsql.append(\" and o.%s = ?\");%n",columnMeta.name));
				ret.append(String.format("\t\t\tlist.add(%s.get%s());%n",toLowerCaseFirstOne(tableMeta.modelName),captureName(columnMeta.attrName)));
				ret.append(String.format("\t\t}%n"));
			}
		}
		ret.append(String.format("\t\twap.setWhere(sql.toString());%n"));
		ret.append(String.format("\t\twap.setParas(list.toArray());%n"));
		ret.append(String.format("\t\treturn wap;%n"));
		ret.append(String.format("\t}%n"));
	}

	// 首字母大写
	public static String captureName(String name) {
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		return name;
	}
	//首字母转小写
	public static String toLowerCaseFirstOne(String s){
	  if(Character.isLowerCase(s.charAt(0)))
	    return s;
	  else
	    return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}
	
	/**
	 * 鑻� model 鏂囦欢瀛樺湪锛屽垯涓嶇敓鎴愶紝浠ュ厤瑕嗙洊鐢ㄦ埛鎵嬪啓鐨勪唬鐮�
	 */
	protected void writeToFile(TableMeta tableMeta) throws IOException {
		
		String tableName = tableMeta.name;
		String fullTabelName = StrKit.firstCharToUpperCase(StrKit.toCamelCase(tableName));
		String pack = fullTabelName.replace(tableMeta.modelName, "");
		pack = Common.underscoreName(pack);
		pack = File.separator+pack.toLowerCase().replace("_",File.separator);
		
		File dir = new File(modelOutputDir + pack);
		if (!dir.exists())
			dir.mkdirs();
		
		String target = modelOutputDir + pack + File.separator + tableMeta.modelName + ".java";
		System.out.println("pack:" + pack);
		System.out.println("target:" + target);
		File file = new File(target);
		if (file.exists()) {
			return ;	// 鑻� Model 瀛樺湪锛屼笉瑕嗙洊
		}
		
		FileWriter fw = new FileWriter(file);
		try {
			fw.write(tableMeta.modelContent);
		}
		finally {
			fw.close();
		}
	}
}
