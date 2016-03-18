package cn.farquer.survey.guest.model;

/**
 * 选项统计模型类。封装了选项的名称和选择人数
 * 
 * @author farquer
 * 
 *         2016年2月23日下午7:56:24
 */
public class OptionStatisticsModel {

	private String label; // 问题标题
	private int count; // 参与人数

	public OptionStatisticsModel() {

	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "OptionStatisticsModel [label=" + label + ", count=" + count
				+ "]";
	}

}
