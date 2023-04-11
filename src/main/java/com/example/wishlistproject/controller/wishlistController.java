package com.example.wishlistproject.controller;

import com.example.wishlistproject.dto.WishDTO;
import com.example.wishlistproject.dto.wishlistDTO;
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
        return "redirect:/wishlist/seewishes";
    }

    @GetMapping("/seewishes")
    public String seeWishes(Model model) {
        model.addAttribute("wishes", wishlistService.getWishes());
        return "wishes";
    }

    @PostMapping("/deletewish")
    public String deleteWish(@RequestParam("id") int id) {
        wishlistService.deleteWish(id);
        return "redirect:/wishlist/seewishes";
    }

    @GetMapping("/edit/wish/{id}")
    public String editWish(@PathVariable int id, Model model) {
        WishDTO wish = wishlistService.findWishById(id);
        model.addAttribute("wish", wish);
        model.addAttribute("wishlists", wishlistService.getWishLists());
        return "editWish";
    }

    @PostMapping("/edit/wish/{id}")
    public String editedWish(@PathVariable int id, @ModelAttribute WishDTO editedWish) {
        wishlistService.editWish(id, editedWish);
        return "redirect:/wishlist/seewishes";
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

    @GetMapping("/createwishlist")
    public String createWishlist(Model model) {
        wishlistDTO wishlist = new wishlistDTO();
        model.addAttribute("wishlist", wishlist);

        return "createWishlist";
    }

    @PostMapping("/createwishlist")
    public String createdWishlist(@ModelAttribute("wishlist") wishlistDTO wishlist, Model model) {
        wishlistService.createWishlist(wishlist);
        model.addAttribute("wishlists", wishlistService.getWishLists());

        return "SuccessSeeLists";
    }




  /*  @PostMapping("/wishlist/createwish")
    public String handleImageUpload(@RequestParam("imageFile") MultipartFile imageFile) {
        // Handle the upload image file
        return "redirect:/wishlist/";
    }*/


}
