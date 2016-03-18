package cn.farquer.survey.utils;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import cn.farquer.survey.admin.entity.Authority;
import cn.farquer.survey.admin.entity.Resource;
import cn.farquer.survey.admin.entity.Role;
import cn.farquer.survey.guest.model.OptionStatisticsModel;
import cn.farquer.survey.guest.model.QuestionStatisticsModel;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 封装了全部常用静态方法的工具类
 * 
 * @author farquer
 * 
 *         2016年3月1日下午6:34:50
 */
public class DataProcessUtils {

	/**
	 * 将传入的"t_log_yyyy_xx"表名形式转换成"yyyy年xx月"
	 * @param result
	 * @return
	 */
	public static String str2LogTableName(String result) {
		
		String substring = result.substring(6);

		String[] split = substring.split("_");

		String date = split[0] + "年" + split[1] + "月";
		
		return date;
	}
	
	/**
	 * 将传入的"yyyy年xx月"形式转换成"t_log_yyyy_xx"表名
	 * @param result
	 * @return
	 */
	public static String logTableName2Str(String result) {
		
		String year = result.substring(0, result.indexOf("年"));
		String mouth = result.substring(result.indexOf("年") + 1, result.indexOf("月"));
		
		String logTableName = "t_log_" + year + "_" + mouth;
		
		return logTableName;
	}
	
	/**
	 * 根据偏移量生成日志表的表名，0：当前月 >0：以后的某个月 <0：以前的某个月
	 * 
	 * @param offset
	 *            
	 * @return
	 */
	public static String generateTableName(int offset) {

		int yearOffset = (offset - (offset % 12)) / 12;

		offset = offset % 12;

		Calendar calendar = Calendar.getInstance();

		int year = calendar.get(Calendar.YEAR) + yearOffset;

		// 月份表示是0~11，所以需要+1
		int month = calendar.get(Calendar.MONTH) + 1 + offset;

		if (month < 1) {
			month = month + 12;
			year--;
		}

		if (month > 12) {
			month = month - 12;
			year++;
		}
		// 拼出表名的格式
		return "t_log_" + year + "_" + month;
	}

	/**
	 * 根据角色集合计算权限位、权限码
	 * 
	 * @param roles
	 */
	public static String calculatCodeByRoles(Set<Role> roles, Integer maxPox) {

		Long[] codeArr = new Long[maxPox + 1];

		if (!ValidateUtils.collectionValidate(roles))
			return null;

		// 1.遍历Role集合
		for (Role role : roles) {
			// 2.获取每一个Role对象关联的Authority对象集合
			Set<Authority> authorities = role.getAuthorities();
			if (!ValidateUtils.collectionValidate(authorities))
				continue;

			// 3.遍历Authority集合，得到每一个Authority对象
			for (Authority authority : authorities) {

				// 4.通过Authority对象获取Resource集合
				Set<Resource> resources = authority.getResources();
				if (!ValidateUtils.collectionValidate(resources))
					continue;

				// 5.遍历Resource集合
				for (Resource resource : resources) {

					// 6.获取当前Resource对象的权限位、权限码
					int resPos = resource.getResPos();
					long resCode = resource.getResCode();

					// 7.根据当前resPos从Admin或User中获取用户中当前的权限码，执行或运算，再重新保存回去
					Long currentCode = codeArr[resPos];

					// 如果是第一次进行计算，那么currentCode的值就是null，则初始化为0
					if (currentCode == null) {
						currentCode = 0L;
					}

					codeArr[resPos] = currentCode | resCode;
				}

			}

		}

		return DataProcessUtils.convertArrToStr(codeArr);

	}

	/**
	 * 生成指定长度的随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String generateRandomString(int length) {
		StringBuilder randomString = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int randomNumber = getRangedRandomNumber();
			char suitableChar = (char) randomNumber;
			randomString.append(suitableChar);
		}

		return randomString.toString();
	}

	/**
	 * 生成固定范围的随机数 0~9 a~z A~Z
	 * 
	 * @return
	 */
	public static int getRangedRandomNumber() {

		while (true) {
			int randomNumber = (int) (Math.random() * 100);
			if (randomNumber < 48 || (randomNumber > 57 && randomNumber < 65)
					|| (randomNumber > 90 && randomNumber < 97)
					|| randomNumber > 122) {
				continue;
			} else {
				return randomNumber;
			}
		}

	}

