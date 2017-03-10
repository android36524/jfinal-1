package com.xiaoan.wlt.common.jfinal;

import java.util.List;

import com.jfinal.plugin.activerecord.generator.MappingKitGenerator;
import com.jfinal.plugin.activerecord.generator.TableMeta;

/**
 * 
 * @author liangjiahong
 * @date 2016年12月9日
 */
public class MyMappingKitGenerator extends MappingKitGenerator {

	public MyMappingKitGenerator(String mappingKitPackageName, String mappingKitOutputDir) {
		super(mappingKitPackageName, mappingKitOutputDir);
	}

	protected void genMappingMethod(List<TableMeta> tableMetas, StringBuilder ret) {
		ret.append(String.format(mappingMethodDefineTemplate));
		System.out.println(mappingKitPackageName);
		for (TableMeta tableMeta : tableMetas) {
			boolean isCompositPrimaryKey = tableMeta.primaryKey.contains(",");
			if (isCompositPrimaryKey)
				ret.append(String.format(compositeKeyTemplate, tableMeta.primaryKey));
			String con = tableMeta.modelContent;
			con = con.substring(con.indexOf("package")+8,con.indexOf(";"));
			System.out.println(con);
			String add = String.format(mappingMethodContentTemplate, tableMeta.name, tableMeta.primaryKey,con+"."+tableMeta.modelName);
			ret.append(add);
		}
		ret.append(String.format("\t}%n"));
	}
	
}
