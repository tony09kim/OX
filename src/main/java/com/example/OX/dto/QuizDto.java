package com.example.OX.dto;


import com.example.OX.entitiy.Quiz;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private Long id;
    @Size(min = 0, message = "글자를 쓰십시오.")
    private String text;
    private boolean o;
    private boolean x;
    @Size(min = 2, message = "이름은 2글자 이상입니다.")
    private String user;
    public Quiz fromQuizDto(QuizDto dto){
        Quiz quiz = new Quiz();
        quiz.setId(dto.getId());
        quiz.setText(dto.getText());
        quiz.setUser(dto.getUser());
        quiz.setO(dto.isO());
        quiz.setX(dto.isX());
        return quiz;
    }

    public static QuizDto fromQuizEntity(Quiz quiz) {
        return new QuizDto(
                quiz.getId(),
                quiz.getText(),
                quiz.isO(),
                quiz.isX(),
                quiz.getUser()
        );
    }
}
