package com.wangpin.bbs.userManage.controller;


import com.alibaba.fastjson.JSONArray;
import com.wangpin.bbs.userManage.bean.User;
import com.wangpin.bbs.userManage.bean.UserVo;
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
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/userManage")
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;


	Logger logger = LoggerFactory.getLogger(UserController.class);

	//登录返回代码
	String string="loginResult";
	//登录页面
	String login="user/login";
	//验证码
	String randCheckCode="randCheckCode";
	//修改密码页面
	String useredit="user/usereditpwd";


	@RequestMapping({"/login"})
	public String toLogin(Model model) {
		model.addAttribute(string, "null");
		return login;
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
	public String toRegister(){

		return "user/reg";
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
	 * 跳转到用户信息页面
	 * @return
	 */
	@PostMapping(value = "toUserInfo")
	public String editUserinfo(){
		return "user/userIofo";
	}

	/**
	 * 保存用户信息
	 * @return
	 */
	@PostMapping(value = "saveUserInfo")
	public String saveUserinfo(@RequestBody User user){
		userServiceImpl.userInfoUpload(user);
		return "user/userIofo";
	}

	/**
	 * 跳转到修改密码页面
	 *
	 * @return 跳转到修改密码页面
	 */
	@RequestMapping(value = "user/toEditPwd")
	public String editPwd(HttpServletResponse res,int loginResult,Model model) {
		model.addAttribute(string, loginResult);
		return useredit;
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
	public String editUserpwd(HttpServletResponse response, HttpServletRequest request,
							  @RequestParam("newPassword") String new_password, @RequestParam ("oldPassword") String old_password, Model model)
			throws IOException {
		// 从session取值
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int result = 1;
		if (user==null)
			return "非法操作，请登录后进行！";
		if (!user.getPassword().equals(DigestUtils.sha1Hex(old_password))) {
			//model.addAttribute(string, 1);
			return "请输入正确的旧密码";
		}
		else {
			// 设置新密码
			user.setPassword(DigestUtils.sha1Hex(new_password));
			ResultDomain<User> resultDomain = userServiceImpl.userInfoUpload(user);
			result = resultDomain.getResultCode();
			return resultDomain.getResultMsg();
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
		log.info(user.toString());
		if (user==null)
			return "/login";
		else {
			if (user.getUserName()==name){
				model.addAttribute("user",user);
			}
			else{
				User user2=userServiceImpl.userFindByName(name).getResultData();
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
	 * 跳转到用户中心
	 * @param uid
	 * @return
	 */
	@RequestMapping(value ="/index")
	public  String toUserIndex(int uid){

		return "user/index";
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
