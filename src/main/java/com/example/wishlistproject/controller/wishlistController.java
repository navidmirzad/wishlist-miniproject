package com.example.wishlistproject.controller;

import com.example.wishlistproject.dto.WishDTO;
import com.example.wishlistproject.dto.wishlistDTO;
import com.example.wishlistproject.model.User;
import com.example.wishlistproject.model.Wishlist;
import com.example.wishlistproject.service.wishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        model.addAttribute("wishlists", wishlistService.getWishlists());

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

    @GetMapping("/createuser")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "createUser";
    }

    @PostMapping("/createuser")
    public String createdUser(@ModelAttribute("user") User user) {
        wishlistService.createUser(user);
        return "redirect:/wishlist/seewishlists";
    }

    @GetMapping("/seewishlists")
    public String seeWishlists(Model model) {
        model.addAttribute("wishlists", wishlistService.getWishlists());
        return "wishlists";
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

        return "redirect:/wishlist/seewishlists";
    }

    @PostMapping("/deletewishlist")
    public String deleteWishlist(@RequestParam("id") int id) {
        wishlistService.deleteWishlist(id);
        return "redirect:/wishlist/seewishlists";
    }




  /*  @PostMapping("/wishlist/createwish")
    public String handleImageUpload(@RequestParam("imageFile") MultipartFile imageFile) {
        // Handle the upload image file
        return "redirect:/wishlist/";
    }*/


}
