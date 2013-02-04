/**
 * 
 */
package com.challenge.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
public class LoginLogoutController {
	//appdirectintegration.cloudfoundry.com/login?error={error}&openid={openid}&accountId={accountIdentifier}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage(@RequestParam(value="error", required=false) boolean error,
			@RequestParam(value="openid", defaultValue="") String openid,
			@RequestParam(value="accountId", defaultValue="there") String acctid,
			ModelMap model) {

		if (error == true) {
			model.put("error", "You have entered an invalid username or password!");
		} else {
			model.put("error", "");
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("user_id", acctid);
		mav.addObject("openid_url", openid);
		return mav;
	}
}