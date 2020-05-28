package oneday.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String uri=req.getRequestURI();
		System.out.println(uri);
//		if(uri.startsWith("/OneDay/user/login")||uri.startsWith("/OneDay/user/register")) {
//			return true;
//		}
//		else {
//			if(req.getSession().getAttribute("user")==null) {
//				System.out.println("用户未登录");
//				if(!uri.equals("/OneDay/user/nullUser")) {
//					resp.sendRedirect("/OneDay/user/nullUser");
//					return false;
//				}
//				else {
//					return true;
//				}
//			}
//			else {
//				return true;
//			}
//		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
