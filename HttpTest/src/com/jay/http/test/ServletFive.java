package com.jay.http.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletFive extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setHeader("content-disposition", "attachment;filename=Android.pdf");
		InputStream in = this.getServletContext().getResourceAsStream("/file/android±àÂë¹æ·¶.pdf");
		byte buffer[] = new byte[1024];
		int len = 0;
		OutputStream out = resp.getOutputStream();
		while((len = in.read(buffer)) > 0)
		{
			out.write(buffer,0,len);
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
