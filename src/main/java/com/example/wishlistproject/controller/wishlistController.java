package com.example.wishlistproject.controller;

import com.example.wishlistproject.model.Wish;
import com.example.wishlistproject.service.wishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Wishlist")
public class wishlistController {

    private wishlistService wishlistService;

    public wishlistController(wishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/create")
    public String createWish(Model model) {
        Wish wish = new Wish();
        model.addAttribute("wish", wish);

        model.addAttribute("wishlists", wishlistService.getWishLists());
        return "createWish";
    }

    @GetMapping("/create")
    public String createdWish(@ModelAttribute("wish") Wish wish) {
      //   not working yet, need to create "addWish" method in wishListRepositoryDB  //  wishlistService.addWish(wish);
        return "creation_success";
    }

}
