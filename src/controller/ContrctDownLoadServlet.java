package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.hr.data.TContractInfo;
import com.hr.data.TStaffInfo;
import com.hr.database.IDaoService;
import com.hr.database.impl.ContractInfoDao;
import com.hr.database.impl.StaffInfoDao;

/**
 * Servlet implementation class ContrctDownLoadServlet
 */
@WebServlet("/contrctdownload")
@MultipartConfig
public class ContrctDownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IDaoService<TContractInfo> iDaoService = new ContractInfoDao();
	IDaoService<TStaffInfo> staffIndoService = new StaffInfoDao();
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String status = request.getParameter("status");
		
		String contrctId = request.getParameter("id");
		
		if ("query".equals(status)) {
			query(request, response);
		}else if ("isdownload".equals(status)) {
			download(request, response, contrctId);
		}else if("upload".equals(status)){
			upload(request, response);
		}
	}
	
	protected void upload(HttpServletRequest request, HttpServletResponse response) {
		try {

			Part part = request.getPart("file");
			System.out.println(part.getName() + "    " + part.getSubmittedFileName());
			String fileName = part.getSubmittedFileName();
			System.out.println(fileName);
			// 插入合同数据 10000310.doc
			String contract = fileName.substring(0, 8);
			String contractId = contract.substring(6, 8);
			String contractTime = contract.substring(4, 6);
			System.out.println("contract:"+contract);
			System.out.println("id:" + contractId);
			System.out.println("time:" + contractTime);
			if (Integer.parseInt(contractId) < 10) {
				contractId = contractId.substring(1, 2);
			}
			if (Integer.parseInt(contractTime) < 10) {
				contractTime = contractTime.substring(1, 2);
			}
			System.out.println("id:" + contractId);
			System.out.println("time:" + contractTime);
			int row = staffIndoService.querys(Integer.parseInt(contractId));
			System.out.println("row:"+row);
			if (row == 1) {
				TContractInfo contractInfo = new TContractInfo();
				//文件存在
				int rowContract = iDaoService.querys(Integer.parseInt(contract));
				System.out.println("rowContract:"+rowContract);
				if(rowContract == 1){
					// 转发数据
					request.setAttribute("result", "合同已存在！");
					// 返回用户列表界面
//					request.getRequestDispatcher("/Contract/contract.jsp").forward(request, response);
					query(request, response);
				}else{
					//文件不存在
					contractInfo.setContractId(Integer.parseInt(contract));
					TStaffInfo info = new TStaffInfo();
					info.setStaffId(Integer.parseInt(contractId));
					contractInfo.setContractTime(contractTime + "年");
					contractInfo.setTStsffInfo(info);
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					contractInfo.setContractTimeEnd(new Date(format.parse("2018-06-07").getTime()));
					contractInfo.setContractTimeStart(new Date(format.parse("2017-03-05").getTime()));
					contractInfo.setContratType("委托合同");
					iDaoService.add(contractInfo);
					
					part.write(getServletContext().getRealPath("/upload/") + "/" + fileName);
					String src = getServletContext().getRealPath("/upload/") + "/" + fileName;

					FileInputStream fis = new FileInputStream(new File(src));
					FileOutputStream fos = new FileOutputStream(
							new File("C:/Users/Administrator/Desktop/17.6实训/hrmanager/WebContent/download/" + fileName));
					byte[] b = new byte[1024];
					int len = 0;
					while ((len = fis.read(b)) != -1) {
						fos.write(b, 0, len);
					}
					fis.close();
					fos.close();

					System.out.println(contractId + "  " + contractTime);
					
					// 转发数据
					request.setAttribute("result", "文件上传成功！");
					// 返回用户列表界面
//					request.getRequestDispatcher("/Contract/contract.jsp").forward(request, response);
					
					query(request, response);
				}
			} else {
				// 转发数据
				request.setAttribute("result", "合同无效，无法上传！");
				query(request, response);
//				// 返回用户列表界面
//				request.getRequestDispatcher("/Contract/contract.jsp").forward(request, response);
			}
			
			query(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void query(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 获得查询条件用户名
			String contratType = request.getParameter("contratType");
			// 创建用户对象
			TContractInfo contractInfo = new TContractInfo();
			List<TContractInfo> list = null;
			
			if("".equals(contratType) || contratType==null){
				// 访问用户表条件查询用户
				list = iDaoService.query(contractInfo);
			}else{
				// 设置查询条件
				contractInfo.setContratType("like '%" + contratType + "%'");
				// 访问用户表条件查询用户
				list = iDaoService.query(contractInfo);
			}
			// 转发数据
			request.setAttribute("list", list);
			
			System.out.println("size:"+list.size());
			
			// 返回用户列表界面
			request.getRequestDispatcher("/Contract/contract.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void download(HttpServletRequest request, HttpServletResponse response, String contrctId) {
		try {
			// 获得请求文件名
			String filename = "download/"+contrctId + ".doc";
			// 设置文件MIME类型
			response.setContentType(getServletContext().getMimeType(filename));
			// 设置Content-Disposition
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);
			// 读取目标文件，通过response将目标文件写到客户端
			// 获取目标文件的绝对路径
			String fullFileName = getServletContext().getRealPath(filename);
			System.out.println(fullFileName);
			// 读取文件
			InputStream in;
			in = new FileInputStream(fullFileName);
			OutputStream out = response.getOutputStream();

			// 写文件
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
			out.close();
//			query(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
