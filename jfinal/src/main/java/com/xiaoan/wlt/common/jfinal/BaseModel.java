package com.xiaoan.wlt.common.jfinal;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;
import com.xiaoan.wlt.common.WhereAndParas;

public abstract class BaseModel<M extends Model> extends Model<M> {
	private static final long serialVersionUID = -6471371052665270761L;
	private static Log log = Log.getLog(BaseModel.class);

	private int pageNumber = 1;
	private int pageSize = 15;

	private Date startTime;
	private Date endTime;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 重写save方法
	 */
	public boolean save() {
		if (getTable().hasColumnLabel("version")) { // 是否需要乐观锁控制
			this.set("version", Long.valueOf(0)); // 初始化乐观锁版本号
		}
		if (getTable().hasColumnLabel("create_time")
				&& this.get("create_time") == null) { // 创建时间
			this.set("create_time", new Date()); // 初始化创建时间
		}
		if (getTable().hasColumnLabel("update_time")
				&& this.get("update_time") == null) { // 更新时间
			this.set("update_time", new Date());
		}
		if (getTable().hasColumnLabel("createTime")
				&& this.get("createTime") == null) { // 创建时间
			this.set("createTime", new Date()); // 初始化创建时间
		}
		if (getTable().hasColumnLabel("updateTime")
				&& this.get("updateTime") == null) { // 更新时间
			this.set("updateTime", new Date());
		}
		return super.save();
	}

	/**
	 * 重写update方法
	 */
	@SuppressWarnings("unchecked")
	public boolean update() {
		Table table = getTable();
		String name = table.getName();
		String[] pk = table.getPrimaryKey();

		// 1.数据是否还存在
		String sql = new StringBuffer("select version from ").append(name)
				.append(" where ").append(pk[0]).append(" = ? ").toString();
		Model<M> modelOld = findFirst(sql, get(pk[0]));
		if (null == modelOld) { // 数据已经被删除
			throw new RuntimeException("数据库中此数据不存在，可能数据已经被删除，请刷新数据后在操作");
		}

		if (getTable().hasColumnLabel("version")) { // 是否需要乐观锁控制
			Number versionDB = modelOld.getNumber("version"); // 数据库中的版本号
			Number versionForm = getNumber("version"); // 表单中的版本号
			if (versionForm != null && versionForm.longValue() != 0
					&& versionForm.longValue() < versionDB.longValue()) {
				throw new RuntimeException(
						"表单数据版本号和数据库数据版本号不一致，可能数据已经被其他人修改，请重新编辑");
			}
			this.set("version", versionDB.longValue() + 1);// 修改为：在此处增加version，而不是放到表单中
		}

		if (getTable().hasColumnLabel("updateTime")) { // 更新时间
			this.set("updateTime", new Date());
			this.set("updateTime", new Date());
		}
		if (getTable().hasColumnLabel("update_time")) { // 更新时间
			this.set("update_time", new Date());
			this.set("update_time", new Date());
		}

		return super.update();
	}

	public boolean saveOrUpdate() {
		Table table = getTable();
		String[] pk = table.getPrimaryKey();
		Object id = get(pk[0]);
		if (id != null && NumberUtils.isNumber(id.toString())) {
			return update();
		} else {
			return save();
		}
	}

	/**
	 * 获取表映射对象
	 * 
	 * @return
	 */
	public Table getTable() {
		return TableMapping.me().getTable(getClass());
	}

	public abstract WhereAndParas getWhereAndParas(M model);

	@SuppressWarnings({ "unchecked" })
	public <T extends BaseModel<T>> Page<T> findPageList(T model) {
		WhereAndParas wp = model.getWhereAndParas(model);
		return (Page<T>) paginate(model.getPageNumber(), model.getPageSize(),
				"select * ", "from " + model.getTable().getName()
						+ " where 1=1 " + wp.getWhere(), wp.getParas());
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseModel<T>> Page<T> paginateSpec(T model,
			String extWhere, Object... extParas) {
		WhereAndParas wp = model.getWhereAndParas(model);
		return (Page<T>) paginate(model.getPageNumber(), model.getPageSize(),
				"select * ", "from " + model.getTable().getName()
						+ " where 1=1 " + wp.getWhere() + extWhere,
				ArrayUtils.addAll(wp.getParas(), extParas));
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseModel<T>> List<T> findAll() {
		return (List<T>) find("select * from " + getTable().getName());
	}
}
