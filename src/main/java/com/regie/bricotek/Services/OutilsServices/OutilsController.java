package com.regie.bricotek.Services.OutilsServices;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("outils")
@RequiredArgsConstructor
@Tag(name = "Outils")
public class OutilsController {
}
