package controller;

import java.io.IOException;
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
 * 员工合同控制器
 * @author TangShengYu
 * @crateTime 2017年6月27日 上午6:29:52
 * @version 1.0.0
 */
@WebServlet("/record")
public class RecordServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private IDaoService<TStaffInfo> iDaoStaffService = new StaffInfoDao();
	
	private IDaoService<TRecordInfo> iDaoRecordService = new RecordInfoDao();
	
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
		if ("staffRecord".equals(status)) {
			staffRecord(request, response);
		} else if ("query".equals(status)) {
			query(request, response);
		} else if ("recordAddView".equals(status)) {
			recordAddView(request, response);
		} else if ("recordAdd".equals(status)) {
			recordAdd(request, response);
		} else if ("recordUpdateView".equals(status)) {
			recordUpdateView(request, response);
		} else if ("recordUpdate".equals(status)) {
			recordUpdate(request, response);
		} else if ("recordDelete".equals(status)) {
			recordDelete(request, response);
		} 
	}

	/**
	 * 查询所有档案，并跳转档案信息页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void staffRecord(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// 查询用户表中所有的数据
			List<TRecordInfo> recordList = iDaoRecordService.query();
			String paper;
			String oldmemo;
			String newmemo;
			for(TRecordInfo record:recordList){
				paper = record.getRecordPaper();
				oldmemo = paper.substring(paper.indexOf("由")+1, paper.indexOf("到"));
				newmemo = paper.substring(paper.indexOf("到")+1);
				record.setBeiyong41(oldmemo);
				record.setBeiyong42(newmemo);
			}	
			request.setAttribute("list", recordList);
			request.getRequestDispatcher("/userrecord/staffrecord.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 条件查询档案
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void query(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			//获得查询条件“用户名”
			String recordName = request.getParameter("recordName");
			TRecordInfo record = new TRecordInfo();
			//设置作为查询条件的属性
			record.setRecordName(" like '%" + recordName + "%' ");
			//访问用户表，条件查询用户
			List<TRecordInfo> recordList = iDaoRecordService.query(record);
			//创建用户对象
			String paper;
			String oldmemo;
			String newmemo;
			for(TRecordInfo recordfor:recordList){
				paper = recordfor.getRecordPaper();
				oldmemo = paper.substring(paper.indexOf("由")+1, paper.indexOf("到"));
				newmemo = paper.substring(paper.indexOf("到")+1);
				recordfor.setBeiyong41(oldmemo);
				recordfor.setBeiyong42(newmemo);
			}	
			//转发数据
			request.setAttribute("list", recordList);
			//返回用户列表界面
			request.getRequestDispatcher("/userrecord/staffrecord.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			//转发到错误界面
			request.getRequestDispatcher("/admin/user/error.jsp").forward(request, response);
		}
	}
	
	/**
	 * 添加档案信息前，展示档案添加页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void recordAddView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// 查询用户表中所有的数据
			TStaffInfo staff = new TStaffInfo();
			List<TStaffInfo> staffList = iDaoStaffService.queryadd(staff);
			List<TDeptInfo> deptList = iDaoDeptService.query();
			request.setAttribute("dept", deptList);
			request.setAttribute("list", staffList);
			for(TStaffInfo i : staffList){
				System.out.println(i.getStaffName());
			}
			request.getRequestDispatcher("/userrecord/recordadd.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加档案
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void recordAdd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获得请求中的用户数据,即客户端发送到服务器的数据
			String recordName = request.getParameter("recordName");
			String oldMemo = request.getParameter("oldMemo");
			String newMemo = request.getParameter("newMemo");
			String staffId = request.getParameter("staffId");
			TStaffInfo staff = new TStaffInfo();
			staff.setStaffId(Integer.parseInt(staffId));
			TRecordInfo record = new TRecordInfo();
			record.setRecordName(recordName);
			record.setTStsffInfo(staff);
			record.setRecordPaper("由"+oldMemo+"到"+newMemo);
			iDaoRecordService.add(record);
			// 返回列表界面,更新数据
			staffRecord(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改档案之前，展示档案修改页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void recordUpdateView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			//获得要修改的员工的ID
			String recordId = request.getParameter("id");
			TDeptInfo dept = new TDeptInfo();
			List<TDeptInfo> deptList = iDaoDeptService.query();
			dept.setDeptId(Integer.parseInt(recordId));
			request.setAttribute("dept", deptList);

			TRecordInfo record = new TRecordInfo();
			record.setRecordId(Integer.parseInt(recordId));
			//访问用户表，条件查询用户数据
			List<TRecordInfo> recordList = iDaoRecordService.query(record);
			request.setAttribute("record", recordList.get(0));
			String paper = recordList.get(0).getRecordPaper();
			//String dataInTime = paper.substring(0, 11);
			String oldmemo = paper.substring(paper.indexOf("由")+1, paper.indexOf("到"));
			String newmemo = paper.substring(paper.indexOf("到")+1);
			System.out.println(oldmemo+"\t"+newmemo);
			//DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			request.setAttribute("oldmemo", oldmemo);
			request.setAttribute("newmemo", newmemo);
			//转发到用户界面
			request.getRequestDispatcher("/userrecord/recordupdate.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改档案信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void recordUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获得请求中的用户数据,即客户端发送到服务器的数据
			String recordName = request.getParameter("recordName");
			String recordId = request.getParameter("recordId");
			String staffId = request.getParameter("staffId");
			String oldMemo = request.getParameter("oldMemo");
			String newMemo = request.getParameter("newMemo");
			//String inTime = request.getParameter("inTime");
			System.out.println(staffId +"\t"+ recordName +"\t"+ recordId  +"\t"+ newMemo  +"\t"+ oldMemo);
			// 创建用户对象
			TRecordInfo record = new TRecordInfo();
			TStaffInfo staff = new TStaffInfo();
			staff.setStaffId(Integer.parseInt(staffId));
			// 创建部门对象
			//TDeptInfo dept = new TDeptInfo();
			// 设置部门ID
			//dept.setDeptId(Integer.parseInt(deptId));
			// 封装用户的数据
			//staff.setTDemoDept(dept);
			record.setRecordId(Integer.parseInt(recordId));
			record.setRecordName(recordName);
			record.setTStsffInfo(staff);
			record.setRecordPaper("由"+oldMemo+"到"+newMemo);
			// 创建时间格式化对象

			// 通过Dao接口，访问用户表，保持用户数据
			System.out.println("---------------2");
			iDaoRecordService.update(record);
			
			//返回列表界面,更新数据
			staffRecord(request,response);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除档案信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void recordDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println(2);
			//获得请求中的数据：ID
			String[] ids = request.getParameterValues("id"); 
			//通过Dao接口，访问用户表，删除数据
			iDaoRecordService.delete(ids);
			//返回用户列表界面，更新用户数据
			staffRecord(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
