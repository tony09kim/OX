package com.example.OX.service;

import com.example.OX.dto.QuizDto;
import com.example.OX.entitiy.Quiz;
import com.example.OX.repository.QuizRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class QuizService {
    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public void insertQuiz(QuizDto dto){
        Quiz quiz = dto.fromQuizDto(dto);
        quizRepository.save(quiz);
    }
    public List<QuizDto> showAllQuizs(){
        List<QuizDto> quizDtoList = new ArrayList<>();
        return quizRepository.findAll()
                .stream()
                .map(x-> QuizDto.fromQuizEntity(x))
                .toList();
    }

    public QuizDto getOneQuiz(Long id){
        QuizDto quizDto = quizRepository.findById(id).map(x-> QuizDto.fromQuizEntity(x)).orElse(null);
        return quizDto;
    }

    public void updateQuiz(QuizDto dto){
        Quiz quiz = dto.fromQuizDto(dto);
        quizRepository.save(quiz);
    }
    public void deleteQuiz(Long id){
        quizRepository.deleteById(id);
    }

    public QuizDto getRandomQuiz(){
        Random random = new Random();
        List<QuizDto> quizList = new ArrayList<QuizDto>();
        quizList = quizRepository.findAll()
                .stream()
                .map(x-> QuizDto.fromQuizEntity(x))
                .toList();
        int i =quizList.toArray().length;
        int r = random.nextInt(0,i+1-1);
        QuizDto quizDto = quizList.get(r);
        log.info(quizDto.toString());
        return quizDto;
    }

}
