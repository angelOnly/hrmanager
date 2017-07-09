package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hr.data.TDeptInfo;
import com.hr.data.TRecordInfo;
import com.hr.data.TStaffInfo;
import com.hr.database.IDaoService;
import com.hr.database.impl.DeptInfoDao;
import com.hr.database.impl.RecordInfoDao;
import com.hr.database.impl.StaffInfoDao;

/**
 * 用户管理的控制器
 * 
 * @author TangShengYu
 * @crateTime 2017年6月26日 下午6:49:58
 * @version 1.0.0
 */
@WebServlet("/staff")
public class StaffServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 创建访问用户表数据的Dao接口对象
	private IDaoService<TStaffInfo> iDaoService = new StaffInfoDao();
	// 创建访问部门表数据的Dao接口对象
	private IDaoService<TDeptInfo> iDaoDeptService = new DeptInfoDao();
	private IDaoService<TRecordInfo> iDaoRecordService = new RecordInfoDao();

	/**
	 * 服务
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求中数据的编码格式
		request.setCharacterEncoding("utf-8");
		// 获得请求方法的状态
		String status = request.getParameter("status");
		// 判断状态访问指定的状态
		if ("list".equals(status)) {
			list(request, response);
		} else if ("query".equals(status)) {
			query(request, response);
		} else if ("addView".equals(status)) {
			addView(request, response);
		} else if ("add".equals(status)) {
			add(request, response);
		} else if ("delete".equals(status)) {
			delete(request, response);
		} else if ("staffInformation".equals(status)) {
			staffInformation(request, response);
		} else if ("staffRecord".equals(status)) {
			staffRecord(request, response);
		} else if ("userchange".equals(status)) {
			userchange(request, response);
		} else if ("userchangeView".equals(status)) {
			userchangeView(request, response);
		} 
	}

	/**
	 * 查询所有员工信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void list(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// 查询用户表中所有的数据
			List<TStaffInfo> list = iDaoService.query();
			System.out.println(list);
			// 转发数据
			request.setAttribute("list", list);
			// 转发到用户列表界面
			request.getRequestDispatcher("/admin/user/staffInformation.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 条件查询员工信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//获得查询条件“用户名”
			String staffName = request.getParameter("suserName");
			//创建用户对象
			TStaffInfo staff = new TStaffInfo();
			//设置作为查询条件的属性
			staff.setStaffName(" like '%" + staffName + "%' ");
			//访问用户表，条件查询用户
			List<TStaffInfo> list = iDaoService.query(staff);
			//转发数据
			request.setAttribute("list", list);
			//返回用户列表界面
			request.getRequestDispatcher("/admin/user/staffinfomation.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			//转发到错误界面
			request.getRequestDispatcher("/admin/user/error.jsp").forward(request, response);
		}
	}

	/**
	 * 添加员工之前，展示添加页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<TDeptInfo> deptList = iDaoDeptService.query();
			request.setAttribute("deptList", deptList);
			request.getRequestDispatcher("/admin/user/useradd.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加员工信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获得请求中的用户数据,即客户端发送到服务器的数据
			String staffName = request.getParameter("userName");
			String gender = request.getParameter("gender");
			String deptId = request.getParameter("deptMemo");
			String staffEdu = request.getParameter("staffEdu");
			String inTime = request.getParameter("inTime");
			// 创建员工对象
			TStaffInfo staff = new TStaffInfo();
			TDeptInfo dept = new TDeptInfo();
			dept.setDeptId(Integer.parseInt(deptId));
			staff.setStaffName(staffName);
			staff.setStaffPassword("123456");
			staff.setStaffGender(gender);
			staff.setStaffEdu(staffEdu);
			staff.setTDeptInfo(dept);
			// 创建时间格式化对象
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			staff.setInTime(new Date(format.parse(inTime).getTime()));
			// 通过Dao接口，访问员工表，添加员工数据
			iDaoService.add(staff);
			List<TStaffInfo> staffList=iDaoService.query();
			staff.setStaffId(staffList.get(staffList.size()-1).getStaffId());
			List<TDeptInfo> deptList = iDaoDeptService.query(dept);
			String recordName = staffName + "的档案";
			String oldMemo = deptList.get(0).getDeptMemo();
			String newMemo = deptList.get(0).getDeptMemo();
			TRecordInfo record = new TRecordInfo();
			record.setRecordName(recordName);
			record.setTStsffInfo(staff);
			record.setRecordPaper("由"+oldMemo+"到"+newMemo);
			iDaoRecordService.add(record);
			// 返回列表界面,更新数据
			list(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除用户信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println(2);
			//获得请求中的数据：ID
			String[] ids = request.getParameterValues("id"); 
			System.out.println(1);
			System.out.println(ids[0]);
			//通过Dao接口，访问用户表，删除数据
			iDaoService.delete(ids);
			//返回用户列表界面，更新用户数据
			list(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 员工基本信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void staffInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 查询用户表中所有的数据
			List<TStaffInfo> list = iDaoService.query();
			//System.out.println(list);
			//System.out.println("StaffServlet.staffInformation()");
			// 转发数据
			request.setAttribute("list", list);
			// 转发到用户列表界面
			request.getRequestDispatcher("/admin/user/staffInformation.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 员工档案
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void staffRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 查询用户表中所有的数据
			List<TStaffInfo> list = iDaoService.query();
			System.out.println(list);
			// 转发数据
			request.setAttribute("list", list);
			// 转发到用户列表界面
			request.getRequestDispatcher("/admin/user/staffrecord.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改员工信息之前，暂时修改界面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void userchangeView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//获得要修改的员工的ID
			String staffId = request.getParameter("id");
			String deptId = request.getParameter("deptId");
			System.out.println(staffId);
			//创建作为条件的用户对象
			TStaffInfo staff = new TStaffInfo();
			TDeptInfo dept = new TDeptInfo();
			dept.setDeptId(Integer.parseInt(deptId));
			staff.setTDeptInfo(dept);
			staff.setStaffId(Integer.parseInt(staffId));
			//访问用户表，条件查询用户数据
			List<TStaffInfo> staffList = iDaoService.query(staff);
			request.setAttribute("staff", staffList.get(0));
			//转发到用户界面
			request.getRequestDispatcher("/admin/user/userchange.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 员工信息修改
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void userchange(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 获得请求中的用户数据,即客户端发送到服务器的数据
			String staffName = request.getParameter("staffName");
			String gender = request.getParameter("gender");
			String staffEdu = request.getParameter("staffEdu");
			String inTime = request.getParameter("inTime");
			String staffId = request.getParameter("staffId");
			String deptId = request.getParameter("deptId");
			TStaffInfo staff = new TStaffInfo();
			// 创建部门对象
			TDeptInfo dept = new TDeptInfo();
			// 设置部门ID
			dept.setDeptId(Integer.parseInt(deptId));
			// 封装用户的数据
			staff.setTDeptInfo(dept);
			staff.setStaffId(Integer.parseInt(staffId));
			staff.setStaffName(staffName);
			//staff.setStaffPassword(password);
			staff.setStaffGender(gender);
			staff.setStaffEdu(staffEdu);
			staff.setStaffId(Integer.parseInt(staffId.trim()));
			// 创建时间格式化对象
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			staff.setInTime(new Date(format.parse(inTime).getTime()));
			// 通过Dao接口，访问用户表，保持用户数据
			iDaoService.update(staff);
			
			//返回列表界面,更新数据
			list(request,response);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
