package com.regie.bricotek.Services.PretsServices;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("prets")
@RequiredArgsConstructor
@Tag(name = "Prets")
public class PretController {
}
