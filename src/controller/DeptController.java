package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hr.data.TDeptInfo;
import com.hr.database.IDaoService;
import com.hr.database.impl.DeptInfoDao;

/**
 * Servlet implementation class DeptList
 */
@WebServlet("/deptlist")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IDaoService<TDeptInfo> deptService = new DeptInfoDao();
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// TODO Auto-generated method stub
		String status = request.getParameter("status");
		if("deptlist".equals(status)){
			deptList(request,response);
		}else if("deptupdateview".equals(status)){
			deptUpdateView(request, response);
		}else if("deptupdate".equals(status)){
			deptUpdate(request,response);
		}else if("add".equals(status)){
			add(request,response);
		}else if("addview".equals(status)){
			addview(request, response);
		}else if("query".equals(status)){
			query(request,response);
		}
	}
	
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		try {
			String deptName = request.getParameter("deptName");
			TDeptInfo dept = new TDeptInfo();
			dept.setDeptName("like '%" + deptName + "%'");
			List<TDeptInfo> list = deptService.query(dept);
			// 转发数据
			request.setAttribute("list", list);
			request.getRequestDispatcher("/Maintain/maintainMain.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		try {
			request.getRequestDispatcher("/Maintain/mainAdd.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void addview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		try {
			String deptId = request.getParameter("dept_id");
			String deptName = request.getParameter("dept_name");
			String deptMemo = request.getParameter("dept_memo");
			TDeptInfo deptInfo  = new TDeptInfo();
			deptInfo.setDeptId(Integer.valueOf(deptId));
			deptInfo.setDeptName(deptName);
			deptInfo.setDeptMemo(deptMemo);
			deptService.add(deptInfo);
			deptList(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		deptList(request, response);
	}
	
	protected void deptList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		try {
			List<TDeptInfo> list = deptService.query();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/Maintain/maintainMain.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void deptUpdateView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		try {
			String deptId = request.getParameter("id");
			System.out.println(deptId);
			TDeptInfo deptInfo =new  TDeptInfo();
			deptInfo.setDeptId(Integer.parseInt(deptId));
			List<TDeptInfo> list=deptService.query(deptInfo);
			request.setAttribute("list", list.get(0));
			request.getRequestDispatcher("/Maintain/mainupdate.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void deptUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		try {
			String deptId = request.getParameter("dept_id");
			String deptName = request.getParameter("dept_name");
			String deptMemo = request.getParameter("dept_memo");
			TDeptInfo deptInfo  = new TDeptInfo();
			deptInfo.setDeptId(Integer.valueOf(deptId));
			deptInfo.setDeptName(deptName);
			deptInfo.setDeptMemo(deptMemo);
			deptService.update(deptInfo);
			deptList(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
