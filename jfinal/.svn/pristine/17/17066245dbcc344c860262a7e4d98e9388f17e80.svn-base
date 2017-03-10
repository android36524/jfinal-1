package com.xiaoan.wlt.common.jfinal;

import javax.sql.DataSource;

public class MyGenerator extends com.jfinal.plugin.activerecord.generator.Generator {

	public MyGenerator(DataSource dataSource, String baseModelPackageName, String baseModelOutputDir, String modelPackageName, String modelOutputDir) {
		super(dataSource, new MyBaseModelGenerator(baseModelPackageName, baseModelOutputDir), new MyModelGenerator(modelPackageName, baseModelPackageName, modelOutputDir));
		super.mappingKitGenerator = new MyMappingKitGenerator(modelPackageName, modelOutputDir);
	}
}
