package io.github.wbdsjunior.fulfillment.orders.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.github.wbdsjunior.fulfillment.orders.BuyerDto;
import io.github.wbdsjunior.fulfillment.orders.SaveBuyerService;


@RestController
@RequestMapping("/buyers")
public class BuyersRestController {

    private final SaveBuyerService saveBuyerService;

    public BuyersRestController(final SaveBuyerService saveBuyerService) {

        this.saveBuyerService = saveBuyerService;
    }

    // @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // public ResponseEntity<Set<BuyerDto>> save(@RequestBody MultipartFile file) throws Exception {
    
    //     var tempFile = Files.createTempFile(
    //               null
    //             , String.format(
    //                       "-%s"
    //                     , file.getOriginalFilename())
    //         )
    //             .toFile();

    //     file.transferTo(tempFile);
    //     return ResponseEntity.ok()
    //             .body(saveBuyerService.save(tempFile));
    // }

    @PostMapping
    public ResponseEntity<Set<BuyerDto>> save() throws Exception {
    
        var file = new File(getClass()
            .getClassLoader()
            .getResource("data_1.txt")
            .getPath());

        return ResponseEntity.ok()
                .body(saveBuyerService.save(file));
    }
}
