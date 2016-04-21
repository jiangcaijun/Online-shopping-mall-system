package com.eshop.controller.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.eshop.utils.Image;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String savePath;
	String tempPath;//用户临时目录
	String []url;  //存放图片url地址
	
	String mainFile;//商品主图名称
	String mainUrl;//商品主图地址
	int uid;
	 

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
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
		response.setContentType("text/html; charset=utf-8");
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		@SuppressWarnings("unused")
		PrintWriter out = response.getWriter();
		
		//获取username
		if(session.getAttribute("uid")!=null && !session.getAttribute("uid").equals("")){
			uid=(int) session.getAttribute("uid");
		}else{
			uid = 3;
		}
		
		//获取主图地址
		if(request.getParameter("mainfile")!=null && !request.getParameter("mainfile").equals("")){
			mainFile=request.getParameter("mainfile");
		}
		
		//System.out.println(request.getParameter("action"));
		if (request.getParameter("action") != null && request.getParameter("action").equals("send")) {
			//保存头像
			System.out.println("tempPath1:"+tempPath);
			File file = new File(tempPath);
			File[] files = file.listFiles();
			//System.out.println(savePath);
			if(files!=null&&files.length>0){
				url = new String[files.length];
				for(int i=0;i<files.length;i++){
					File file1 = files[i];
					String filename = file1.getName();
					//获取文件名
					//System.out.println("filename"+filename);
					String saveFileName = makeFileName(filename); //新的图片名
					//新的保存目录
					String newSavePath = makePathS(saveFileName, savePath); 
					String old = String.valueOf(file1);
                    File oldd = new File(old);
                    File rename = new File(newSavePath+ "\\" +saveFileName);//图片新的保存路径
                   // System.out.println(rename);
                   
                    oldd.renameTo(rename);
                    String headPic = String.valueOf(rename);
                    Image image1 = new Image(headPic,headPic+"70x70.jpg");
                	image1.resize(70,70);
                    String filePath = Judge(rename);
                    url[i]= filePath;
                    if(filename.equals(mainFile)){
        				mainUrl=filePath;
        			}
                    if(request.getParameter("operate")!=null && request.getParameter("operate").equals("item")){
                    	String path = newSavePath+ "\\" +saveFileName;
                    	System.out.println(path);
                    	Image image = new Image(path,path+"95×100.jpg");
                    	image.resize(95,100);
                    	image = new Image(path,path+"359×351.jpg");
                    	image.resize(359,351);
                    	image = new Image(path,path+"191×157.jpg");
                    	image.resize(191,157);
                    }
				}
				if(request.getParameter("operate")!=null && request.getParameter("operate").equals("item")){
					request.setAttribute("url", url);
					request.setAttribute("mainUrl", mainUrl);
					request.getRequestDispatcher("/item?action=item_add").forward(request, response);
				}else{
					request.setAttribute("action","saveHead");
					request.setAttribute("url", url);
					//request.setAttribute("mainUrl", mainUrl);
					request.getRequestDispatcher("/user").forward(request, response);
				}
			}
		} else if (request.getParameter("action") != null && request.getParameter("action").equals("del")) {
			//删除头像，重新选择
			
			//删除商品图片
			String itemName = "";
			if(request.getParameter("itemName")!=null && !request.getParameter("itemName").equals("")){
				itemName = request.getParameter("itemName");
			}
			if(request.getParameter("operate")!=null && request.getParameter("operate").equals("item")){
				String filePath="";
				filePath = savePath+"/"+String.valueOf(uid) + "_" + "temp"+"/"+itemName;
				System.out.println(filePath);
				File file = new File(filePath);
				if(file.exists()){
		            //创建目录
		            file.delete();
		        }
			}
		} else if(request.getParameter("action")!=null&&request.getParameter("action").equals("delPic")){
			//删除临时图片
			String filename =  new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
			File file = new File(tempPath+"\\"+filename);
			file.delete();
		}else if(request.getParameter("action")!=null&&request.getParameter("action").equals("clear")){
			//用户退出或注销后，清空uid_temp
		}else if(request.getParameter("action")!=null&&request.getParameter("action").equals("saveComPic")){
			//评论图片
			File file = new File(tempPath);
			File[] files = file.listFiles();
			//System.out.println(savePath);
			if(files!=null&&files.length>0){
				url = new String[files.length];
				for(int i=0;i<files.length;i++){
					File file1 = files[i];
					String filename = file1.getName();
					//获取文件名
					//System.out.println("filename"+filename);
					String saveFileName = makeFileName(filename); //新的图片名
					//新的保存目录
					String newSavePath = makePathS(saveFileName, savePath); 
					String old = String.valueOf(file1);
                    File oldd = new File(old);
                    File rename = new File(newSavePath+ "\\" +saveFileName);//图片新的保存路径
                   // System.out.println(rename);
                    oldd.renameTo(rename);
                    String yasuoPic = String.valueOf(rename);
                   // System.out.println(yasuoPic);
                    Image image = new Image(yasuoPic,yasuoPic+"350×350.jpg");
                	image.resize(350,350);
                	Image image1 = new Image(yasuoPic,yasuoPic+"50×50.jpg");
                 	image1.resize(50,50);
                    String filePath = Judge(rename);
                    url[i]= filePath;
				}
				String commentId = getUuid();
				session.setAttribute("commentId", commentId);
				request.setAttribute("action","saveComPic");
				session.setAttribute("commentId", "saveComPic");
				request.setAttribute("url", url);
				request.getRequestDispatcher("/user").forward(request, response);
			}
		}else{
			String allowedTypes = "jpg,jpeg,gif,png";
			// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
			savePath = this.getServletContext().getRealPath("/customer/image");
			if(request.getParameter("operate")!=null && request.getParameter("operate").equals("item")){
					savePath = this.getServletContext().getRealPath("/admin/item");
			}
			//int uid = (int) session.getAttribute("uid");// 用户uid
			//System.out.println("savePath:"+savePath);
			
			System.out.println(uid);
			System.out.println(request.getParameter("operate"));
			if(request.getParameter("operate")!=null && request.getParameter("operate").equals("item")){
				tempPath = makePath(savePath, uid);// 获取临时保存目录
				System.out.println(tempPath+" "+1);
			}else{
				uid =3;
				tempPath = makePath(savePath, uid);// 获取临时保存目录
			}
			
			//System.out.println("tempPath:"+tempPath);
			File tmpFile = new File(tempPath);
			if (!tmpFile.exists()) {
				// 创建临时目录
				tmpFile.mkdirs();
			}

			@SuppressWarnings("unused")
			String message = "";
			try {
				// 使用Apache文件上传组件处理文件上传步骤：
				// 1、创建一个DiskFileItemFactory工厂
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// 设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
				factory.setSizeThreshold(1024 * 100);// 设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
				// 设置上传时生成的临时文件的保存目录
				factory.setRepository(tmpFile);
				// 2、创建一个文件上传解析器
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 监听文件上传进度
				/*
				 * upload.setProgressListener(new ProgressListener(){ public
				 * void update(long pBytesRead, long pContentLength, int arg2) {
				 * System.out.println("文件大小为：" + pContentLength + ",当前已处理：" +
				 * pBytesRead); } });
				 */
				// 解决上传文件名的中文乱码
				upload.setHeaderEncoding("UTF-8");
				// 3、判断提交上来的数据是否是上传表单的数据
				if (ServletFileUpload.isMultipartContent(request)) {
					// 设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
					upload.setFileSizeMax(1024 * 1024);
					// 设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
					upload.setSizeMax(1024 * 1024 * 10);
					// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
					List<FileItem> list = upload.parseRequest(new ServletRequestContext(request));
					for (FileItem item : list) {
						// 如果fileitem中封装的是普通输入项的数据
						if (item.isFormField()) {
							String name = item.getFieldName();
							// 解决普通输入项的数据的中文乱码问题
							String value = item.getString("UTF-8");
							// value = new
							// String(value.getBytes("iso8859-1"),"UTF-8");
							System.out.println(name + "=" + value);
						} else {// 如果fileitem中封装的是上传文件
								// 得到上传的文件名称，
							String filename = item.getName();
							System.out.println(filename);
							if (filename == null || filename.trim().equals("")) {
								continue;
							}
							// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
							// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
							// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
							filename = filename.substring(filename.lastIndexOf("\\") + 1);
							// 得到上传文件的扩展名
							String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
							// 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
							System.out.println("上传的文件的扩展名是：" + fileExtName);
							if (!allowedTypes.contains(fileExtName)) {
								message = "格式不对，只能上传" + allowedTypes;
							}
							// 获取item中的上传文件的输入流
							InputStream in = item.getInputStream();
							// 得到文件保存的名称
							// String saveFilename = makeFileName(filename);
							// 得到文件的保存目录
							String realSavePath = makePath(filename, tempPath);
							//System.out.println("realSavePath"+realSavePath);
							// 创建一个文件输出流
							FileOutputStream fos = new FileOutputStream(realSavePath + "\\" + filename);
							// 创建一个缓冲区
							byte buffer[] = new byte[1024];
							// 判断输入流中的数据是否已经读完的标识
							int len = 0;
							// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
							while ((len = in.read(buffer)) > 0) {
								// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath
								// + "\\" + filename)当中
								fos.write(buffer, 0, len);
							}
							// 关闭输入流
							in.close();
							// 关闭输出流
							fos.close();
							// 删除处理文件上传时生成的临时文件
							item.delete();
							message = "文件上传成功！";
						}
					}
				}
			} catch (FileUploadBase.FileSizeLimitExceededException e) {
				// e.printStackTrace();
				message = "单个文件超出最大值！！！";
			} catch (FileUploadBase.SizeLimitExceededException e) {
				// e.printStackTrace();
				message = "上传文件的总的大小超出限制的最大值！！！";
				return;
			} catch (Exception e) {
				message = "文件上传失败！";
			} finally {
				/*
				 * request.setAttribute("message", message);
				 * //System.out.println("成功");
				 * request.getRequestDispatcher("/demo/me/upload.jsp").forward(
				 * request, response);
				 */
			}

		}
	}

	/**
	 * 根据用户uid创建相关img保存目录
	 * 
	 * @param savePath
	 * @param uid
	 * @return
	 */
	private String makePath(String savePath, int uid) {
		String temp = String.valueOf(uid) + "_" + "temp";
		String dir = savePath + "\\" + temp;
		return dir;
	}

	/**
	 * @Method: makeFileName
	 * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
	 * @param filename
	 *            文件的原始名称
	 * @return uuid+"_"+文件的原始名称
	 */
	private String makeFileName(String filename) { // 2.jpg
		@SuppressWarnings("unused")
		String s = System.currentTimeMillis() + "" + (Math.random() * 10000);
		// 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		// UUID （有时候也会用在oracle的id生成器那里） IEEE
		return UUID.randomUUID().toString() + "_" + filename;
	}

	/**
	 * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
	 * 
	 * @Method: makePath
	 * @Description:
	 * @param filename
	 *            文件名，要根据文件名生成存储目录
	 * @param savePath
	 *            文件存储路径
	 * @return 新的存储目录
	 */
	private String makePath(String filename, String savePath) {
		// 得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
		int hashcode = filename.hashCode();
		@SuppressWarnings("unused")
		int dir1 = hashcode & 0xf; // 0--15
		// int dir2 = (hashcode&0xf0)>>4; //0-15
		// 构造新的保存目录
		String dir = savePath /* + "\\" + dir1 */; // upload\2\3 upload\3\5
		// File既可以代表文件也可以代表目录
		File file = new File(dir);
		// 如果目录不存在
		if (!file.exists()) {
			// 创建目录
			file.mkdirs();
		}
		return dir;
	}
	
	private String makePathS(String filename, String savePath) {
		// 得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf; // 0--15
		// int dir2 = (hashcode&0xf0)>>4; //0-15
		// 构造新的保存目录
		String dir = savePath  + "\\" + dir1 ; // upload\2\3 upload\3\5
		// File既可以代表文件也可以代表目录
		File file = new File(dir);
		// 如果目录不存在
		if (!file.exists()) {
			// 创建目录
			file.mkdirs();
		}
		return dir;
	}
	
	private String getUuid(){
		String s = UUID.randomUUID().toString(); 
		 return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	}
	/**
	 * 删除用户文件夹里的文件以及文件夹
	 * 
	 * @param f
	 */
	@SuppressWarnings("unused")
	private void recurDelete(File f) {
		for (File fi : f.listFiles()) {
			if (fi.isDirectory()) {
				recurDelete(fi);
			} else {
				fi.delete();
			}
		}
		f.delete();
	}
	
	 /**获取文件相对路径
	 * @param file
	 * @return
	 */
	private String Judge(File file){
	    	String newfile = String.valueOf(file);
	    	String[] filenames = newfile.split("\\\\");
	    	String everyFile ="/"+ filenames[filenames.length-3]+"/"+ filenames[filenames.length-2]+"/"+filenames[filenames.length-1];
	    	return everyFile;
	    }

}
