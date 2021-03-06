package com.yqkj.zysoft.common.util;

import javax.servlet.http.HttpServletRequest;
/**
 * @ClassName IPTool
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/3/25 16:28
 * @Version 1.0
 **/
public class IPTool {

	private static final String UNKNOWN = "unknown";

	protected IPTool() {
	}

	/**
	 * 获取IP地址
	 * 使用 Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非 unknown的有效IP字符串，则为真实IP地址
	 * @return String
	 * @param request  请求参数
	 */
	public static String getIpAddr(final HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}

}
