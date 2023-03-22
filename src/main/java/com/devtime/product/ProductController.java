package com.devtime.product;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String viewProductManagerPage(Model model){
        List<Product> listProducts = productService.getAll();
        model.addAttribute("listProducts", listProducts);
        return "index";
    }

    @GetMapping("/new")
    public String viewNewProductPage(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "new_product";
        }
        else {
            productService.save(product);
            return "redirect:/";
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView viewEditProductPage(@PathVariable(name = "id") long id){
        ModelAndView mav = new ModelAndView("edit_product");
        Product product = productService.get(id);
        mav.addObject("product", product);
        return mav;
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") long id){
        productService.deleteProduct(id);
        return "redirect:/";
    }


}
