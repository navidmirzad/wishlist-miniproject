package com.example.wishlistproject.controller;

import com.example.wishlistproject.dto.WishDTO;
import com.example.wishlistproject.dto.wishlistDTO;
import com.example.wishlistproject.model.User;
import com.example.wishlistproject.service.wishlistService;
import jakarta.servlet.http.HttpSession;
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

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    @GetMapping("")
    public String index() {
        return "index";
    }

    @PostMapping("") // index is loginPage
    public String index(@RequestParam("userName") String userName,
                        @RequestParam("userPassword") String userPassword,
                        HttpSession session,
                        Model model)
    {
        // find user in repo - return loggedIn if succes
        User user = wishlistService.getUser(userName);
        System.out.println(user);
        if (user != null)
            if (user.getUserPassword().equals(userPassword)) {
                // create session for user and set session timeout to 30 sec (container default: 15 min)
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(30);
                return "SuccessSeeLists";
            }
        // wrong login info
        model.addAttribute("wrongLoginInfo", true);
        System.out.println("wrong login");
        return "index";
        //  boolean login = wishlistService.checkLogin(uid, pwd);

      /*  System.out.println(login);
        // ? means if true - : means if else
        return (login) ? "redirect:/wishlist/SuccessSeeLists" : "redirect:/wishlist/index/";*/

    }

    @GetMapping("/createwish")
    public String createWish(Model model, HttpSession session) {
        WishDTO wish = new WishDTO();
        model.addAttribute("wish", wish);

        model.addAttribute("wishlists", wishlistService.getWishLists());
        return "createWish";
    }

    @PostMapping("/createwish")
    public String createdWish(@ModelAttribute("wish") WishDTO wish, HttpSession session) {
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
        return "createUserSucces";
    }

    @GetMapping("/createwishlist")
    public String createWishlist(Model model) {
        wishlistDTO wishlist = new wishlistDTO();
        model.addAttribute("wishlist", wishlist);

        return"createWishlist";
    }

    @PostMapping("/createwishlist")
    public String createdWishlist(@ModelAttribute("wishlist") wishlistDTO wishlist,
                                  Model model) {
        wishlistService.createWishlist(wishlist);
        model.addAttribute("wishlists", wishlistService.getWishLists());

        return "SuccessSeeLists";
    }

    @GetMapping("/SuccessSeeLists")
    public String seeWishlists(HttpSession session) {
        return isLoggedIn(session) ? "SuccessSeeLists" : "index";
    }


}
