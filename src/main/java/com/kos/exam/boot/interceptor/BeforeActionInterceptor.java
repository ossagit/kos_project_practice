package com.kos.exam.boot.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kos.exam.boot.service.MemberService;
import com.kos.exam.boot.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {
	
	@Autowired
	private MemberService memberService;
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		//Rq객체가 자동으로 만들어지기때문에 필요없음.
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}