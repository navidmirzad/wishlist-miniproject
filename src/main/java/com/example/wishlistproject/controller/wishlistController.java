package com.example.wishlistproject.controller;

import com.example.wishlistproject.dto.WishDTO;
import com.example.wishlistproject.model.User;
import com.example.wishlistproject.model.Wish;
import com.example.wishlistproject.model.Wishlist;
import com.example.wishlistproject.service.wishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/wishlist")
public class wishlistController {

    private wishlistService wishlistService;

    public wishlistController(wishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/createwish")
    public String createWish(Model model) {
        WishDTO wish = new WishDTO();
        model.addAttribute("wish", wish);

        model.addAttribute("wishlists", wishlistService.getWishLists());

        return "createWish";
    }

    @PostMapping("/createwish")
    public String createdWish(@ModelAttribute("wish") WishDTO wish) {
        wishlistService.createWish(wish);
        return "createWish";
    }

    @GetMapping("/createuser")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "createUser";
    }

    @PostMapping("/createuser")
    public String createdUser(@ModelAttribute("user") User user) {
        wishlistService.createUser(user);
        return "createUserSuccess";
    }

    @PostMapping("/wishlist/createwish")
    public String handleImageUpload(@RequestParam("imageFile") MultipartFile imageFile) {
        // Handle the upload image file
        return "redirect:/wishlist/";
    }


}
