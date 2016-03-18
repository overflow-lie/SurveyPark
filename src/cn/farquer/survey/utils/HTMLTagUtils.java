package cn.farquer.survey.utils;

/**
 * 拼出JSP页面的HTML标签的封装了一些此类静态方法的工具类
 * 
 * @author farquer
 *
 * 		2016年2月23日上午10:22:27
 */
public class HTMLTagUtils {
	
	/**
	 * 根据给定数据生成单选按钮HTML代码
	 * <input id="radio50" type="radio" name="question5" value="0"/><label for="radio50">选项01</label><br/>
	 * @return
	 */
	public static String generateRadio(String idStr, String name, String value, String label, String checkedStr, boolean isBr) {
		return "<input id='"+idStr+"' type='radio' name='"+name+"' value='"+value+"' "+checkedStr+"/><label for='"+idStr+"'>"+label+"</label>"+(isBr?"<br/>":"");
	}
	
	public static String generateCheckbox(String idStr, String name, String value, String label, String checkedStr, boolean isBr) {
		return "<input id='"+idStr+"' type='checkbox' name='"+name+"' value='"+value+"' "+checkedStr+"/><label for='"+idStr+"'>"+label+"</label>"+(isBr?"<br/>":"");
	}

	/**
	 * <option value="xxx" selected="selected">xxx</option>
	 */
	public static String generateOption(String value, String selected, String label) {
		return "<option value='"+value+"' "+selected+">"+label+"</option>";
	}
	
	/**
	 * <select name="xxx>
			<option value="xxx" selected="selected">xxx</option>
			<option value="xxx" selected="selected">xxx</option>
			<option value="xxx" selected="selected">xxx</option>
			<option value="xxx" selected="selected">xxx</option>
			<option value="xxx" selected="selected">xxx</option>
		</select>
	 * @return
	 */
	public static String generateSelect(String name, String allOpts) {
		return "<select name='"+name+"'>"+allOpts+"</select>";
	}
	
	/**
	 * <input type="text" name="xxx" value="xxx"/>其他
	 * @return
	 */
	public static String generateText(String name, String value, boolean isOther) {
		return "<input type='text' name='"+name+"' value='"+value+"'/>"+(isOther?"其它":"");
	}
	
	/**
	 * <td>xxx</td>
	 * @return
	 */
	public static String generateTd(String content) {
		return "<td>"+content+"</td>";
	}
	
	/**
	 * <tr>
	 * 	<td></td>
	 * 	<td></td>
	 * 	<td></td>
	 * </tr>
	 * @return
	 */
	public static String generateTr(String tds) {
		return "<tr>"+tds+"</tr>";
	}
	
	public static String generateTable(String trs) {
		return "<table class='dashedTable'>"+trs+"</table>";
	}
	
}
