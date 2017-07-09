package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
 * Servlet implementation class ChangServlet
 */
@WebServlet("/chang")
public class ChangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 创建访问用户表数据的Dao接口对象
	private IDaoService<TStaffInfo> iDaoStaffService = new StaffInfoDao();
	// 创建访问部门表数据的Dao接口对象
	private IDaoService<TDeptInfo> iDaoDeptService = new DeptInfoDao();


	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 设置请求中数据的编码格式
		request.setCharacterEncoding("utf-8");
		// 获得请求方法的状态
		String status = request.getParameter("status");
		// 判断状态访问指定的状态
		if ("changView".equals(status)) {
			changView(request, response);
		} else if ("userUpdateView".equals(status)) {
			userUpdateView(request, response);
		} else if ("userUpdate".equals(status)) {
			userUpdate(request, response);
		} else if ("query".equals(status)) {
			query(request, response);
		}
	}
	
	/**
	 * 人事变动查询所有
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void changView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			// 查询用户表中所有的数据
			//TStaffInfo staff = new TStaffInfo();
			//TDeptInfo dept = new TDeptInfo();
			List<TStaffInfo> list = iDaoStaffService.query();
			System.out.println(list);
			// 转发数据
			request.setAttribute("list", list);
			// 转发到用户列表界面
			request.getRequestDispatcher("/admin/user/chang.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 人事变动之前，显示修改界面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void userUpdateView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String staffId = request.getParameter("id");
			TStaffInfo staffInfo = new TStaffInfo();
			staffInfo.setStaffId(Integer.parseInt(staffId));
			List<TStaffInfo> list = iDaoStaffService.query(staffInfo);
			request.setAttribute("staffList", list.get(0));
			
			TDeptInfo deptInfo  = new TDeptInfo();
			List<TDeptInfo> deptList=iDaoDeptService.query();
			request.setAttribute("deptList", deptList);
			request.getRequestDispatcher("/admin/user/userupdate.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改人事变动
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void userUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String detpId=request.getParameter("dept2");
		String staffName = request.getParameter("userName");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		//String deptId = request.getParameter("dept");
		String inTime = request.getParameter("inTime");
		String staffId = request.getParameter("userId");
		// 创建用户对象
		TStaffInfo staff = new TStaffInfo();
		// 创建部门对象
		TDeptInfo dept = new TDeptInfo();
		// 设置部门ID
		dept.setDeptId(Integer.parseInt(detpId));
		// 封装用户的数据
		staff.setTDeptInfo(dept);
		staff.setStaffId(Integer.parseInt(staffId));
		staff.setStaffName(staffName);
		//staff.setStaffPassword(password);
		staff.setStaffGender(gender);
		staff.setStaffId(Integer.parseInt(staffId.trim()));
		
		// 创建时间格式化对象
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			staff.setInTime(new Date(format.parse(inTime).getTime()));
			iDaoStaffService.update(staff);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		changView(request, response);
	}
	
	/**
	 * 模糊查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void query(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

}