	/**
	 * 将捕获到的ActionName翻译成中文
	 * 
	 * @param actionName
	 * @return
	 */
	public static String translateActionName(String actionName) {

		if (!ValidateUtils.stringValidate(actionName))
			return null;

		actionName = actionName.toLowerCase();

		// SurveyAction_save
		// SurveyAction_surveyDesign

		actionName = actionName.replaceAll("export", "导出");
		actionName = actionName.replaceAll("user", "用户");
		actionName = actionName.replaceAll("survey", "调查");
		actionName = actionName.replaceAll("bag", "包裹");
		actionName = actionName.replaceAll("question", "问题");
		actionName = actionName.replaceAll("answer", "答案");
		actionName = actionName.replaceAll("statistics", "统计");
		actionName = actionName.replaceAll("show", "显示");
		actionName = actionName.replaceAll("list", "列表");
		actionName = actionName.replaceAll("action", "");
		actionName = actionName.replaceAll("page", "页面");
		actionName = actionName.replaceAll("remove", "删除");
		actionName = actionName.replaceAll("delete", "删除");
		actionName = actionName.replaceAll("move", "移动");
		actionName = actionName.replaceAll("copy", "复制");
		actionName = actionName.replaceAll("this", "这个");
		actionName = actionName.replaceAll("entry", "入口");
		actionName = actionName.replaceAll("edit", "编辑");
		actionName = actionName.replaceAll("update", "更新");
		actionName = actionName.replaceAll("save", "保存");
		actionName = actionName.replaceAll("add", "保存");
		actionName = actionName.replaceAll("create", "创建");
		actionName = actionName.replaceAll("get", "获取");
		actionName = actionName.replaceAll("find", "查找");
		actionName = actionName.replaceAll("all", "全部");
		actionName = actionName.replaceAll("uncompleted", "未完成的");
		actionName = actionName.replaceAll("completed", "已完成的");
		actionName = actionName.replaceAll("complete", "完成");
		actionName = actionName.replaceAll("available", "可用的");
		actionName = actionName.replaceAll("top", "前");
		actionName = actionName.replaceAll("text", "文本");
		actionName = actionName.replaceAll("other", "其他");
		actionName = actionName.replaceAll("login", "登录");
		actionName = actionName.replaceAll("logout", "退出登录");
		actionName = actionName.replaceAll("register", "注册");
		actionName = actionName.replaceAll("regist", "注册");
		actionName = actionName.replaceAll("result", "结果");
		actionName = actionName.replaceAll("matrix", "矩阵");
		actionName = actionName.replaceAll("normal", "常规");
		actionName = actionName.replaceAll("cell", "单元格");
		actionName = actionName.replaceAll("select", "下拉列表");
		actionName = actionName.replaceAll("engaged", "参与");
		actionName = actionName.replaceAll("engage", "参与");
		actionName = actionName.replaceAll("mycenter", "个人中心");
		actionName = actionName.replaceAll("pay", "充值");
		actionName = actionName.replaceAll("vip", "续费");
		actionName = actionName.replaceAll("my", "我的");
		actionName = actionName.replaceAll("design", "设计");
		actionName = actionName.replaceAll("type", "类型");
		actionName = actionName.replaceAll("chosen", "选择");
		actionName = actionName.replaceAll("download", "下载");
		actionName = actionName.replaceAll("order", "顺序");
		actionName = actionName.replaceAll("adjust", "调整");
		actionName = actionName.replaceAll("workbook", "工作表");
		actionName = actionName.replaceAll("authorities", "权限");
		actionName = actionName.replaceAll("authority", "权限");
		actionName = actionName.replaceAll("auth", "权限");
		actionName = actionName.replaceAll("summary", "摘要");
		actionName = actionName.replaceAll("generate", "生成");
		actionName = actionName.replaceAll("chart", "图表");
		actionName = actionName.replaceAll("image", "图片");
		actionName = actionName.replaceAll("admins", "管理员");
		actionName = actionName.replaceAll("admin", "管理员");
		actionName = actionName.replaceAll("resources", "资源");
		actionName = actionName.replaceAll("resource", "资源");
		actionName = actionName.replaceAll("res", "资源");
		actionName = actionName.replaceAll("batch", "批量");
		actionName = actionName.replaceAll("roles", "角色");
		actionName = actionName.replaceAll("role", "角色");
		actionName = actionName.replaceAll("manager", "管理");
		actionName = actionName.replaceAll("mng", "管理");
		actionName = actionName.replaceAll("to", "前往");
		actionName = actionName.replaceAll("or", "或");
		actionName = actionName.replaceAll("do", "执行");
		actionName = actionName.replaceAll("10", "十");
		actionName = actionName.replaceAll("calculation", "计算");
		actionName = actionName.replaceAll("logs", "日志");
		actionName = actionName.replaceAll("log", "日志");
		actionName = actionName.replaceAll("main", "主");
		actionName = actionName.replaceAll("export", "导出");
		actionName = actionName.replaceAll("option", "下拉列表形式的");
		actionName = actionName.replaceAll("choosen", "选择");
		actionName = actionName.replaceAll("choose", "选择");
		actionName = actionName.replaceAll("code", "码");

		return actionName;
	}

