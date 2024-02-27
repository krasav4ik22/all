package com.geekmasher.ssrf;

import org.springframework.web.bind.annotation.RestController;

import org.jsoup.Jsoup;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class SsrfJSoup1 {

    @GetMapping("/ssrf/jsoup1")
    @ResponseBody
	public String index(@RequestParam(required = false) String input) {

        try {
            String result = Jsoup.connect(input).get().html();

            return result;
        }
        catch (Exception e) {
            return "Invalid URL :: " + input;
        }
	}
}
