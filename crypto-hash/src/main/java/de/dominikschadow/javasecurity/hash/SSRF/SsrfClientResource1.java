package com.geekmasher.ssrf;

import com.geekmasher.ssrf.utils.Streamer;

import org.restlet.resource.ClientResource;
import org.restlet.representation.Representation;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class SsrfClientResource1 {

    @GetMapping("/ssrf/clientresource1")
    @ResponseBody
	public String index(@RequestParam(required = false) String input) {

        try {
            ClientResource resource = new ClientResource(input);
            Representation repr = resource.get();

            String result = Streamer.readStream(repr.getStream());

            return result;
        }
        catch (Exception e) {
            return "Invalid URL :: " + input;
        }
	}
}
