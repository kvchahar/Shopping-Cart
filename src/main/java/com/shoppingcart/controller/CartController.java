package com.shoppingcart.controller;

import com.shoppingcart.global.GlobalData;
import com.shoppingcart.model.Product;
import com.shoppingcart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {

    @Autowired
    ProductService productService;

    @RequestMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id){
        GlobalData.cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }

    @RequestMapping("/cart")
    public String cartGet(Model model){
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart",GlobalData.cart);
        return "cart";
    }

    @RequestMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index){
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }

    @RequestMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";
    }
}
