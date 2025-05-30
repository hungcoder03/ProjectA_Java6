package com.adc.project_a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ProductDetailController {

    @GetMapping("/detail/BlackMythWukong")
    public String detailGame1() {
        return "product-details";
    }
    @GetMapping("/detail/GunfireReborn")
    public String detailGame2() {
        return "game/game2";
    }
    @GetMapping("/detail/Borderlands3")
    public String detailGame3() {
        return "game/game3";
    }
    @GetMapping("/detail/Diablo®IV")
    public String detailGame4() {
        return "game/game4";
    }
    @GetMapping("/detail/FirstDwarf")
    public String detailGame5() {
        return "game/game5";
    }
    @GetMapping("/detail/GiantsUprising")
    public String detailGame6() {
        return "game/game6";
    }
    @GetMapping("/detail/HogwartsLegacy")
    public String detailGame7() {
        return "game/game7";
    }
    @GetMapping("/detail/Avowed")
    public String detailGame8() {
        return "game/game8";
    }
    @GetMapping("/detail/Cyberpunk2077")
    public String detailGame9() {
        return "game/game9";
    }
    @GetMapping("/detail/Battlefield™1")
    public String detailGame10() {
        return "game/game10";
    }
    @GetMapping("/detail/ReadyorNot")
    public String detailGame11() {
        return "game/game11";
    }
    @GetMapping("/detail/Hunt:Showdown1896")
    public String detailGame12() {
        return "game/game12";
    }
}