package instrument_transformer;

public class Constant {
		
	/**
	 * 每次查询的最大记录条数
	 */
	public static final String LIMIT="100";
	
	/**
	 * 动态改变ab、bc、ca三个区域的表格的行高时，需要减去的高度。相当于窗口上下边框+tab边框+表头的高度和。
	 * 只是个估算值，若发现在调整窗口大小时，测试数据表格的行和左侧的“修约值”不在同一水平线上，可以调整此值。
	 */
	public static final Integer EXTRA_HEIGHT=50;

}
