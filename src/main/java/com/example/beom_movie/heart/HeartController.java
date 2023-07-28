package com.example.beom_movie.heart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/heart")
public class HeartController {

    private final HeartService heartService;

    @PostMapping
    public ResponseEntity<HeartDTO> heart(@RequestBody HeartDTO heartDTO)  {
        try {
            heartService.insert(heartDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(heartDTO, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<HeartDTO> unHeart(@RequestBody HeartDTO heartDTO)  {
        try {
            heartService.delete(heartDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(heartDTO, HttpStatus.CREATED);
    }


}
