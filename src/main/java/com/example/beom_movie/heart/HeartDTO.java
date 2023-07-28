package com.example.beom_movie.heart;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class HeartDTO {

    private Long memberId;
    private Long reviewNum;

    public HeartDTO(Long memberId, Long reviewNum) {
        this.memberId = memberId;
        this.reviewNum = reviewNum;
    }

}
