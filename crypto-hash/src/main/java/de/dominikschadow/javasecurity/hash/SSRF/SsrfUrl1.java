package com.geekmasher.ssrf;

import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.net.URLConnection;

import com.geekmasher.ssrf.utils.Streamer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class SsrfUrl1 {

    @GetMapping("/ssrf/url1")
    @ResponseBody
	public String index(@RequestParam(required = false) String input) {

        try {
            URL url = new URL(input);
            URLConnection connection = url.openConnection();

            return Streamer.readStream(connection.getInputStream());
        }
        catch (Exception e) {
            return "Invalid URL :: " + input;
        }
	}
}
