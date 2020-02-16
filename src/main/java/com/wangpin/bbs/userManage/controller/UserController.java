package com.wangpin.bbs.userManage.controller;


import com.wangpin.bbs.topicManage.bean.Topic;
import com.wangpin.bbs.topicManage.service.implement.TopicServiceImpl;
import com.wangpin.bbs.userManage.bean.User;
import com.wangpin.bbs.userManage.service.implement.UserServiceImpl;
import com.wangpin.bbs.utils.CodeUtil;
import com.wangpin.bbs.utils.ResultDomain;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/userManage")
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private TopicServiceImpl topicServiceImpl;


	Logger logger = LoggerFactory.getLogger(UserController.class);

	//登录返回代码
	String string="loginResult";
	//登录页面
	String login="user/login";
	//验证码
	String randCheckCode="randCheckCode";

	@RequestMapping({"/login"})
	public String toLogin(Model model, HttpServletRequest request ) {
		model.addAttribute(string, "null");
		HttpSession session=request.getSession();
		User user= (User) session.getAttribute("user");
		if (user==null)
			return login;
		else
			return "redirect:/userManage/home?name="+user.getUserName();
	}

	
	@PostMapping("/login/authen")
	@ResponseBody
	public ResultDomain authen(HttpServletResponse response, String userName, String password, String securitycode,
						 HttpSession session, Model model, String rememberMe, HttpServletRequest request) throws Exception {

		ResultDomain<User> resultDomain = userServiceImpl.userFindByName(userName);
		String password2 =DigestUtils.sha1Hex(password.getBytes("UTF-8"));
		User user=resultDomain.getResultData();
		if (user==null){
			resultDomain.setResultMsg("用户不存在！请检查用户名和密码是否正确");
			resultDomain.setResultCode(-1);
			return resultDomain;
		}
		else {
			log.info(user.toString());
			Subject subject = SecurityUtils.getSubject();
			// 2.封装用户数据
			UsernamePasswordToken token = new UsernamePasswordToken(userName,password2 );
			// 3.执行登录方法
			String verifyCode=(String) session.getAttribute("VerifyCode");
			if (verifyCode==null){
				resultDomain.setResultMsg("验证码无效！");
				resultDomain.setResultCode(-1);
				return resultDomain;
			}
			if (verifyCode!=null){
				if (!securitycode.equalsIgnoreCase(verifyCode)){
					log.debug(securitycode+":"+verifyCode);
					resultDomain.setResultMsg("验证码错误！");
					resultDomain.setResultCode(-1);
					return resultDomain;
				}
			}
			try{
				subject.login(token);
				session.setAttribute("user",user);
				model.addAttribute("user",user);
				user.setLastTime(new Date());
				userServiceImpl.userInfoUpload(user);
				log.info("登录成功");
				resultDomain.setResultMsg("登录成功！");
				resultDomain.setResultCode(1);
				return resultDomain;
			}catch (Exception e){
				resultDomain.setResultMsg("密码错误！");
				resultDomain.setResultCode(-1);
				return resultDomain;
			}
			//return "redirect:/main"; //重定向到跳转到首页的方法,改变地址栏为首页地址
		}

	}

	/**
	 * 获取验证码图片
	 * @param request
	 * @param response
	 */
	@GetMapping("/verifyCode")
	public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map map=CodeUtil.generateCodeAndPic();
			BufferedImage imageNew=(BufferedImage)map.get("codePic");
			String code= (String) map.get("code");
			ByteArrayOutputStream outStream =new ByteArrayOutputStream();
			ImageIO.write(imageNew, "jpg", outStream);

			log.info(code);
			//将验证码绑定session
			request.getSession().setAttribute("VerifyCode", code);
			//设置响应头
			response.setHeader("Pragma", "no-cache");
			//设置响应头
			response.setHeader("Cache-Control", "no-cache");
			//在代理服务器端防止缓冲
			response.setDateHeader("Expires", 0);
			//设置响应内容类型
			response.setContentType("image/jpeg");
			response.getOutputStream().write(outStream.toByteArray());
			response.getOutputStream().flush();
		} catch (IOException e) {
			log.info("验证码生成异常", e);
		}
	}

	/**
	 * @param
	 * @return
	 */
	@GetMapping(value = "/toRegist")
	public String toRegister(HttpServletRequest request){
		HttpSession session=request.getSession();
		User user= (User) session.getAttribute("user");
		if (user==null)
			return "user/reg";
		else
			return "redirect:/userManage/home?name="+user.getUserName();
	}

	@PostMapping(value = "/userRegist")
	@ResponseBody
	public ResultDomain userRegist(User user) throws Exception {
		ResultDomain resultDomain=new ResultDomain();
		System.out.println(user);
		user.setRegistTime(new Date());
		user.setLastTime(new Date());
		String password =DigestUtils.sha1Hex(user.getPassword().getBytes("UTF-8"));
		user.setPassword(password);
		user.setGold(0);
		user.setIdentity("普通用户");
		user.setVipGrade(0);
		user.setSpend(0);
		resultDomain=userServiceImpl.userAdd(user);
		log.info(resultDomain.getResultMsg());
			return resultDomain;

	}

	/**
	 * 邮箱验证
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		String regExp = "^[^0-9][\\w_]{5,9}$";
		if(email.matches(regExp)) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 校验QQ
	 * @param qq
	 * @return ResultDomain
	 */
	public static boolean checkQQ(String qq)
	{

		String reg = "[1-9][0-9]{6,14}";
		if(qq.matches(reg))
			return true;
		else
			return false;
	}

	/**
	 * 用户名验证，字母数字下划线组合，不能以数字开头
	 * @param name
	 * @return
	 */
	public static boolean checkName(String name) {
		String regExp = "^[^0-9][\\w_]{5,9}$";
		if(name.matches(regExp)) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 密码验证,任意非空白字符8-20
	 * @param pwd
	 * @return
	 */
	public static boolean checkPwd(String pwd) {
		String regExp = "^[\\S]{8,20}$";
		if(pwd.matches(regExp)) {
			return true;
		}
		return false;
	}


	/**
	 * 修改密码
	 * 
	 * @param response     页面弹框
	 * @param request      从session取值
	 * @param new_password 新密码
	 * @return 修改成功跳转回登录页面
	 * @throws IOException
	 */
	@PostMapping(value = "editUserPwd")
	@ResponseBody
	public ResultDomain editUserpwd(HttpServletResponse response, HttpServletRequest request,
							  @RequestParam("newPassword") String new_password, @RequestParam ("oldPassword") String old_password, Model model)
			throws IOException {
		// 从session取值
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ResultDomain<User> resultDomain=new ResultDomain<>();
		if (user==null){
			resultDomain.setResultMsg("非法操作！请登陆后进行！");
			resultDomain.setResultCode(-1);
			return resultDomain;
		}
		if (!user.getPassword().equals(DigestUtils.sha1Hex(old_password))) {
			resultDomain.setResultMsg("旧密码错误！请重新输入！");
			resultDomain.setResultCode(-1);
			return  resultDomain;
		}
		else {
			// 设置新密码
			user.setPassword(DigestUtils.sha1Hex(new_password));
			resultDomain = userServiceImpl.userInfoUpload(user);
			return resultDomain;
		}
	}

	/**
	 * 跳转到用户主页
	 * @param name
	 * @return
	 */
	@RequestMapping(value ="/home")
	public  String toUserHome(HttpServletRequest request,Model model,@RequestParam String name){
		HttpSession session=request.getSession();
		User user = (User) session.getAttribute("user");
		if (user==null)
			return "/login";
		else {
			if (user.getUserName()==name){
				model.addAttribute("user",user);
			}
			else{
				User user2=userServiceImpl.userFindByName(name).getResultData();
				if (user2==null)
					user2=userServiceImpl.userFindByNickname(name).getResultData();
				model.addAttribute("user",user2);

			}
		}
		return "user/home";

	}

	/**
	 * 跳转到基本设置
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/set")
	public  String toUserSet(HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
		User user = (User) session.getAttribute("user");
		model.addAttribute("user",user);
		return "user/set";
	}

	/**
	 * 保存用户信息
	 * @param
	 * @return
	 */
	@PostMapping (value ="/set/saveUserInfo")
	@ResponseBody
	public  ResultDomain<User> SaveUserSet(HttpServletRequest request,User user){
		HttpSession session=request.getSession();
		User user1= (User) session.getAttribute("user");
		ResultDomain<User> userResultDomain=new ResultDomain<>();
		if (user!=null){
			user1.setEmail(user.getEmail());
			user1.setNickname(user.getNickname());
			user1.setSex(user.getSex());
			user1.setSignature(user.getSignature());
			user1.setCity(user.getCity());
			user1.setBirthday(user.getBirthday());
			userResultDomain=userServiceImpl.userInfoUpload(user1);
		}
		else {
			userResultDomain.setResultMsg("用户未登录");
			userResultDomain.setResultCode(-1);
		}
		return userResultDomain;
	}

	//资源上传
	@PostMapping (value="/uploadImage")
	@ResponseBody//json交互注解
	public Map<String, Object> uploadResource(HttpServletRequest request, HttpServletResponse response, HttpSession session, MultipartFile file) throws Exception{
		User user = (User) session.getAttribute("user");
		String prefix="";
		String dateStr="";
		//保存上传
		OutputStream out = null;
		InputStream fileInput=null;
		if(user==null){
			Map<String,Object> map=new HashMap<>();
			map.put("code",-1);
			map.put("msg","用户尚未登录！");
			return map;
		}

		try{
			if(file!=null){
				String originalName = file.getOriginalFilename();
				prefix=originalName.substring(originalName.lastIndexOf(".")+1);
				Date date = new Date();
				String uuid = UUID.randomUUID()+"";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dateStr = simpleDateFormat.format(date);
				String filepath = "C:\\Users\\1\\IdeaProjects\\bbs\\userImg\\" + dateStr+"_"+uuid+"." + prefix;
				File files=new File(filepath);
				//打印查看上传路径
				System.out.println(filepath);
				if(!files.getParentFile().exists()){
					files.getParentFile().mkdirs();
				}
				file.transferTo(files);
				Map<String,Object> map2=new HashMap<>();
				Map<String,Object> map=new HashMap<>();
				map.put("code",1);
				map.put("msg","上传成功！");
				map.put("data",map2);
				map.put("src","/userImg/"+ dateStr+"_"+uuid+"." + prefix);
				user.setImage("/userImg/"+ dateStr+"_"+uuid+"." + prefix);
				log.info("用户头像上传成功！保存信息中。。。");
				userServiceImpl.userInfoUpload(user);
				return map;
			}

		}catch (Exception e){
		}finally{
			try {
				if(out!=null){
					out.close();
				}
				if(fileInput!=null){
					fileInput.close();
				}
			} catch (IOException e) {
			}
		}
		Map<String,Object> map=new HashMap<>();
		map.put("code",0);
		map.put("msg","上传失败！");
		return map;
	}

	/**
	 * 跳转到用户中心
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/index")
	public  String toUserIndex(HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
		User user = (User) session.getAttribute("user");
		model.addAttribute("user",user);
		return "user/index";
	}

	/**
	 * 跳转到消息
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/message")
	public  String toUserMessage(HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
		User user = (User) session.getAttribute("user");
		model.addAttribute("user",user);
		return "user/message";
	}

	/**
	 * 注销
	 * 
	 * @param request
	 * @return 返回登录页面
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request,Model model) {
		// 清除session
		Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
        	subject.logout();
        }
		HttpSession session=request.getSession();
        session.removeAttribute("user");
		//User user= (User) session.getAttribute("user");
		//model.addAttribute(string, "null");
		return  "redirect:/userManage/main";
	}

	@RequestMapping(value ={"/main"})
	private  String toStart(HttpSession httpSession,Model model) {
		log.info("主页");
		User user=(User)httpSession.getAttribute("user");
		List<Topic> topicsTop=topicServiceImpl.queryModuleTopic(null,null,null,0,1).getResultData();
		List<Topic> topicsAll=topicServiceImpl.queryModuleTopic(null,null,null,0,0).getResultData();
		model.addAttribute("topicsTop",topicsTop);
		model.addAttribute("topicsAll",topicsAll);
		for (Topic topic:topicsTop) {
			System.out.println(topic);
		}
		for (Topic topic:topicsAll) {
			System.out.println(topic);
		}
		if (user!=null){
			log.info(user.toString());
			model.addAttribute("user",user);
			model.addAttribute("loginResult",1);
		}
		else {
			log.info("未登录！");
			model.addAttribute("user",user);
			model.addAttribute("loginResult",-1);
		}
			return "index";
	}
}
