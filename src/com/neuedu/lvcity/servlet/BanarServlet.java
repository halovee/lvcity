package com.neuedu.lvcity.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.alibaba.fastjson.JSONObject;
import com.neuedu.lvcity.common.FileUtils;
import com.neuedu.lvcity.model.Banar;
import com.neuedu.lvcity.service.BanarService;
import com.neuedu.lvcity.service.impl.BanarServiceImpl;

//import jdk.management.resource.internal.inst.FileChannelImplRMHooks;

public class BanarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BanarServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String action = request.getParameter("action");
		if ("findAllBanar".equals(action)) {
			dofindAllBanar(request, response);
		} else if ("addBanar".equals(action)) {
			doAddBanar(request, response);
		} else if ("delBanar".equals(action)) {
			doDelBanar(request, response);
		}
	}

	private void doDelBanar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 璋冪敤Service鏂规硶
		BanarService banarService = BanarServiceImpl.getInstance();
		int banarid = Integer.parseInt(request.getParameter("banarid"));
		Banar banar = new Banar();
		banar.setBanarid(banarid);
		int res = banarService.deleteBanar(banar);
		System.out.println(res);
		Map<String, Object> map = new HashMap<String, Object>();
		if (res > 0) {
			map.put("success", true);
		} else {
			map.put("success", false);
			map.put("errorMsg", "delete contact fail !!!");
		}

		String str = JSONObject.toJSONString(map);
		response.getWriter().write(str);

	}

	@SuppressWarnings("unchecked")
	private void doAddBanar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 鑾峰彇session
		HttpSession se = request.getSession();
		// 璋冪敤Service鏂规硶
		BanarService banarService = BanarServiceImpl.getInstance();

		// 涓婁紶鏂囦欢鐨勭洰褰�
		//	 String path = request.getSession().getServletContext().getRealPath("/")+"images";    //鏂囦欢绯荤粺鐨勮矾寰�
        // String path= System.getProperty("catalina.home") + "\\webapps\\lvcityFG\\images\\";    //tomcat涓嬮潰鐨勮矾寰�
       				
		//鍒涘缓鏂囦欢瀵硅薄
		File savePath = new File(FileUtils.UPLOAD_PATH);	
		
		// 妫�鏌ユ垜浠槸鍚︽湁鏂囦欢涓婁紶璇锋眰
		//boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		// 1,澹版槑DiskFileItemFactory宸ュ巶绫伙紝鐢ㄤ簬鍦ㄦ寚瀹氱鐩樹笂璁剧疆涓�涓复鏃剁洰褰�
		DiskFileItemFactory disk = new DiskFileItemFactory(1024 * 10, savePath);
	
		// 2,澹版槑ServletFileUpload锛屾帴鏀朵笂杈圭殑涓存椂鏂囦欢銆備篃鍙互榛樿鍊�
		ServletFileUpload up = new ServletFileUpload(disk);		

		try {
			// 3,瑙ｆ瀽request
			List<FileItem> list;
			list = up.parseRequest(request);
			// 濡傛灉灏变竴涓枃浠讹紝
			FileItem file = list.get(0);
			// 鑾峰彇鏂囦欢鍚嶏細
			String fileName = file.getName();
			
			// 鑾峰彇鏂囦欢鐨勭被鍨嬶細
			//String fileType = file.getContentType();
			// 鏂囦欢澶у皬
	     	//	int size = file.getInputStream().available();
			
			// 鑾峰彇鏂囦欢鐨勮緭鍏ユ祦锛岀敤浜庤鍙栬涓婁紶鐨勬枃浠�
			InputStream in = file.getInputStream();
			
			// 澹版槑杈撳嚭瀛楄妭娴侊紝鐢ㄤ簬灏嗘枃浠朵笂浼犲埌鐩殑浣嶇疆
			OutputStream out = new FileOutputStream(new File(savePath + "/" + fileName));
		
			
			// 鏂囦欢copy
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
				//out2.write(b, 0, len);
			}
			out.flush();
			out.close();
		

			// 鍒犻櫎涓婁紶鐢熸垚鐨勪复鏃舵枃浠�
			file.delete();
			
			//缃戞暟鎹簱娣诲姞璁板綍锛屽湪鍓嶉潰鍔犱笂鍥剧墖鏈嶅姟鍣ㄧ殑鍓嶇紑			
			int result = banarService.addBanar("http://localhost:9080/uploads/" + fileName);
			
			//鐢熸垚杩斿洖缁撴灉鐨刴ap
			Map<String, Object> map = new HashMap<String, Object>();
			if (result > 0) {
				map.put("success", true);
			} else {
				map.put("success", false);
				map.put("errorMsg", "Save user fail !");
			}
           //灏唌ap杞垚JSON瀵硅薄
			String str = JSONObject.toJSONString(map);
			//杩斿洖缁撴灉
			response.getWriter().write(str);

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void dofindAllBanar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// page锛氶〉鐮侊紝璧峰鍊� 1銆� rows锛氭瘡椤垫樉绀鸿銆� page涓哄墠鍙拌鏌ヨ鐨勯〉锛宺ows涓哄墠鍙扮殑姣忛〉璁板綍鏁�
		int rows = Integer.parseInt(request.getParameter("rows"));
		// System.out.println(rows);
		int page = Integer.parseInt(request.getParameter("page"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startPage", (page - 1) * rows);
		pageMap.put("endPage", rows);

		// 璋冪敤Service鏂规硶
		BanarService banarService = BanarServiceImpl.getInstance();
		//鏌ユ壘鎸囧畾椤电殑璁板綍
		List<Banar> list = banarService.findAllBanar(pageMap);
		// System.out.println(list.size());
		//鏌ユ壘鎬昏褰曟暟
		int total = banarService.banarCount();

		// JSON涓紝total璁板綍鎬绘暟锛宺ows璁板綍銆倀otal涓哄悗鍙拌繑鍥炵殑锛堟暟鎹簱鐨勶級鎬昏褰曟暟锛岋紙绗簩涓級rows涓哄悗鍙拌繑鍥炵殑json瀵硅薄鏁扮粍銆�
		map.put("rows", list);
		map.put("total", total);
		String str = JSONObject.toJSONString(map);
		// System.out.println(map.toString());
		response.getWriter().write(str);

		/*
		 * //鑾峰彇session HttpSession se = request.getSession(); // 璋冪敤Service鏂规硶
		 * BanarService banarService = BanarServiceImpl.getInstance();
		 * List<Banar> banars = null; // 鏌ヨ鎵�鏈夌敤鎴� banars =
		 * banarService.findAllBanar(); if(banars != null) { //
		 * System.out.println(banars.size()); se.setAttribute("banarList",
		 * banars); response.sendRedirect(request.getContextPath() +
		 * "/Admin/banar.jsp");
		 * 
		 * }
		 */
	}

}
