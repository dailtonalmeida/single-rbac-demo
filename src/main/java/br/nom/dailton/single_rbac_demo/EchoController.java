package br.nom.dailton.single_rbac_demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EchoController {

    @GetMapping(value = "/echo/{input}")
    public Map<String, String> echo(@PathVariable("input") String input) {
        return Map.of("echo", input);
    }

}