	/**
	 * 根据选项模型List生成Chart
	 * 
	 * @param osmList
	 * @return
	 */
	public static JFreeChart generateChartByOsmList(
			List<OptionStatisticsModel> osmList) {

		DefaultPieDataset dataset = new DefaultPieDataset();
		for (OptionStatisticsModel optionStatisticsModel : osmList) {
			String label = optionStatisticsModel.getLabel();
			int count = optionStatisticsModel.getCount();
			dataset.setValue(label, count);
		}

		JFreeChart chart = ChartFactory.createPieChart3D(null, dataset);

		// 设置“图例”部分信息字体、风格、字号
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 20));

		// 获取代表当前图表绘图区的PiePlot对象
		PiePlot plot = (PiePlot) chart.getPlot();

		// 设置标签字体、风格、字号
		plot.setLabelFont(new Font("微软雅黑", Font.ITALIC, 15));

		// 设置前景色半透明
		plot.setForegroundAlpha(0.6f);

		// 设置标签信息格式
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0},{1}/{3},{2}"));

		return chart;
	}

	/**
	 * 根据问题统计模型生成图表对象
	 * 
	 * @param qsm
	 * @return
	 */
	public static JFreeChart generateChartByQsm(QuestionStatisticsModel qsm) {

		String chartTitle = qsm.getQuestionName() + "【共" + qsm.getTotalCount()
				+ "人次参与】";

		List<OptionStatisticsModel> osmList = qsm.getOsmList();
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (OptionStatisticsModel optionStatisticsModel : osmList) {
			String label = optionStatisticsModel.getLabel();
			int count = optionStatisticsModel.getCount();
			dataset.setValue(label, count);
		}

		JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, dataset);

		// 设置“标题”部分字体、风格、字号
		chart.getTitle().setFont(new Font("隶书", Font.BOLD, 50));

		// 设置“图例”部分信息字体、风格、字号
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 20));

		// 获取代表当前图表绘图区的PiePlot对象
		PiePlot plot = (PiePlot) chart.getPlot();

		// 设置标签字体、风格、字号
		plot.setLabelFont(new Font("微软雅黑", Font.ITALIC, 15));

		// 设置前景色半透明
		plot.setForegroundAlpha(0.6f);

		// 设置标签信息格式
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0},{1}/{3},{2}"));

		return chart;
	}

	/**
	 * 将字符串数组组装成字符串
	 * 
	 * @param sourceArr
	 * @return
	 */
	public static String convertArrToStr(Object[] sourceArr) {

		if (!ValidateUtils.arrayValidate(sourceArr))
			return null;

		if (sourceArr.length == 1)
			return sourceArr[0] + "";

		StringBuilder sb = new StringBuilder();

		for (Object source : sourceArr) {

			sb.append(source).append(",");

		}

		// [2,3,5]→2,3,5,
		return sb.substring(0, sb.lastIndexOf(","));
	}

	/**
	 * 将源字符串以逗号为分隔符拆分为数组
	 * 
	 * @param source
	 * @return
	 */
	public static String[] convertStrToArr(String source) {

		if (!ValidateUtils.stringValidate(source))
			return null;

		return source.split(",");
	}

	/**
	 * 将源字符串中的逗号替换为\r\n
	 * 
	 * @param source
	 * @return
	 */
	public static String processStrForShow(String source) {
		// 验证源字符串是否有效
		if (!ValidateUtils.stringValidate(source))
			return null;

		// String的对象是一个不可改变的字符序列，所以源字符串是不变的，我们需要获取替换方法的返回值
		return source.replaceAll(",", "\r\n");
	}

	/**
	 * 将源字符串中的\r\n替换为逗号
	 * 
	 * @param source
	 * @return
	 */
	public static String processStrForSave(String source) {

		// 验证源字符串是否有效
		if (!ValidateUtils.stringValidate(source))
			return null;

		while (source.contains(",,")) {
			source = source.replaceAll(",,", ",");
		}

		// String的对象是一个不可改变的字符序列，所以源字符串是不变的，我们需要获取替换方法的返回值
		return source.replaceAll("\r\n", ",");
	}

	public static final Map<Integer, String> QUESTION_TYPE_MAP = new HashMap<>();

	static {
		QUESTION_TYPE_MAP.put(0, "单选题");
		QUESTION_TYPE_MAP.put(1, "多选题");
		QUESTION_TYPE_MAP.put(2, "下拉列表选择题");
		QUESTION_TYPE_MAP.put(3, "简答题");
		QUESTION_TYPE_MAP.put(4, "矩阵单选题");
		QUESTION_TYPE_MAP.put(5, "矩阵多选题");
		QUESTION_TYPE_MAP.put(6, "矩阵下拉列表选择题");
	}

	/**
	 * 将图片压缩按本来的长宽比例压缩为100宽度的jpg图片
	 * 
	 * @param sourceFile
	 * @param realPath
	 *            /surveyLogos目录的真实路径，后面没有斜杠
	 * @return 将生成的文件路径返回 /surveyLogos/4198393905112.jpg
	 */
	public static String resizeImages(File sourceFile, String realPath) {

		OutputStream out = null;

		try {
			// 1.构造原始图片对应的Image对象
			BufferedImage sourceImage = ImageIO.read(sourceFile);

			// 2.获取原始图片的宽高值
			int sourceWidth = sourceImage.getWidth();
			int sourceHeight = sourceImage.getHeight();

			// 3.计算目标图片的宽高值
			int targetWidth = 100;
			int targetHeight = sourceHeight / (sourceWidth / 100);

			// 4.创建压缩后的目标图片对应的Image对象
			BufferedImage targetImage = new BufferedImage(targetWidth,
					targetHeight, BufferedImage.TYPE_INT_RGB);

			// 5.绘制目标图片
			targetImage.getGraphics().drawImage(sourceImage, 0, 0, targetWidth,
					targetHeight, null);

			// 6.构造目标图片文件名
			String targetFileName = System.nanoTime() + ".jpg";

			// 7.创建目标图片对应的输出流
			out = new FileOutputStream(realPath + "/" + targetFileName);

			// 8.获取JPEG图片编码器
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

			// 9.JPEG编码
			encoder.encode(targetImage);

			// 10.返回文件名
			return "/surveyLogos/" + targetFileName;

		} catch (Exception e) {

			return null;
		} finally {
			// 10.关闭流
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * MD5加密
	 * 
	 * @param source
	 *            明文
	 * @return 密文
	 * 
	 *         使用注意： 注册：明文→密文→保存到数据库 登录：明文→密文→将两个密文进行比较
	 */
	public static String md5(String source) {

		// 1.提供一个保存每个字节字符的字符串
		StringBuilder sb = new StringBuilder();

		try {
			// 2.将原始字符串转换为字节数组
			byte[] bytes = source.getBytes();

			// 3.创建MD5加密对象
			MessageDigest digest = MessageDigest.getInstance("MD5");

			// 4.给加密对象传入字节数组
			byte[] targetBytes = digest.digest(bytes);

			// 5.提供一个转换16进制数的字符数组
			char[] code = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'A', 'B', 'C', 'D', 'E', 'F' };

			// 6.将字节数组转化为字符串
			for (int i = 0; i < targetBytes.length; i++) {
				byte b = targetBytes[i];

				// ①低四位转换
				int lowNumber = b & 0x0F;// 0x0F→00001111

				// ②高四位转换
				int highNumber = (b >> 4) & 0x0F;

				sb.append(code[lowNumber]).append(code[highNumber]);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	/**
	 * 实现深度复制
	 * 
	 * @param sourceBag
	 * @return
	 */
	public static Serializable deeplyCopy(Serializable sourceBag) {

		// 1.声明需要使用的变量
		// ①接收反序列化结果的变量
		Serializable result = null;

		// ②对象输入流
		ObjectInputStream objIS = null;

		// ③对象输出流
		ObjectOutputStream objOS = null;

		// ④字节数组输入流
		ByteArrayInputStream byteArrIS = null;

		// ⑤字节数组输出流
		ByteArrayOutputStream byteArrOS = null;

		try {
			// 2.将源对象进行序列化操作
			// ①创建字节数组输出流
			byteArrOS = new ByteArrayOutputStream();

			// ②创建对象输出流
			objOS = new ObjectOutputStream(byteArrOS);

			// ③将源对象通过objOS写入到byteArrOS
			objOS.writeObject(sourceBag);

			// ④通过byteArrOS获取源对象序列化后得到的字节数组
			byte[] byteArray = byteArrOS.toByteArray();

			// 3.将源对象进行反序列化操作
			// ①创建字节数组输入流
			byteArrIS = new ByteArrayInputStream(byteArray);

			// ②根据字节数组输入流创建对象输入流
			objIS = new ObjectInputStream(byteArrIS);

			// ③从对象输入流中读取对象
			result = (Serializable) objIS.readObject();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

			// 关闭资源
			try {
				if (objIS != null)
					objIS.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if (objOS != null) {
					objOS.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		// 4.返回反序列化操作的结果
		return result;
	}

	/**
	 * 获取参与调查时提交按钮的name属性值
	 * 
	 * @param parametersMap
	 * @return
	 */
	public static String getSubmitName(Map<String, String[]> parametersMap) {

		// submit_next:进入下一个包裹
		Set<String> keySet = parametersMap.keySet();
		for (String key : keySet) {
			if (key.startsWith("submit_"))
				return key;
		}

		return null;
	}

	public static String removeLastComma(String oldContent) {

		if (!ValidateUtils.stringValidate(oldContent))
			return oldContent;

		if (!oldContent.contains(","))
			return oldContent;

		return oldContent.substring(0, oldContent.lastIndexOf(","));
	}

	/**
	 * 检查权限码是否匹配目标资源
	 * 
	 * @param resource
	 * @param resCode
	 * @return
	 */
	public static boolean checkAuthority(Resource resource, String resCode) {

		String[] codeStrArr = DataProcessUtils.convertStrToArr(resCode);
		int resPos = resource.getResPos();
		String codeStr = codeStrArr[resPos];
		if (codeStr == null || "null".equals(codeStr)) {
			return false;
		} else {

			Long code = Long.parseLong(codeStr);
			long resCodeValue = resource.getResCode();
			long result = code & resCodeValue;
			return result != 0;

		}

	}

	public static String generateSubSelect(List<String> tableNameList) {

		StringBuilder subSelect = new StringBuilder();

		for (String tableName : tableNameList) {
			subSelect.append("SELECT * FROM ").append(tableName)
					.append(" UNION ");
		}

		return subSelect.substring(0, subSelect.lastIndexOf(" UNION "));
	}

}
