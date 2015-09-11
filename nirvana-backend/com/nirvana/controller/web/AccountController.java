package com.nirvana.controller.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nirvana.service.BackEndAccountService;
import com.nirvana.service.XCaptchaService;

@Controller
@RequestMapping("/backend")
public class AccountController {
	@Resource
	private XCaptchaService xCaptchaService;
	@Resource
	private CacheManager cacheManager;
	@Resource
	private BackEndAccountService backEndAccoutService;

	@RequestMapping("/web/login.html")
	public String login() {
		return "login";
	}

	@RequestMapping("/web/resetpwd/step1")
	public String resetPwdStep1(HttpServletRequest request) {
		return "forgetpwd";
	}

	@RequestMapping(value = "/web/resetpwd/step2", method = { RequestMethod.POST })
	public String resetPwdStep2(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		String phone = request.getParameter("phone");
		String category = request.getParameter("category");
		if (xCaptchaService.validate(phone, category, true)) {
			HttpSession session = request.getSession();
			session.setAttribute("phone", phone);
			session.setMaxInactiveInterval(300);
			return "redirect:/backend/web/resetpwd/step2";
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "请先获取验证码");
			return "redirect:/backend/web/resetpwd/step1";
		}
	}

	@RequestMapping(value = "/web/resetpwd/step3", method = { RequestMethod.POST })
	public String resetPwdStep3(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		String pwd = request.getParameter("password");
		String repwd = request.getParameter("repeatPassword");
		String phone = (String) session.getAttribute("phone");
		if (phone == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "由于您长时间未操作，请重新输入账号");
			return "redirect:/backend/web/resetpwd/step1";
		} else if (pwd != null && pwd.equals(repwd)) {
			try {
				backEndAccoutService.resetPassword(phone, pwd);

			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("errorMessage", "用户不存在");
				return "redirect:/backend/web/resetpwd/step1";
			}
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "密码不一致");
			return "redirect:/backend/web/resetpwd/step2";
		}
		return "redirect:/backend/web/resetpwd/step3";
	}

	@RequestMapping(value = "/web/resetpwd/step2", method = { RequestMethod.GET })
	public String resetPwdStep2Get(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		String phone = (String) session.getAttribute("phone");
		if (phone == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "请输入手机号");
			return "redirect:/backend/web/resetpwd/step1";
		}
		return "resetPwdStep2";
	}

	@RequestMapping(value = "/web/resetpwd/step3", method = { RequestMethod.GET })
	public String resetPwdStep3Get(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		String phone = (String) session.getAttribute("phone");
		if (phone == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "请输入手机号");
			return "redirect:/backend/web/resetpwd/step1";
		}
		session.invalidate();
		return "resetPwdStep3";
	}
}
