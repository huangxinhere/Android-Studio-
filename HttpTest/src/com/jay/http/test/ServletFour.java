package com.jay.http.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletFour extends HttpServlet {
	public int second = 0;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1.�����ÿ��5�붨ʱˢ��ҳ��
//		resp.setHeader("refresh", "2");
//		resp.getWriter().write(++second + "");
//		System.out.println("doGet����������~");
		
		
		//2.����ҳ��5s��Ȼҳ�����ٶ�~
		resp.setHeader("refresh", "5;url='http://www.baidu.com'");
		resp.getWriter().write("HE HE DA~");
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException 
	{
		doGet(req, resp);
	};
}
