package pl.vachacz.angular.server.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.vachacz.angular.server.Nationality;
import pl.vachacz.angular.server.rest.common.BaseController;

@Controller
public class MasterdataController extends BaseController {

	@RequestMapping("/masterdata/nationalities")
	public @ResponseBody List<String> getNationalities() {
		List<String> result = new ArrayList<String>();
		for (Nationality nationality : Nationality.values()) {
			result.add(nationality.code());
		}
		return result;
	}
	
}
