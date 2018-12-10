package org.iauhsoaix.config;

import org.iauhsoaix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserService userService;

	/**
	 * 通过 @EnableWebMvcSecurity 注解开启Spring Security的功能 继承
	 * WebSecurityConfigurerAdapter ，并重写它的方法来设置一些web安全的细节 configure(HttpSecurity
	 * http) 方法 通过 authorizeRequests() 定义哪些URL需要被保护、哪些不需要被保护。例如以上代码指定了 / 和 /home
	 * 不需要任何认证就可以访问，其他的路径都必须通过身份验证。 通过 formLogin() 定义当需要用户登录时候，转到的登录页面。
	 * 
	 */

	// 为什么有三个configure?第一个configure是拿来干嘛的
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * SecurityBuilder(AuthenticationManagerBuilder)用来创建一个AuthenticationManager。
		 * 允许轻松构建内存身份验证，LDAP身份验证， 基于JDBC的身份验证，添加UserDetailsService和添加
		 * AuthenticationProvider。 getDefaultUserDetailsService()
		 * 获取默认UserDetailsService的 AuthenticationManagerBuilder。
		 * PasswordEncoder是实现加密解密的接口
		 */
		auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder() {
			/*
			 * (non-Javadoc) 由我们自己调用
			 * 
			 * @see
			 * org.springframework.security.crypto.password.PasswordEncoder#encode(java.lang
			 * .CharSequence)
			 */
			@Override
			public String encode(CharSequence charSequence) {
				return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
			}

			/**
			 * @param charSequence 明文
			 * @param s            密文
			 * @return由spring调用
			 */
			@Override
			public boolean matches(CharSequence charSequence, String s) {
				return s.equals(DigestUtils.md5DigestAsHex(charSequence.toString().getBytes()));
			}
		});
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/**
		 * rememberMe 允许配置“记住我”的验证 authorizeRequests() 允许使用HttpServletRequest限制访问
		 * logout() 添加退出登录支持。当使用WebSecurityConfigurerAdapter时，这将自动应用。默认情况是，访问URL“/
		 * logout”，使用HTTP会话无效来清除用户，清除已配置的任何#rememberMe()身份验证，SecurityContextHolder清除，然后重定向到“/
		 * login ？成功” anonymous()
		 * 允许配置匿名用户的表示方法。当与WebSecurityConfigurerAdapter结合使用时，这将自动应用。默认情况下，匿名用户将使用org.springframework.security.authentication.AnonymousAuthenticationToken表示，并包含角色“ROLE_ANONYMOUS”
		 * formLogin()
		 * 指定支持基于表单的身份验证。如果未指定FormLoginConfigurer#loginPage(String)，则将生成默认登录页面
		 * oauth2Login() 根据外部OAuth 2.0或OpenID Connect 1.0提供程序配置身份验证
		 */
		http.authorizeRequests()
		.antMatchers("/admin/category/all").authenticated()
                .antMatchers("/article/publicArticle").authenticated()
		//表示该路径不需要身份认证
		.antMatchers("/admin/**", "/reg")
				.hasRole("超级管理员")
				// admin/**的URL都需要有超级管理员角色，如果使用.hasAuthority()方法来配置，需要在参数中加上ROLE_,如下.hasAuthority("ROLE_超级管理员")
				.anyRequest().authenticated()// 所有请求都要认证
				// 其他的路径都是登录后即可访问
				.and().formLogin()// 表单验证
				.loginPage("/login_page")
				/*登录页面为/login_page
				formLogin
public  FormLoginConfigurer < HttpSecurity > formLogin（）
指定支持基于表单的身份验证。如果 FormLoginConfigurer.loginPage(String)未指定，则将生成默认登录页面。*/

				.successHandler(new AuthenticationSuccessHandler() {
					@Override
					public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
							HttpServletResponse httpServletResponse, Authentication authentication)
							throws IOException, ServletException {
						httpServletResponse.setContentType("application/json;charset=utf-8");
						PrintWriter out = httpServletResponse.getWriter();
						out.write("{\"status\":\"success\",\"msg\":\"登录成功\"}");
						out.flush();
						out.close();
					}
				}).failureHandler(new AuthenticationFailureHandler() {
					@Override
					public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
							HttpServletResponse httpServletResponse, AuthenticationException e)
							throws IOException, ServletException {
						httpServletResponse.setContentType("application/json;charset=utf-8");
						PrintWriter out = httpServletResponse.getWriter();
						out.write("{\"status\":\"error\",\"msg\":\"登录失败\"}");
						out.flush();
						out.close();
					}
				})
				.loginProcessingUrl("/login")
				/*
				* 指定用于验证凭据的URL。
				* 验证用户名和密码的URL*/
				.usernameParameter("username")
				.passwordParameter("password").permitAll()//当访问到这个url不需要身份认证
				.and()
				//Return the SecurityBuilder when done using the SecurityConfigurer
				.logout().permitAll().
				and().csrf().disable().
				exceptionHandling()
				.accessDeniedHandler(getAccessDeniedHandler());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/blogimg/**", "/index.html", "/static/**");
	}

	@Bean
	AccessDeniedHandler getAccessDeniedHandler() {
		return new AuthenticationAccessDeniedHandler();
	}
}