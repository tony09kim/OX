package com.example.OX.controller;

import com.example.OX.dto.QuizDto;
import com.example.OX.dto.ResultDto;
import com.example.OX.entitiy.Quiz;
import com.example.OX.service.QuizService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j

public class OxController {

    private final QuizService quizService;
    public OxController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("list")
    public String showList(){
        return "/list";
    }

    @GetMapping("insertForm")
    public String newQuiz(Model model){
        model.addAttribute("quizDto",new QuizDto());
        return "quiz/insert";
    }
    @PostMapping("insert")
    public String insertQuiz(@Valid @ModelAttribute("quizDto")QuizDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "quiz/insert";
        }
        if (dto.isO() && dto.isX()){
            return "quiz/insert";
        }else {
            if (!dto.isO() && !dto.isX()){
                return "quiz/insert";
            }else {
                quizService.insertQuiz(dto);
                return "redirect:/list";
            }
        }
    }

    @GetMapping("updateForm")
    public String updateQuiz(Model model){

        List<QuizDto> dtoList = quizService.showAllQuizs();
        model.addAttribute("quizDto",dtoList);
        return "/quiz/quiz";
    }

    @GetMapping("update")
    public String updateQuiz2(@RequestParam("updateId")Long id, Model model){
        QuizDto quizDto=quizService.getOneQuiz(id);
        model.addAttribute(quizDto);
        return "/quiz/update";
    }
    @PostMapping("update")
    public String updateQuiz3(@ModelAttribute("quizDto")QuizDto quizDto){
        if (quizDto.isO() && quizDto.isX()){
            return "quiz/update";
        }else {
            if (!quizDto.isO() && !quizDto.isX()){
                return "quiz/update";
            }else {
                quizService.updateQuiz(quizDto);
                return "quiz/quiz";
            }
        }
    }
    @PostMapping("delete")
    public String delete(@RequestParam("deleteId")Long id){
        quizService.deleteQuiz(id);
        return "quiz/quiz";
    }
    @GetMapping("showList")
    public String showList(Model model){
        List<QuizDto> dtoList=quizService.showAllQuizs();
        model.addAttribute("quizDto",dtoList);
        return "quiz/show";
    }

    boolean b;
    @GetMapping("play")
    public String playGame(Model model){
        QuizDto dto=quizService.getRandomQuiz();
        model.addAttribute("quizDto",dto);
        boolean b = dto.isO();
        ResultDto resultDto = new ResultDto();
        model.addAttribute("resultDto",resultDto);
        return "quiz/play";
    }
    @PostMapping("play")
    public String showResult(@ModelAttribute("quizDto")QuizDto quizDto , @ModelAttribute("resultDto")ResultDto resultDto, Model model){
        String yesOrNo;
        if (resultDto.isO() && resultDto.isX()){
            return "list";
        }else {
            if (!resultDto.isO() && !resultDto.isX()) {
                return "list";
            } else {

                if (b==resultDto.isO())
                {
                 yesOrNo="정답입니다.";
                }else {
                    yesOrNo="오답입니다.";
                }
                model.addAttribute("yesOrNo",yesOrNo);
                return "quiz/result";
            }
        }
    }


}
