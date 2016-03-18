package cn.farquer.survey.model;

import java.util.List;

/**
 * 分页类，封装了分页需要的相关属性以及方法
 * 
 * @author farquer
 * 
 *         2016年2月17日下午4:52:03
 */
public class Page<T> {

	private Integer pageNo;// 当前页页码
	private int totalRecordNo;// 总记录数
	private int totalPageNo;// 总页数
	private List<T> list;// 数据集合
	private int pageSize;// 每页显示数据数量

	public Page(String pageNoStr, int totalRecordNo, int pageSize) {

		this.pageSize = pageSize;

		// 1.设置总记录数
		this.totalRecordNo = totalRecordNo;

		// 2.计算总页数
		this.totalPageNo = this.totalRecordNo / this.pageSize
				+ ((this.totalRecordNo % this.pageSize == 0) ? 0 : 1);

		// 3.将字符串类型的pageNoStr转换为int类型
		// ①为了防止转换失败，给pageNo设置默认值为1
		this.pageNo = 1;

		// ②转换
		try {
			this.pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {/* 如果转换失败，则保持默认值 */
		}

		// 4.在1~总页数之间修正pageNo
		// 考虑到totalRecordNo有可能为0，进而totalPageNo也为0，为了避免pageNo变成0，所以先进行pageNo >
		// totalPageNo判断
		if (pageNo > totalPageNo) {
			pageNo = totalPageNo;
		}

		// 如果pageNo被改成0，那么在下面判断中恢复为1
		if (pageNo < 1) {
			pageNo = 1;
		}
	}

	public boolean isHasPrev() {
		return pageNo > 1;
	}

	public boolean isHasNext() {
		return pageNo < totalPageNo;
	}

	public int getPrev() {
		return pageNo - 1;
	}

	public int getNext() {
		return pageNo + 1;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public int getTotalRecordNo() {
		return totalRecordNo;
	}

	public int getTotalPageNo() {
		return totalPageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

}
