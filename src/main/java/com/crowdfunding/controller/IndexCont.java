package com.crowdfunding.controller;

import com.crowdfunding.controller.main.Attributes;
import com.crowdfunding.model.enums.Scope;
import com.crowdfunding.model.enums.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexCont extends Attributes {

    @GetMapping
    public String index1(Model model) {
        AddAttributesCatalog(model);
        return "catalog";
    }

    @GetMapping("/index")
    public String index2(Model model) {
        AddAttributesCatalog(model);
        return "catalog";
    }

    @GetMapping("/about")
    public String about(Model model) {
        AddAttributes(model);
        return "about";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam Scope scope, @RequestParam Type type) {
        AddAttributesCatalogSearch(model, scope, type);
        return "catalog";
    }
}
