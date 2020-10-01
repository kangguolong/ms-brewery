package com.pk.ms.msbrewery.web.controller;

import com.pk.ms.msbrewery.services.BeerService;
import com.pk.ms.msbrewery.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> getBeer(@PathVariable UUID beerId) {
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody BeerDto beerDto) {
        BeerDto saveDto = beerService.saveNewBeer(beerDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer" + saveDto.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{beerId}"})
    public ResponseEntity handleUpdate(@PathVariable UUID beerId, @RequestBody BeerDto beerDto) {
        beerService.updateBeer(beerId, beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable UUID beerId) {
        beerService.deleteById(beerId);
    }
}
