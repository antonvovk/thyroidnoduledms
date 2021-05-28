import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, Validators } from "@angular/forms";
import { QualificationService } from "../qualification.service";
import { QualificationQuestion } from "../../_models/qualification-question.mode";
import { QualificationAnsweredQuestion } from "../../_models/qualification-answered-question.mode";
import { QualificationGivenAnswer } from "../../_models/qualification-given-answer.model";
import { AuthService } from "../../auth/auth.service";

@Component({
  selector: 'app-qualification-testing',
  templateUrl: './qualification-testing.component.html',
  styleUrls: ['./qualification-testing.component.scss']
})
export class QualificationTestingComponent implements OnInit {

  form = new FormArray([])
  questions: QualificationQuestion[] = []
  qualificationResult: any

  constructor(private qualificationService: QualificationService,
              private authService: AuthService) {
  }

  ngOnInit(): void {
    this.qualificationService.getAllQuestions().subscribe(questions => {
      this.questions = questions

      for (const question of questions) {
        this.form.push(new FormControl(null, Validators.required))
      }
    })
  }

  getControl(index: number): FormControl {
    return this.form.at(index) as FormControl
  }

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAsTouched()
      return
    }

    const answeredQuestions: QualificationAnsweredQuestion[] = []

    for (let i = 0; i < this.questions.length; ++i) {
      answeredQuestions.push(<QualificationAnsweredQuestion>{
        question: this.questions[i],
        givenAnswer: <QualificationGivenAnswer>{
          text: this.form.at(i).value
        }
      })
    }

    this.qualificationService.testQualification(answeredQuestions).subscribe(res => {
      console.log(res);
      this.qualificationResult = res
      this.authService.qualify(res.passed)
    })
  }
}
