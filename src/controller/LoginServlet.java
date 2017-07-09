package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hr.data.TDeptInfo;
import com.hr.data.TStaffInfo;
import com.hr.database.IDaoService;
import com.hr.database.impl.DeptInfoDao;
import com.hr.database.impl.StaffInfoDao;

/**
 * 登录的控制器
 * 
 * @author Wangyu 时间 2017年6月20日 下午8:11:31
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IDaoService<TStaffInfo> deptService = new StaffInfoDao();

	/**
	 * 入口方法 处理提交模式的数据 比如：get post
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			//获取客户端的请求的方法 参数
			String status=request.getParameter("status");
			if("login".equals(status)){
				login(request, response);
			}else if("loginOut".equals(status)){
				loginOut(request,response);
			}else{
				loginOut(request,response);
			}
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		TStaffInfo tDemoUser = new TStaffInfo();
		tDemoUser.setStaffId(Integer.parseInt(userName));
		tDemoUser.setStaffPassword(password);
		java.util.List<TStaffInfo> list=null;
		try {
			list = deptService.query(tDemoUser);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (list.size() > 0) {
			
			request.getSession().setAttribute("user",list.get(0));
			request.setAttribute("user",list.get(0));
			request.setAttribute("user",list.get(0).getStaffName());
			//转发
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else{
			request.setAttribute("error","用户名密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	protected void loginOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//删除Session中的缓存
		request.getSession().removeAttribute("user");
		//重定向
		response.sendRedirect(request.getContextPath()+"/login.jsp");

	}
}
