package com.challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.challenge.event.Events;
import com.challenge.utils.EventXML;

@Controller
public class SubscriptionController {
	private final static String CREATE = "create";
	private final static String CANCEL = "cancel";
	private final static String SSO = "sso";

	//appdirectintegration.cloudfoundry.com/subscription?action=create&token={token}
	//appdirectintegration.cloudfoundry.com/subscription?action=cancel&token={token}
	@RequestMapping(value = "/subscription", method = RequestMethod.GET)
	public @ResponseBody String subscripe(@RequestParam("action") String action, 
				@RequestParam(value="token", defaultValue="") String token,
				@RequestParam(value="openid", defaultValue="") String openid,
				@RequestParam(value="accountId", defaultValue="") String acctid) {
		String xml = "<Invalid></Invalid>";
		if (CREATE.equals(action) || CANCEL.equals(action)) {
			Events e = new Events();
			xml = e.FectchEvent(token);
			xml = e.handleEvent(new EventXML(xml));
		} else if (SSO.equals(action)) {
			//acctid: cliangdev@gmail.com
			//openid: https://www.appdirect.com/openid/id/fa063459-cc4c-43e6-9c6a-508193cbf1e3
			// redirect user to a register screen
			xml = "<test>" + acctid + ", " + openid + "</test>";
			
		}

		return xml;
	} 
}
